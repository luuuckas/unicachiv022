function ordenarProductos() {
    var seleccion = document.getElementById("orden").value;
    var contenedorProductos = document.getElementById("productos");

    // Obtener todos los productos
    var productos = contenedorProductos.querySelectorAll(".producto");

    // Convertir la lista de productos en un array para poder ordenarlos
    var arrayProductos = Array.from(productos);

    // Ordenar los productos según el criterio seleccionado
    arrayProductos.sort(function(a, b) {
        var nombreA = a.querySelector("p").textContent.trim().toLowerCase();
        var nombreB = b.querySelector("p").textContent.trim().toLowerCase();

        if (seleccion === "az") {
            return nombreA.localeCompare(nombreB);
        } else if (seleccion === "za") {
            return nombreB.localeCompare(nombreA);
        }
    });

    // Limpiar el contenedor de productos
    contenedorProductos.innerHTML = '';

    // Agregar los productos ordenados de vuelta al contenedor
    arrayProductos.forEach(function(producto) {
        contenedorProductos.appendChild(producto);
    });
}
