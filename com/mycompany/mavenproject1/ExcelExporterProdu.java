package com.mycompany.mavenproject1;

import java.io.FileOutputStream;
import java.io.IOException;
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

public class ExcelExporterProdu {

    public static void main(String[] args) {
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

            // Llamar al método exportToExcel para exportar los datos
            ExcelExporterProdu exporter = new ExcelExporterProdu();
            String filePath = "Productos.xlsx"; // Nombre del archivo de Excel
            exporter.exportToExcel(data, filePath);

            // Cerrar la conexión a la base de datos
            rs.close();
            stmt.close();
            conn.close();

            System.out.println("¡Los datos se han exportado exitosamente a Excel!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToExcel(List<String> data, String filePath) throws IOException {
        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();

        // Crear una nueva hoja en el libro
        Sheet sheet = workbook.createSheet("Productos"); // Nombre de la hoja

        // Crear una fila para las cabeceras
        Row headerRow = sheet.createRow(0);

        // Crear celdas para las cabeceras
        Cell cell1 = headerRow.createCell(0);
        cell1.setCellValue("ID");

        Cell cell2 = headerRow.createCell(1);
        cell2.setCellValue("NOMBRE");

        Cell cell3 = headerRow.createCell(2);
        cell3.setCellValue("DESCRIPCION");

        Cell cell4 = headerRow.createCell(3);
        cell4.setCellValue("PRECIO");

        Cell cell5 = headerRow.createCell(4);
        cell5.setCellValue("CANTIDAD");

        // Insertar los datos en las filas
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            String[] rowData = data.get(rowIndex).split(",");

            for (int columnIndex = 0; columnIndex < rowData.length; columnIndex++) {
                Cell cell = row.createCell(columnIndex);
                cell.setCellValue(rowData[columnIndex]);
            }
        }

        // Guardar el libro de Excel en un archivo
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        // Cerrar el libro para liberar recursos
        workbook.close();
    }
}
