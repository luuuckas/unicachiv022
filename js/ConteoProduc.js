function agregarProducto() {
    var cantidad = document.getElementById('cantidad').value;
    // Aquí puedes agregar la lógica para agregar el producto al carrito con la cantidad seleccionada
    alert("Se agregarán " + cantidad + " unidades al carrito.");
}

function sumarCantidad() {
    var cantidadInput = document.getElementById('cantidad');
    cantidadInput.value = parseInt(cantidadInput.value) + 1;
}

function restarCantidad() {
    var cantidadInput = document.getElementById('cantidad');
    if (parseInt(cantidadInput.value) > 1) {
        cantidadInput.value = parseInt(cantidadInput.value) - 1;
    }
}
