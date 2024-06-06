package Modelo;

public class Producto {
    private int codigoProducto;
    private String nombre;
    private double precio;
    private String imagen;
    private int estado;
    private String categoria;

public Producto(int codigoProducto, String nombre, double precio, String imagen, int estado,String categoria) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.estado = estado;
        this.categoria = categoria;
    }
    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategotia(String categoria) {
        this.categoria = categoria;
    }
    
    
}
