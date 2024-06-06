package Persistencia;
import java.sql.*;
public class Conexion implements Parametros {

    public static Object getConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public Connection con;
    public PreparedStatement ps;
    public Statement smt;
    public ResultSet rs;
    public String mensaje;
    public Conexion(){
        try{
            Class.forName(DRIVER);
            con =  DriverManager.getConnection(URL,USER,CLAVE);
            smt = con.createStatement();
            mensaje="Conexión OK!!!";
        }catch(Exception ex){
            mensaje="ERROR al conectar a la base de datos.."+ex;
        }
    }

     public Connection getConnection(){
        return con;
    }
     
}//fin de la clase
