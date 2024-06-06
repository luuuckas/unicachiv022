-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-06-2024 a las 00:22:05
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_unicachi`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL,
  `nombreCategoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `cantidadProductos` int(11) DEFAULT NULL,
  `precioPromedio` double DEFAULT NULL,
  `popularidad` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `nombreCategoria`, `descripcion`, `cantidadProductos`, `precioPromedio`, `popularidad`) VALUES
(1, 'Electrónicos', 'Productos electrónicos para el hogar', 100, 500, 'Alta'),
(2, 'Ropa', 'Ropa de moda para todas las edades', 200, 50, 'Media'),
(3, 'Electrodomésticos', 'Electrodomésticos para el hogar', 80, 700, 'Baja'),
(4, 'Juguetes', 'Juguetes para niños de todas las edades', 150, 20, 'Alta'),
(5, 'Muebles', 'Muebles para el hogar de alta calidad', 120, 300, 'Alta'),
(6, 'Electrodomésticos de Cocina', 'Electrodomésticos para la cocina moderna', 90, 400, 'Media'),
(7, 'Herramientas', 'Herramientas para bricolaje y construcción', 180, 80, 'Alta'),
(8, 'Artículos de Decoración', 'Artículos decorativos para el hogar', 250, 40, 'Baja'),
(9, 'Accesorios de Moda', 'Accesorios de moda para complementar tu estilo', 300, 30, 'Media');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `IdProducto` int(11) NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `Popularidad` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`IdProducto`, `Descripcion`, `Precio`, `Cantidad`, `Popularidad`) VALUES
(1, 'Televisor LED 50 pulgadas', 599.99, 30, 'Alta'),
(2, 'Chaqueta de cuero', 149.99, 50, 'Media'),
(3, 'Refrigerador de acero inoxidable', 899.99, 20, 'Alta'),
(4, 'Muñeca articulada con accesorios', 29.99, 100, 'Alta'),
(5, 'Smartphone Android 5G', 399.99, 80, 'Alta'),
(6, 'Laptop de última generación', 1799.99, 15, 'Alta'),
(7, 'Mesa de comedor de roble macizo', 699.99, 25, 'Media'),
(8, 'Auriculares inalámbricos con cancelación de ruido', 129.99, 40, 'Alta'),
(9, 'Impresora multifuncional a color', 249.99, 30, 'Alta'),
(10, 'Mesa de centro de vidrio templado', 149.99, 20, 'Media'),
(11, 'Reloj inteligente con monitor de actividad', 79.99, 60, 'Alta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puestos`
--

CREATE TABLE `puestos` (
  `idPuesto` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `producto` varchar(255) DEFAULT NULL,
  `dueño` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `puestos`
--

INSERT INTO `puestos` (`idPuesto`, `categoria`, `producto`, `dueño`, `estado`) VALUES
(1, 'Carnes', 'Pollo', 'Laura', 1),
(2, 'Dulces', 'Chocolate', 'Ana', 0),
(3, 'Dulces', 'ChocolateBlanco', 'Maria', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IdProducto`);

--
-- Indices de la tabla `puestos`
--
ALTER TABLE `puestos`
  ADD PRIMARY KEY (`idPuesto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `puestos`
--
ALTER TABLE `puestos`
  MODIFY `idPuesto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
