<%@page import="java.util.List"%>
<%@page import="Modelo.Puesto"%>
<%@page import="ModeloDAO.PuestoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Modificar Puesto</title>
</head>
<body>
<div class="container">
    <div class="col-lg-6">
        <% 
            PuestoDAO dao = new PuestoDAO();
            List<Puesto> listaPuestos = dao.listar();
            for (Puesto puesto : listaPuestos) {
        %>
        <h1>Modificar Puesto</h1>
        <form action="Controlador">
            Número de Puesto:<br>
            <input class="form-control" type="text" name="txtIdPuesto" value="<%= puesto.getIdPuesto()%>"><br>
            Categoría: <br>
            <input class="form-control" type="text" name="txtCategoria" value="<%= puesto.getCategoria()%>"><br>
            Producto: <br>
            <input class="form-control" type="text" name="txtProducto" value="<%= puesto.getProducto()%>"><br>
            Dueño del Puesto: <br>
            <input class="form-control" type="text" name="txtDueño" value="<%= puesto.getDueño()%>"><br>
            <input type="hidden" name="txtIdPuesto" value="<%= puesto.getIdPuesto()%>">
            <input class="btn btn-primary" type="submit" name="accion" value="Actualizar">
            <a href="Controlador?accion=listar">Regresar</a>
        </form>
        <% } %>
    </div>
</div>
</body>
</html>
