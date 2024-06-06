package Util;

import Modelo.DetallePedido;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class Carrito {

    public void AgregarCarrito(DetallePedido detalle, HttpServletRequest request) {
        ArrayList<DetallePedido> lista = ObtenerSesion(request);
        int posc = ExisteProducto(lista, detalle.getProducto().getCodigoProducto());
        if (posc == -1) {
            lista.add(detalle);
        }else{
            DetallePedido pDet = lista.get(posc);
            pDet.AumentarCantidad(detalle.getCantidad());
            lista.set(posc, pDet);
    }
        GuardarSesion(request, lista);
    }
    
    public double ImporteTotal(ArrayList<DetallePedido> lista){
    double total = 0;
        for (DetallePedido p : lista) {
            total += p.Importe();
        }
        return total;
    }
    
    public void RemoverItemCarrito(HttpServletRequest request, int indice){
    ArrayList<DetallePedido> lista = ObtenerSesion(request);
    lista.remove(indice);
        GuardarSesion(request, lista);
    }

    public ArrayList<DetallePedido> ObtenerSesion(HttpServletRequest request) {
        ArrayList<DetallePedido> lista;

        if (request.getSession().getAttribute("carrito") == null) {
            lista = new ArrayList<>();
        } else {
            lista = (ArrayList<DetallePedido>) request.getSession().getAttribute("carrito");
        }
        return lista;
    }

    public void GuardarSesion(HttpServletRequest request, ArrayList<DetallePedido> lista) {
        request.getSession().setAttribute("carrito", lista);
    }

    public int ExisteProducto(ArrayList<DetallePedido> lista, int codigoProducto) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getProducto().getCodigoProducto() == codigoProducto) {
                return i;
            }
        }
        return -1;
    }

}
