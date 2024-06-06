package DAO;

import Interfaz.CRUDUsuario;
import Modelo.DTOusuario;
import Persistencia.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UsuarioDAO extends Conexion implements CRUDUsuario {
    public UsuarioDAO() {}

    @Override
    public DTOusuario verificarUsuario(DTOusuario user) throws Exception {
        DTOusuario usuario = null;
        String SQL = "SELECT * FROM usuario WHERE nombreusuario = ? AND clave = ?";
        try (PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setString(1, user.getNombreusuario());
            ps.setString(2, user.getClave());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new DTOusuario();
                    usuario.setIdusuario(rs.getString(1));
                    usuario.setNombreusuario(rs.getString(2));
                    usuario.setClave(rs.getString(3));
                    usuario.setEstado(rs.getBoolean(4));
                    usuario.setPerfil(rs.getString(5));
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR al recuperar usuario: " + ex);
        }
        return usuario;
    }

    // Otros métodos ...

    @Override
    public void editarUsuario(DTOusuario user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void copiarUsuario(String idUsuarioOriginal, String nuevoIdUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrarUsuario(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DTOusuario> listarUsuarios() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
