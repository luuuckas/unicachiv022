package Controlador;

import Modelo.Producto;
import DAO.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JRException;

public class srvProducto extends HttpServlet {

    String listar = "vista/listar.jsp";
    String add = "vista/add.jsp";
    ProductoDAO dao = new ProductoDAO(); // Asegúrate de tener un constructor predeterminado en ProductoDAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";

        if (action == null || action.isEmpty()) {
            action = "listar"; // Por defecto, mostrar la lista de productos
        }

        switch (action) {
            case "listar":
                acceso = listar;
                break;
            case "add":
                acceso = add;
                break;
            case "exportarReporteProductos":
                this.exportarReporteProductos(request, response);
                acceso = listar;
                break;
            default:
                acceso = listar; // Acceso predeterminado si la acción no coincide con ninguna de las anteriores
                break;
        }

        request.getRequestDispatcher(acceso).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        if ("Agregar".equalsIgnoreCase(action)) {
            agregarProducto(request, response);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String descripcion = request.getParameter("txtDescripcion");
            double precio = Double.parseDouble(request.getParameter("txtPrecio"));
            int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
            String popularidad = request.getParameter("txtPopularidad");

            Producto producto = new Producto();
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.setPopularidad(popularidad);

            boolean agregado = dao.insertar(producto);

            if (agregado) {
                response.sendRedirect("Controlador?accion=listar");
            } else {
                request.setAttribute("error", "Error al agregar el producto.");
                request.getRequestDispatcher(add).forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el producto. Verifique los datos proporcionados.");
            request.getRequestDispatcher(add).forward(request, response);
        }
    }

    private void exportarReporteProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Producto> listaProductos = dao.listar();

            String rutaJasper = getServletContext().getRealPath("/reportesJasper/img/ReporteProductos.jasper");

            try (InputStream inputStream = new FileInputStream(new File(rutaJasper))) {
                Map<String, Object> parametros = new HashMap<>();

                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
                        new JRBeanCollectionDataSource(listaProductos));

                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

                response.setContentType("application/pdf");
                response.setContentLength(pdfBytes.length);
                response.setHeader("Content-Disposition", "inline; filename=reporte_productos.pdf");

                response.getOutputStream().write(pdfBytes);
            }
        } catch (FileNotFoundException e) {
            manejarError(response, "Archivo Jasper no encontrado.");
        } catch (JRException e) {
            manejarError(response, "No se pudo llenar el informe con datos.");
        } catch (IOException e) {
            manejarError(response, "Problema al escribir el archivo PDF.");
        } catch (Exception e) {
            manejarError(response, "Error inesperado al exportar el informe de productos.");
        }
    }

    private void manejarError(HttpServletResponse response, String mensaje) throws IOException {
        response.getWriter().println("Error: " + mensaje);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
