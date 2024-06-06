/*package Modelo;

import java.sql.*;
import java.util.ArrayList;
import Utils.Conexion;

public class ProductoDB {
    public static ArrayList<Producto> obtenerProducto(){
    ArrayList<Producto> lista = new ArrayList<Producto>();
        try {
            CallableStatement cl=Conexion.getConexion().prepareCall("{call listarProductos01()}");
            ResultSet rs=cl.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(rs.getInt(1),rs.getString(2),
                rs.getDouble(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public static Producto obtenerProducto(int codigo){
        Producto p=null;
        try {
            CallableStatement cl=Conexion.getConexion().prepareCall("{call sp_ProductoCod(?)}");
            cl.setInt(1, codigo);
            ResultSet rs=cl.executeQuery();
            while (rs.next()) {
                p=new Producto(rs.getInt(1),rs.getString(2),
                rs.getDouble(3),rs.getString(4),
                        rs.getInt(5),rs.getString(6));
            }
        } catch (Exception e) {
        }
        return p;
    }
    
}*/
