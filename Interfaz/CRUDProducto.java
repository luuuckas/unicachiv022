package Interfaz;

import Modelo.Producto;
import java.util.List;

public interface CRUDProducto {
    public List<Producto> listar();
    public Producto list(int id);
    public boolean add(Producto Producto);
    public boolean edit(Producto Producto);
    public boolean eliminar(int id);
}
