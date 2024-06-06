package Controlador;
//Librerias de JasperReports

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import DAO.PuestoDAO;
import Modelo.Puesto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//Librerias del Servelt
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

public class Controlador extends HttpServlet {

    String listar = "vista/listar.jsp";
    String add = "vista/add.jsp";
    String edit = "vista/edit.jsp";
    PuestoDAO dao = new PuestoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";

        if (action == null || action.isEmpty()) {
            action = "listar"; // Por defecto, mostrar la lista de puestos
        }

        switch (action) {
            case "listar":
                acceso = listar;
                break;
            case "add":
                acceso = add;
                break;
            case "editar":
                acceso = editarPuesto(request, response);
                break;
            case "Actualizar":
                actualizarPuesto(request, response);
                return; // Detener la ejecución del método después de actualizar el puesto
            case "Eliminar":
                eliminarPuesto(request, response);
                return; // Detener la ejecución del método después de eliminar el puesto

            case "exportarReportePuestos":
                this.exportarReportePuestos(request, response);
                acceso = listar;
                break;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        if ("Agregar".equalsIgnoreCase(action)) {
            agregarPuesto(request, response);
        }
    }

    private void agregarPuesto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String categoria = request.getParameter("txtCategoria");
            String producto = request.getParameter("txtProducto");
            String dueño = request.getParameter("txtDueño");

            Puesto puesto = new Puesto();
            puesto.setCategoria(categoria);
            puesto.setProducto(producto);
            puesto.setDueño(dueño);

            boolean agregado = dao.add(puesto);

            if (agregado) {
                response.sendRedirect("Controlador?accion=listar");
            } else {
                request.setAttribute("error", "Error al agregar el puesto.");
                RequestDispatcher vista = request.getRequestDispatcher(add);
                vista.forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el puesto. Verifique los datos proporcionados.");
            RequestDispatcher vista = request.getRequestDispatcher(add);
            vista.forward(request, response);
        }
    }

    private String editarPuesto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idPuestoParam = request.getParameter("idPuesto");
        if (idPuestoParam != null && !idPuestoParam.isEmpty()) {
            try {
                int idPuesto = Integer.parseInt(idPuestoParam);
                Puesto puesto = dao.list(idPuesto);
                if (puesto != null) {
                    request.setAttribute("puesto", puesto);
                    return edit;
                } else {
                    request.setAttribute("error", "El puesto no se encontró en la base de datos");
                    return listar; // o alguna otra página
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El ID del puesto no es válido");
                return listar; // o alguna otra página
            }
        } else {
            request.setAttribute("error", "El ID del puesto no se proporcionó");
            return listar; // o alguna otra página
        }
    }

    private void actualizarPuesto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPuesto = Integer.parseInt(request.getParameter("txtIdPuesto"));
        String categoria = request.getParameter("txtCategoria");
        String producto = request.getParameter("txtProducto");
        String dueño = request.getParameter("txtDueño");

        Puesto puesto = new Puesto();
        puesto.setIdPuesto(idPuesto);
        puesto.setCategoria(categoria);
        puesto.setProducto(producto);
        puesto.setDueño(dueño);

        dao.edit(puesto);

        response.sendRedirect("Controlador?accion=listar");
    }

    private void eliminarPuesto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPuesto = Integer.parseInt(request.getParameter("idPuesto"));
        dao.eliminar(idPuesto);
        response.sendRedirect("Controlador?accion=listar");
    }

    // Agrega aquí el método para agregar un nuevo puesto si es necesario
    @Override
    public String getServletInfo() {
        return "Controlador de gestión de puestos";
    }

    private void exportarReportePuestos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener la lista de puestos
            List<Puesto> listaPuestos = dao.listar();

            // Ruta al archivo Jasper (.jasper)
            String rutaJasper = getServletContext().getRealPath("/reportesJasper/img/ReportePuestos.jasper");

            // Cargar el archivo Jasper
            try (InputStream inputStream = new FileInputStream(new File(rutaJasper))) {

                // Parámetros, si los hay
                Map<String, Object> parametros = new HashMap<>();

                // Llenar el informe con datos y parámetros
                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
                        new JRBeanCollectionDataSource(listaPuestos));

                // Exportar el informe a PDF
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

                // Establecer la respuesta del servlet para enviar el PDF
                response.setContentType("application/pdf");
                response.setContentLength(pdfBytes.length);
                response.setHeader("Content-Disposition", "inline; filename=reporte_puestos.pdf");

                // Escribir los bytes del PDF en la respuesta del servlet
                response.getOutputStream().write(pdfBytes);
            }
        } catch (FileNotFoundException e) {
            manejarError(response, "Archivo Jasper no encontrado.");
        } catch (JRException e) {
            manejarError(response, "No se pudo llenar el informe con datos.");
        } catch (IOException e) {
            manejarError(response, "Problema al escribir el archivo PDF.");
        } catch (Exception e) {
            manejarError(response, "Error inesperado al exportar el informe de puestos.");
        }
    }

    private void manejarError(HttpServletResponse response, String mensaje) throws IOException {
        response.getWriter().println("Error: " + mensaje);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
