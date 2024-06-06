package Controlador;

import DAO.UsuarioDAO;
import Modelo.DTOusuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/srvUsuario")
public class UsuarioControlador extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            if ("verificar".equals(accion)) {
                verificarUsuario(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void verificarUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nombreusuario = request.getParameter("txtusuario");
        String clave = request.getParameter("txtclave");

        DTOusuario usuario = new DTOusuario();
        usuario.setNombreusuario(nombreusuario);
        usuario.setClave(clave);

        DTOusuario usuarioVerificado = usuarioDAO.verificarUsuario(usuario);

        if (usuarioVerificado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioVerificado);
            response.sendRedirect("index.html");
        } else {
            request.setAttribute("mensaje", "Usuario o clave incorrecta");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
