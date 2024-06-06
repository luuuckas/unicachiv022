package Controlador;


import DAO.PuestoDAO;
import Modelo.Puesto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class PuestoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el valor de totalVisits, por ejemplo, desde una base de datos o calculándolo
        int totalVisits = 100; // Ejemplo, debes obtener este valor de donde corresponda

        // Configurar el atributo en la solicitud
        request.setAttribute("totalVisits", totalVisits);

        // Configura la conexión a la base de datos
        PuestoDAO dao = new PuestoDAO();
        List<Puesto> puestos = dao.listar();

        // Configura los atributos de la solicitud
        request.setAttribute("puestos", puestos);

        // Redirige al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
