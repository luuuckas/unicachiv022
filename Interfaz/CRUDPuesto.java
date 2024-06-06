package Interfaz;

import Modelo.Puesto;
import java.util.List;

public interface CRUDPuesto {
    public List<Puesto> listar();
    public Puesto list(int id);
    public boolean add(Puesto puesto);
    public boolean edit(Puesto puesto);
    public boolean eliminar(int id);
}
