package DAO;

import Modelo.Producto;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Conexion cn;

    public ProductoDAO() {
        cn = new Conexion();
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String SQL = "SELECT * FROM productos";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("IdProducto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setCantidad(rs.getInt("Cantidad"));
                producto.setPopularidad(rs.getString("Popularidad"));
                lista.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertar(Producto producto) {
        String SQL = "INSERT INTO productos (Descripcion, Precio, Cantidad, Popularidad) "
                   + "VALUES (?, ?, ?, ?)";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setString(1, producto.getDescripcion());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getCantidad());
            ps.setString(4, producto.getPopularidad());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
