
package DAO;


import Interfaz.CRUDPuesto;
import Modelo.Puesto;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PuestoDAO implements CRUDPuesto {

    Conexion cn = new Conexion();

    @Override
    public List<Puesto> listar() {
        List<Puesto> list = new ArrayList<>();
        String sql = "SELECT * FROM puestos";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Puesto puesto = new Puesto();
                puesto.setIdPuesto(rs.getInt("idPuesto"));
                puesto.setCategoria(rs.getString("categoria"));
                puesto.setProducto(rs.getString("producto"));
                puesto.setDueño(rs.getString("dueño"));
                list.add(puesto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Puesto list(int id) {
        Puesto puesto = null;
        String sql = "SELECT * FROM puestos WHERE idPuesto = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    puesto = new Puesto();
                    puesto.setIdPuesto(rs.getInt("idPuesto"));
                    puesto.setCategoria(rs.getString("categoria"));
                    puesto.setProducto(rs.getString("producto"));
                    puesto.setDueño(rs.getString("dueño"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puesto;
    }

    @Override
    public boolean add(Puesto puesto) {
        String sql = "INSERT INTO puestos (categoria, producto, dueño) VALUES (?, ?, ?)";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, puesto.getCategoria());
            ps.setString(2, puesto.getProducto());
            ps.setString(3, puesto.getDueño());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(Puesto puesto) {
        String sql = "UPDATE puestos SET categoria = ?, producto = ?, dueño = ? WHERE idPuesto = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, puesto.getCategoria());
            ps.setString(2, puesto.getProducto());
            ps.setString(3, puesto.getDueño());
            ps.setInt(4, puesto.getIdPuesto());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM puestos WHERE idPuesto = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
