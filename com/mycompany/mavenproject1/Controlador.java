
package com.mycompany.mavenproject1;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controlador() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String formato = request.getParameter("formato");

        if ("add".equals(accion) && "excel".equals(formato)) {
            exportarExcel(response);
        } else if ("add".equals(accion) && "pdf".equals(formato)) {
            // Lógica para exportar a PDF
        }
    }

    private void exportarExcel(HttpServletResponse response) throws IOException {
        try {
            // Conectarse a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/puestos", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM puestos");

            // Procesar los resultados y guardarlos en una lista de cadenas
            List<String> data = new ArrayList<>();
            while (rs.next()) {
                StringBuilder rowData = new StringBuilder();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    rowData.append(rs.getString(i)).append(",");
                }
                data.add(rowData.toString());
            }

            // Crear un nuevo libro de Excel
            Workbook workbook = new XSSFWorkbook();

            // Crear una nueva hoja en el libro
            Sheet sheet = workbook.createSheet("Puestos");

            // Crear una fila para las cabeceras
            Row headerRow = sheet.createRow(0);

            // Crear celdas para las cabeceras
            Cell cell1 = headerRow.createCell(0);
            cell1.setCellValue("ID");

            Cell cell2 = headerRow.createCell(1);
            cell2.setCellValue("CATEGORIAS");

            Cell cell3 = headerRow.createCell(2);
            cell3.setCellValue("PRODUCTO");
            
            Cell cell4 = headerRow.createCell(3);
            cell4.setCellValue("DUEÑO");

            // Insertar los datos en las filas
            for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                String[] rowData = data.get(rowIndex).split(",");

                for (int columnIndex = 0; columnIndex < rowData.length; columnIndex++) {
                    Cell cell = row.createCell(columnIndex);
                    cell.setCellValue(rowData[columnIndex]);
                }
            }

            // Configurar la respuesta HTTP para descargar un archivo Excel
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Puestos.xlsx");

            // Escribir el workbook al OutputStream de la respuesta
            try (OutputStream out = response.getOutputStream()) {
                workbook.write(out);
            }

            // Cerrar el libro para liberar recursos
            workbook.close();

            // Cerrar la conexión a la base de datos
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error al exportar a Excel", e);
        }
    }

private void exportarPDF(HttpServletResponse response) throws IOException, DocumentException, SQLException {
    Document document = new Document();
    try {
        // Configurar la respuesta HTTP para descargar un archivo PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Puestos.pdf");

        // Escribir el PDF al OutputStream de la respuesta
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Obtener los datos de la base de datos
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/puestos", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM puestos");

        // Agregar los datos al documento PDF
        while (rs.next()) {
            document.add(new Paragraph("ID: " + rs.getInt("id")));
            document.add(new Paragraph("Categoría: " + rs.getString("categoria")));
            document.add(new Paragraph("Producto: " + rs.getString("producto")));
            document.add(new Paragraph("Dueño: " + rs.getString("dueno")));
            document.add(new Paragraph("------------------------------------------"));
        }

        // Cerrar la conexión a la base de datos
        rs.close();
        stmt.close();
        conn.close();
    } catch (DocumentException | SQLException e) {
        e.printStackTrace();
        throw new IOException("Error al exportar a PDF", e);
    } finally {
        document.close();
    }
}
}

    
