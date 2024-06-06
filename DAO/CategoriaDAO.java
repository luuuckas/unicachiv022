package DAO;

import Modelo.Categoria;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Conexion cn;

    public CategoriaDAO() {
        cn = new Conexion();
    }

    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String SQL = "SELECT * FROM categoria";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setNombreCategoria(rs.getString("nombreCategoria"));
                cat.setDescripcion(rs.getString("descripcion"));
                cat.setCantidadProductos(rs.getInt("cantidadProductos"));
                cat.setPrecioPromedio(rs.getDouble("precioPromedio"));
                cat.setPopularidad(rs.getString("popularidad"));
                lista.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertar(Categoria categoria) {
        String SQL = "INSERT INTO categoria (nombreCategoria, descripcion, cantidadProductos, precioPromedio, popularidad) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getCantidadProductos());
            ps.setDouble(4, categoria.getPrecioPromedio());
            ps.setString(5, categoria.getPopularidad());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
