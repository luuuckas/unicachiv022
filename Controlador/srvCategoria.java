package Controlador;

import Modelo.Categoria;
import DAO.CategoriaDAO;
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

public class srvCategoria extends HttpServlet {

    String listar = "vista/listar.jsp";
    String add = "vista/add.jsp";
    CategoriaDAO dao = new CategoriaDAO(); // Asegúrate de tener un constructor predeterminado en CategoriaDAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";

        if (action == null || action.isEmpty()) {
            action = "listar"; // Por defecto, mostrar la lista de categorías
        }

        switch (action) {
            case "listar":
                acceso = listar;
                break;
            case "add":
                acceso = add;
                break;
            case "exportarReporteCategorias":
                this.exportarReporteCategorias(request, response);
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
            agregarCategoria(request, response);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("txtNombre");
            String descripcion = request.getParameter("txtDescripcion");
            int cantidadProductos = Integer.parseInt(request.getParameter("txtCantidadProductos"));
            double precioPromedio = Double.parseDouble(request.getParameter("txtPrecioPromedio"));
            String popularidad = request.getParameter("txtPopularidad");

            Categoria categoria = new Categoria();
            categoria.setNombreCategoria(nombre);
            categoria.setDescripcion(descripcion);
            categoria.setCantidadProductos(cantidadProductos);
            categoria.setPrecioPromedio(precioPromedio);
            categoria.setPopularidad(popularidad);

            boolean agregada = dao.insertar(categoria);

            if (agregada) {
                response.sendRedirect("Controlador?accion=listar");
            } else {
                request.setAttribute("error", "Error al agregar la categoría.");
                request.getRequestDispatcher(add).forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar la categoría. Verifique los datos proporcionados.");
            request.getRequestDispatcher(add).forward(request, response);
        }
    }

    private void exportarReporteCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Categoria> listaCategorias = dao.listar();

            String rutaJasper = getServletContext().getRealPath("/reportesJasper/img/ReporteCategorias.jasper");

            try (InputStream inputStream = new FileInputStream(new File(rutaJasper))) {
                Map<String, Object> parametros = new HashMap<>();

                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
                        new JRBeanCollectionDataSource(listaCategorias));

                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

                response.setContentType("application/pdf");
                response.setContentLength(pdfBytes.length);
                response.setHeader("Content-Disposition", "inline; filename=reporte_categorias.pdf");

                response.getOutputStream().write(pdfBytes);
            }
        } catch (FileNotFoundException e) {
            manejarError(response, "Archivo Jasper no encontrado.");
        } catch (JRException e) {
            manejarError(response, "No se pudo llenar el informe con datos.");
        } catch (IOException e) {
            manejarError(response, "Problema al escribir el archivo PDF.");
        } catch (Exception e) {
            manejarError(response, "Error inesperado al exportar el informe de categorías.");
        }
    }

    private void manejarError(HttpServletResponse response, String mensaje) throws IOException {
        response.getWriter().println("Error: " + mensaje);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
