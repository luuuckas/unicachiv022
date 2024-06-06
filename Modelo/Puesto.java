package Modelo;

public class Puesto {

    private int idPuesto;
    private String categoria;
    private String producto;
    private String dueño;
    

    public Puesto() {
    }

    public Puesto(int idPuesto, String categoria, String producto, String dueño) {
        this.idPuesto = idPuesto;
        this.categoria = categoria;
        this.producto = producto;
        this.dueño = dueño;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

}
