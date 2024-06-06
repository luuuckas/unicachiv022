package Utils;
import java.sql.*;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

public class Conexion {
public static Connection getConexion(){
    Connection cn=null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost/bd_unicachi","root","");
        System.out.println("Conexion Exitosa");
    } catch (Exception e) {
        System.out.println("Error de Conexion");
    }
    return cn;
} 
   
    public static void main(String[] args) {
        Conexion.getConexion();
    }
    
}
