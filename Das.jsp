<%@page import="Modelo.Categoria"%>
<%@page import="DAO.CategoriaDAO"%>
<%@page import="Modelo.Producto"%>
<%@page import="DAO.ProductoDAO"%>
<%@ page import="Modelo.Puesto" %>
<%@ page import="DAO.PuestoDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Bienvenido Administrador</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/css/cssDash.css" rel="stylesheet" type="text/css"/>
        <script src="js/das.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="sidebar">
            <h2>ADM0001</h2>
            <ul>
                <li><a href="#summary">Resumen Ejecutivo</a></li>
                <li><a href="#productos">Productos</a></li>
                <li><a href="#categoria">Categorias</a></li>
                <li><a href="#puestos">Puestos</a></li>
            </ul>


            <li style="margin-top: 10px; display: flex; align-items: center; justify-content: center;text-align: center;">
                <a href="index.html" style="text-decoration: none; color: #333; font-weight: bold; display: flex; align-items: center;">
                    <img src="IMG/Salir.png" alt="Icono de Regresar" style="width: 20px; height: 20px; margin-right: 5px;">
                    <span style="margin-top: 2px;">Regresar</span>
                </a>
            </li>



        </div>
        <div class="content">
            <h1>Bienvenido Administrador</h1>

            <div id="summary" class="section">
                <h2>Resumen Ejecutivo</h2>
                <div class="stats-container">
                    <div class="stats-box">
                        <div class="card" style="width: 14.5rem; display: inline-block;">
                            <img class="card-img-top" src="IMG/Trafico.png" alt="Card image cap">
                            <div class="card-body">
                                <button type="button" class="btn btn-primary btn-lg btn-block" style="width: 100%;">5</button>
                            </div>
                        </div>
                    </div>


                    <div class="stats-box">
                        <div class="card" style="width: 18rem; display: inline-block;">
                            <img class="card-img-top" src="IMG/web.PNG" alt="Card image cap">
                            <div class="card-body">
                                <button type="button" class="btn btn-primary btn-lg btn-block"style="width: 100%;">5</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div id="productos" class="section">
                <h2>Productos</h2>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <form action="ControladorProductos" method="post" id="exportarExcelForm" style="text-align: right;">
                        <input type="hidden" name="accion" value="exportarProductos">
                        <input type="hidden" name="formato" value="excel">
                        <button type="submit" id="exportarExcelButton" class="btn btn-success">Exportar a Excel</button>
                    </form>
                    <form action="ControladorProductos" method="post" id="exportarPDFForm" style="text-align: right;">
                        <input type="hidden" name="accion" value="exportarReporteProductos">
                        <button type="submit" id="exportarPDFButton" class="btn btn-danger" name="formato" value="pdf">Exportar a PDF</button>
                    </form>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th class="text-center">Id Producto</th>
                            <th class="text-center">Descripción</th>
                            <th class="text-center">Precio</th>
                            <th class="text-center">Cantidad</th>
                            <th class="text-center">Popularidad</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            ProductoDAO productoDAO = new ProductoDAO();
                            List<Producto> listaProductos = productoDAO.listar();
                            for (Producto producto : listaProductos) { 
                        %>
                        <tr>
                            <td class="text-center"><%= producto.getIdProducto() %></td>
                            <td class="text-center"><%= producto.getDescripcion() %></td>
                            <td class="text-center"><%= producto.getPrecio() %></td>
                            <td class="text-center"><%= producto.getCantidad() %></td>
                            <td class="text-center"><%= producto.getPopularidad() %></td>
                            <td class="text-center">
                                <button class="btn btn-warning">Editar</button>
                                <button class="btn btn-danger">Eliminar</button>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>


            <div id="categoria" class="section">
                <h2>Categorias</h2>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <form action="ControladorProductos" method="post" id="exportarExcelForm" style="text-align: right;">
                        <input type="hidden" name="accion" value="exportarCategorias">
                        <input type="hidden" name="formato" value="excel">
                        <button type="submit" id="exportarExcelButton" class="btn btn-success">Exportar a Excel</button>
                    </form>
                    <form action="Controlador" method="post" id="exportarPDFForm" style="text-align: right;">
                        <input type="hidden" name="accion" value="exportarReportePuestos">
                        <button type="submit" id="exportarPDFButton" class="btn btn-danger" name="formato" value="pdf">Exportar a PDF</button>
                    </form>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th class="text-center">Id Categorias</th>
                            <th class="text-center">Categoría</th>
                            <th class="text-center">Descripción</th>
                            <th class="text-center">Cantidad de Productos</th>
                            <th class="text-center">Precio Promedio</th>
                            <th class="text-center">Popularidad</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% CategoriaDAO categoriaDAO = new CategoriaDAO();
                           List<Categoria> listaCategorias = categoriaDAO.listar();
                           for (Categoria categoria : listaCategorias) { %>
                        <tr>
                            <td class="text-center"><%= categoria.getIdCategoria() %></td>
                            <td class="text-center"><%= categoria.getNombreCategoria() %></td>
                            <td class="text-center"><%= categoria.getDescripcion() %></td>
                            <td class="text-center"><%= categoria.getCantidadProductos() %></td>
                            <td class="text-center"><%= categoria.getPrecioPromedio() %></td>
                            <td class="text-center"><%= categoria.getPopularidad() %></td>
                            <td class="text-center">
                                <button class="btn btn-warning">Editar</button>
                                <button class="btn btn-danger">Eliminar</button>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div id="puestos" class="section">
                <h2>Puestos</h2>
                <!-- exportar a PDF -->
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <form action="Controlador" method="post" style="text-align: right;">
                        <input type="hidden" name="accion" value="add">
                        <input type="hidden" name="formato" value="ExcelExporter">
                        <button type="submit" id="exportarExcelButton" class="btn btn-success">Exportar a Excel</button>
                    </form>



                    <!-- Exportar a PDF -->
                    <form action="Controlador" method="post" id="exportarPDFForm" style="text-align: right;">
                        <input type="hidden" name="accion" value="add">
                        <input type="hidden" name="formato" value="pdf">
                        <button type="submit" id="exportarPDFButton" class="btn btn-danger" name="formato" value="pdf">Exportar a PDF</button>
                    </form>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th class="text-center">Id Puesto</th>
                            <th class="text-center">Categoría</th>
                            <th class="text-center">Producto</th>
                            <th class="text-center">Dueño del Puesto</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% PuestoDAO dao = new PuestoDAO();
                            List<Puesto> listaPuestos = dao.listar();
                            for (Puesto puesto : listaPuestos) {%>
                        <tr>
                            <td><%= puesto.getIdPuesto()%></td>
                            <td><%= puesto.getCategoria()%></td>
                            <td><%= puesto.getProducto()%></td>
                            <td><%= puesto.getDueño()%></td>
                            <td class="text-center">
                                <button class="btn btn-warning">Editar</button>
                                <button class="btn btn-danger">Eliminar</button>
                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>

            <script>
                // Exportar a Excel
                document.getElementById("exportarExcelButton").addEventListener("click", function () {
                    document.getElementById("exportarExcelForm").submit();
                });

                // Exportar a PDF
                document.getElementById("exportarPDFButton").addEventListener("click", function () {
                    document.getElementById("exportarPDFForm").submit();
                });
            </script>
            <script src="js/accion.js" type="text/javascript"></script>
        </div>
    </div>
</body>
</html>
