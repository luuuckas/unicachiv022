/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.DetallePedido;
import Modelo.Producto;
import ModeloDAO.ProductoDAO;
import Util.Carrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WINDOWS
 */
public class CarritoControlado extends HttpServlet {

    private String PagListarCarrito = "Jsp/PagCarrito.jsp";
    private String PagInicio = "index.html";
    private ProductoDAO prodDao = new ProductoDAO();
    private Carrito pCarrito = new Carrito();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                Listar(request, response);
                break;
            case "agregar":
                Agregar(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    protected void Agregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        int codigoProducto = Integer.parseInt(request.getParameter("codigoProducto"));
        
        Producto p = prodDao.BuscarPorCodigo(codigoProducto);
        
        if (p !=null) {
            DetallePedido pDet = new DetallePedido();
            pDet.setProducto(p);
            pDet.setCantidad(1);
            
            pCarrito.AgregarCarrito(pDet,request);
        }
        
        request.getRequestDispatcher(PagInicio).forward(request, response);
    }
    
    protected void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<DetallePedido> lista = pCarrito.ObtenerSesion(request);
        request.setAttribute("carrito", lista);
        request.setAttribute("total", pCarrito.ImporteTotal(lista));
        request.getRequestDispatcher(PagListarCarrito).forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
