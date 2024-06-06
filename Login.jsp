<!DOCTYPE html>
<html>
    <head>
        <title>UNICACHI</title>
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <link rel="icon" href="IMG/index_u/logo1.png" type="image/x-icon">
        <link href="CSS/EstiloLogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="login">
            <div>
                <img src="IMG/logo1.png" alt="Ícono de inicio de sesión"/>
            </div>
            <form action="srvUsuario?accion=verificar" method="POST">
                <label for="txtusuario">Usuario:</label>
                <input type="text" id="txtusuario" name="txtusuario" required/><br>
                <label for="txtclave">Contraseña:</label>
                <input type="password" id="txtclave" name="txtclave" required/><br><br>
                <input type="submit" value="Ingresar"/>    
                <button type="button" onclick="window.location.href = 'index.html'" class="invitado-button">Invitado</button>
                <label><span class="enlace"><a href="Interfaces/Recuperar.jsp">¿Olvidaste la contraseña?</a></span></label>
                <label><span class="enlace"><a href="Interfaces/Registrarse.jsp">Registrarse</a></span></label>
            </form>
            <span class="mensaje">
                <%
                    String mensaje = (String) request.getAttribute("mensaje");
                    if (mensaje != null) {
                        out.print(mensaje);
                    }
                %>
            </span>
        </div>
    </body>
</html>
