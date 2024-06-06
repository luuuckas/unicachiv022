<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <title>UNICACHI-REGISTRO</title>
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <link rel="icon" href="IMG/index_u/logo1.png" type="image/x-icon">
    <link href="../CSS/cssRegistrarse.css" rel="stylesheet" type="text/css"/>
    <link href="../CSS/cssregistro.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h2>Registro</h2>
    
 <%
// Obtener los parámetros del formulario
String nombre = request.getParameter("nombre");
String apellido = request.getParameter("apellido");
String dni = request.getParameter("dni");
String email = request.getParameter("email");
String direccion = request.getParameter("direccion");
String password = request.getParameter("password");

// Variables para la conexión a la base de datos
Connection conn = null;
PreparedStatement stmt = null;

try {
    // Cargar el driver de MySQL
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Establecer la conexión con la base de datos
    String url = "jdbc:mysql://localhost:3308/usuario";
    String usuario = "root";
    String contraseña = "";
    conn = DriverManager.getConnection(url, usuario, contraseña);
    
    // Preparar la consulta SQL para insertar los datos del usuario
    String sql = "INSERT INTO registro (nombre, apellido, dni, email, direccion, password) VALUES (?, ?, ?, ?, ?, ?)";
    stmt = conn.prepareStatement(sql);
    stmt.setString(1, nombre);
    stmt.setString(2, apellido);
    stmt.setString(3, dni);
    stmt.setString(4, email);
    stmt.setString(5, direccion);
    stmt.setString(6, password);
    
    // Ejecutar la consulta
    stmt.executeUpdate();
    
    // Mostrar mensaje de éxito
    out.println("<h3>¡Registro exitoso!</h3>");
} catch (Exception e) {
    // Manejar cualquier error
    out.println("<h3>Error al registrar usuario:</h3>");
    out.println("<p>" + e.getMessage() + "</p>");
} finally {
    // Cerrar la conexión y el statement
    try {
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
%>
</body>
</html>
