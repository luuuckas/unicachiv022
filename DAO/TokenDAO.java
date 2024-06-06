
package DAO;

import java.sql.SQLException;
import Persistencia.Conexion;

public class TokenDAO extends Conexion {

    public TokenDAO() {}

    public void guardarToken(String email, String token) {
        String SQL = "INSERT INTO password_reset_tokens (email, token, expiry) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, token);
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis() + 3600 * 1000)); // Token válido por 1 hora
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR al guardar el token: " + ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar conexión: " + ex);
            }
        }
    }

    public boolean validarToken(String token) {
        String SQL = "SELECT * FROM password_reset_tokens WHERE token = ? AND expiry > ?";
        boolean isValid = false;
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, token);
            ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            rs = ps.executeQuery();
            isValid = rs.next();
        } catch (SQLException ex) {
            System.out.println("ERROR al validar el token: " + ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar conexión: " + ex);
            }
        }
        return isValid;
    }

    public String obtenerEmailPorToken(String token) {
        String SQL = "SELECT email FROM password_reset_tokens WHERE token = ?";
        String email = null;
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al obtener el email por token: " + ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar conexión: " + ex);
            }
        }
        return email;
    }

    public void eliminarToken(String token) {
        String SQL = "DELETE FROM password_reset_tokens WHERE token = ?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, token);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR al eliminar el token: " + ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("ERROR al cerrar conexión: " + ex);
            }
        }
    }
}
