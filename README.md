# Proyecto Gestión de Productos

Este proyecto es una aplicación de escritorio en Java que permite gestionar productos, visualizando y modificando información como código, nombre, precio, disponibilidad y foto del producto. Utiliza JavaFX para la interfaz gráfica, junto con acceso a base de datos MySQL.

## Estructura del Proyecto

La estructura del proyecto sigue un patrón estándar para aplicaciones Java con JavaFX. A continuación se detalla la estructura de directorios y archivos más relevantes:

### Directorio `src/main/java`

- **`eu/aitorgu/dein_examen1eval/bbdd/ConexionBBDD.java`**: Clase encargada de gestionar la conexión a la base de datos MySQL.

- **`eu/aitorgu/dein_examen1eval/controller/ProductoController.java`**: Controlador principal de la vista de productos. Gestiona las acciones del usuario, como crear, actualizar y eliminar productos.

- **`eu/aitorgu/dein_examen1eval/dao/DaoProducto.java`**: DAO que maneja las operaciones CRUD (crear, leer, actualizar y eliminar) sobre los productos en la base de datos.

- **`eu/aitorgu/dein_examen1eval/model/ModeloProducto.java`**: Modelo que representa los datos de un producto.

- **`eu/aitorgu/dein_examen1eval/ProductosApplication.java`**: Clase principal de la aplicación, que contiene el método `main` y lanza la interfaz gráfica.

- **`eu/aitorgu/dein_examen1eval/util/Utilidades.java`**: Contiene métodos utilitarios para mostrar alertas, manejar errores y realizar otras tareas auxiliares en la aplicación.

- **`eu/aitorgu/dein_examen1eval/HelloController.java`**: Controlador asociado a la vista principal, posiblemente para funcionalidades básicas como inicio de sesión o información.

### Directorio `src/main/resources`

- **`eu/aitorgu/dein_examen1eval/configuration.properties`**: Archivo de configuración que contiene parámetros de la base de datos y otras configuraciones necesarias para la aplicación.

- **`eu/aitorgu/dein_examen1eval/css/estilos.css`**: Archivo de estilos CSS para personalizar la apariencia de la interfaz de usuario.

- **`eu/aitorgu/dein_examen1eval/fxml/principal.fxml`**: Archivo FXML que define la estructura y componentes de la interfaz principal de la aplicación, como los botones, campos de texto y tablas.

- **`eu/aitorgu/dein_examen1eval/media/img/carrito.png`**: Imagen usada en la aplicación, probablemente para representar un carrito de compras o un ícono en la interfaz.

- **`eu/aitorgu/dein_examen1eval/SQL/examen1.sql`**: Archivo SQL para crear la estructura de la base de datos necesaria para la aplicación.

## Funcionalidades

- **Gestión de productos**: Se pueden crear, actualizar y eliminar productos en la base de datos.

- **Visualización de fotos**: Los productos pueden tener imágenes asociadas, que se muestran en la interfaz de usuario.

- **Validación de datos**: Los campos de texto para el código, nombre y precio están validados antes de ser enviados a la base de datos.

- **Interfaz gráfica**: La aplicación usa JavaFX para crear una interfaz de usuario moderna y fácil de usar.

