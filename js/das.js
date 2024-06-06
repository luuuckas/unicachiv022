window.onload = function() {
    // Obtiene todos los enlaces del menú
    var links = document.querySelectorAll('.sidebar ul li a');

    // Agrega un evento de clic a cada enlace del menú
    links.forEach(function(link) {
        link.addEventListener('click', function(event) {
            // Evita que el enlace redirija
            event.preventDefault();

            // Obtiene el hash del enlace (el ID de la sección a la que se debe desplazar)
            var targetId = this.getAttribute('href').substring(1);

            // Oculta todas las secciones
            var sections = document.querySelectorAll('.content .section');
            sections.forEach(function(section) {
                section.style.display = 'none';
            });

            // Muestra la sección correspondiente al enlace clicado
            var targetSection = document.getElementById(targetId);
            if (targetSection) {
                targetSection.style.display = 'block';
            }
        });
    });
};
