
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="Modelo.DTOusuario" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Intranet</title>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f0f2f5;
                color: #333;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 350px;
                text-align: center;
            }
            h2 {
                color: #4CAF50;
                margin-bottom: 20px;
                font-weight: 500;
            }
            p {
                margin: 10px 0;
                font-size: 16px;
                line-height: 1.6;
            }
            .mensaje {
                color: #e74c3c;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .container p strong {
                color: #4CAF50;
            }
            .footer {
                margin-top: 20px;
                font-size: 14px;
                color: #777;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <% 
                String mensaje = (String) request.getAttribute("mensaje");
                DTOusuario user = (DTOusuario) session.getAttribute("administrador");
            %>
            
            <div class="mensaje">
                <%= mensaje != null ? mensaje : "" %>
            </div>

            <h2>Datos del administrador</h2>
            <p><strong>ID usuario:</strong> <%= user.getIdusuario() %></p>
            <p><strong>Nombre usuario:</strong> <%= user.getNombreusuario() %></p>
            <p><strong>Perfil:</strong> <%= user.getPerfil() %></p>
        </div>
    </body>
</html>
