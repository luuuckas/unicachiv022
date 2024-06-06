    // JavaScript para manejar las acciones de los botones
    document.querySelectorAll('.editar-btn').forEach(button => {
        button.addEventListener('click', () => {
            // Lógica para editar el puesto
            console.log('Editar');
        });
    });

    document.querySelectorAll('.eliminar-btn').forEach(button => {
        button.addEventListener('click', () => {
            // Lógica para eliminar el puesto
            console.log('Eliminar');
        });
    });