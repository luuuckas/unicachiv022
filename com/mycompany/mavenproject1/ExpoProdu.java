
package com.mycompany.mavenproject1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author User
 */
public class ExpoProdu extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExpoProdu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExpoProdu at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Conectarse a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_unicachi", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos"); // Consulta a la tabla "productos"

            // Procesar los resultados y guardarlos en una lista de cadenas
            List<String> data = new ArrayList<>();
            while (rs.next()) {
                StringBuilder rowData = new StringBuilder();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    rowData.append(rs.getString(i)).append(",");
                }
                data.add(rowData.toString());
            }

            // Exportar los datos a Excel
            exportarAExcel(data, "Productos.xlsx", response);

            // Cerrar la conexión a la base de datos
            rs.close();
            stmt.close();
            conn.close();

            System.out.println("¡Los datos se han exportado exitosamente a Excel!");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void exportarAExcel(List<String> data, String filePath, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + filePath);

        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();

        // Crear una nueva hoja en el libro
        Sheet sheet = workbook.createSheet("Productos"); // Nombre de la hoja

        // Crear una fila para las cabeceras
        Row headerRow = sheet.createRow(0);

        // Crear celdas para las cabeceras
        String[] headers = { "ID", "NOMBRE", "DESCRIPCION", "PRECIO", "CANTIDAD" };
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Insertar los datos en las filas
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            String[] rowData = data.get(rowIndex).split(",");

            for (int columnIndex = 0; columnIndex < rowData.length; columnIndex++) {
                Cell cell = row.createCell(columnIndex);
                cell.setCellValue(rowData[columnIndex]);
            }
        }

        // Escribir el libro de Excel en la respuesta
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } finally {
            // Cerrar el libro para liberar recursos
            workbook.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
