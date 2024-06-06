package Interfaz;

import Modelo.Categoria;
import java.util.List;

public interface CRUDCategoria {
    public List<Categoria> listar();
    public Categoria list(int id);
    public boolean add(Categoria categoria);
    public boolean edit(Categoria categoria);
    public boolean eliminar(int id);
}
