 function mostrarCuadro() {
        document.getElementById("fondo-negro").style.display = "block";
        document.getElementById("cuadro").style.display = "block";
        // Desactivar eventos de clic en el fondo oscuro
        document.getElementById("fondo-negro").addEventListener("click", bloquearClic);
    }

    function cerrarCuadro() {
        document.getElementById("fondo-negro").style.display = "none";
        document.getElementById("cuadro").style.display = "none";
        // Reactivar eventos de clic en el fondo oscuro
        document.getElementById("fondo-negro").removeEventListener("click", bloquearClic);
    }

    function bloquearClic(event) {
        event.stopPropagation();
    }