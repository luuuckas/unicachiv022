<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
      <title>UNICACHI-REGISTRATE</title>
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <link rel="icon" href="IMG/index_u/logo1.png" type="image/x-icon">
    <link href="../CSS/cssRegistrarse.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h2>Registro</h2>
    <form action="registro.jsp" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
        
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required>
        
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required>
        
        <label for="email">Correo electrónico:</label>
        <input type="email" id="email" name="email" required>
        
        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" required>
        
         <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>
        
        <input type="submit" value="Registrarse">
    </form>
</body>
</html>
