# API REST - Gestión de Ventas y Stock para Comercio de Electrónica

Proyecto integrador final del curso **"Desarrollo de APIs en Java con Spring Boot"** de TodoCode Academy.

##  Objetivo

Desarrollar una API REST robusta que permita gestionar el stock y registrar ventas de un comercio de artículos electrónicos.  
La aplicación debe servir de backend para futuras aplicaciones web y mobile, facilitando el trabajo de la dueña del comercio y sus empleados.

##  Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Lombok
- Maven
- Postman (para pruebas)
- JUnit & Mockito (tests)


##  Funcionalidades

-  ABM de productos (crear, listar, modificar, eliminar)
-  Registro de ventas con múltiples productos
-  Cálculo automático del total de cada venta
-  Gestión de clientes
-  Descuento automático por cliente recurrente
-  Validaciones de stock insuficiente
-  Reportes de ventas y stock
-  Manejo de errores y excepciones centralizado

## 📱 Escenario de uso

La API fue diseñada para ser consumida por:

- Una aplicación web (frontend a cargo de otro desarrollador)
- Una futura aplicación mobile

##  Estructura del Proyecto

src
├── controller
├── dto
├── entity
├── repository
├── service


##  Endpoints Principales

  Productos
- `POST /productos` → Crea producto  
- `GET /productos` → Lista productos
- `GET /productos/id` → Obtiene detalles de un producto por su código
- `GET /productos/productos-venta/cod_venta` → Lista productos pertenecientes a una venta
- `GET /productos/menos-cinco-unidades` → Lista productos con stock de menos de 5 unidades
- `PUT /productos/id` → Modifica datos del producto
- `PATCH /productos/stock` → Agrega stock a un producto
  
  Clientes
- `POST /clientes` → Registra cliente
- `GET /clientes` → Obtene listado de clientes
- `PUT /clientes/id` → Edita cliente
  
  Ventas
- `POST /ventas` → Registra una venta de a un producto (ej: si el cliente quiere comprar 4 unidades pero solo hay 3 diponibles, le permite comprar 3)
- `POST /ventas/complete` → Registra una venta (ej: la venta se realiza solo si hay la cantidad de stock que el cliente adquiir )
- `GET /ventas` → Lista ventas  
- `GET /ventas/cod_venta` → Obtiene detalles de una venta 
- `GET /ventas/fecha` → Obtiene la sumatoria, el monto, y cantidad de ventas de un dia especifico
- `GET /ventas/mayor-venta` → Obtiene la venta con major monto
- `PUT /ventas/cod_venta` → Modifica datos de una venta seleccionada

## Estado actual del proyecto
-  Completado : Cumple con todos los requerimientos y se agregaron otros propios
-  Por complear : pruebas unitarias y de componentes.

## Autor

Martín Díaz
Estudiante de Análisis de Sistemas | Backend Developer


