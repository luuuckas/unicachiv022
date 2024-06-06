
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/Controlador")
public class ControladorProductos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public  ControladorProductos() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String formato = request.getParameter("formato");

        if ("exportarCategorias".equals(accion) && "excel".equals(formato)) {
            exportarExcel(response);
        } else if ("exportarCategorias".equals(accion) && "pdf".equals(formato)) {
            try {
                exportarPDF(response);
            } catch (DocumentException | SQLException e) {
                e.printStackTrace();
                throw new IOException("Error al exportar a PDF", e);
            }
        }
    }

    private void exportarExcel(HttpServletResponse response) throws IOException {
        try {
            // Conectarse a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/categorias", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, descripcion, cantidad_productos, precio_promedio, popularidad FROM categorias ORDER BY nombre");

            // Crear un nuevo libro de Excel
            Workbook workbook = new XSSFWorkbook();

            // Crear una nueva hoja en el libro
            Sheet sheet = workbook.createSheet("Categorias");

            // Crear una fila para las cabeceras
            Row headerRow = sheet.createRow(0);

            // Crear celdas para las cabeceras
            Cell cell1 = headerRow.createCell(0);
            cell1.setCellValue("Id");

            Cell cell2 = headerRow.createCell(1);
            cell2.setCellValue("Categoría");

            Cell cell3 = headerRow.createCell(2);
            cell3.setCellValue("Descripción");

            Cell cell4 = headerRow.createCell(3);
            cell4.setCellValue("Cantidad de Productos");

            Cell cell5 = headerRow.createCell(4);
            cell5.setCellValue("Precio Promedio");

            Cell cell6 = headerRow.createCell(5);
            cell6.setCellValue("Popularidad");

            // Insertar los datos en las filas
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getString("nombre"));
                row.createCell(2).setCellValue(rs.getString("descripcion"));
                row.createCell(3).setCellValue(rs.getInt("cantidad_productos"));
                row.createCell(4).setCellValue(rs.getDouble("precio_promedio"));
                row.createCell(5).setCellValue(rs.getInt("popularidad"));
            }

            // Configurar la respuesta HTTP para descargar un archivo Excel
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Categorias.xlsx");

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
            response.setHeader("Content-Disposition", "attachment; filename=Categorias.pdf");

            // Escribir el PDF al OutputStream de la respuesta
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            // Obtener los datos de la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/categorias", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, descripcion, cantidad_productos, precio_promedio, popularidad FROM categorias ORDER BY nombre");

            // Agregar los datos al documento PDF
            while (rs.next()) {
                document.add(new Paragraph("ID: " + rs.getInt("id")));
                document.add(new Paragraph("Categoría: " + rs.getString("nombre")));
                document.add(new Paragraph("Descripción: " + rs.getString("descripcion")));
                document.add(new Paragraph("Cantidad de Productos: " + rs.getInt("cantidad_productos")));
                document.add(new Paragraph("Precio Promedio: " + rs.getDouble("precio_promedio")));
                document.add(new Paragraph("Popularidad: " + rs.getInt("popularidad")));
                document.add(new Paragraph("------------------------------------------"));
            }

            // Cerrar la conexión a la base de datos
            rs.close();
            stmt.close();
            conn.close();
        } finally {
            document.close();
        }
    }
}
