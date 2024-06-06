function contarProductos() {
    // Obtener todos los productos
    var productos = document.querySelectorAll(".producto");

    // Obtener el número total de productos
    var totalProductos = productos.length;

    // Mostrar el resultado en el elemento con id "resultado-busqueda"
    var resultado = document.getElementById("resultado-busqueda");
    resultado.textContent = "Resultado de búsqueda: " + totalProductos + " productos";
}

// Llamar a la función cuando se carga la página
window.onload = contarProductos;
