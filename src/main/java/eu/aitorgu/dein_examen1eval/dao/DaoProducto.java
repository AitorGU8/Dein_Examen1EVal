package eu.aitorgu.dein_examen1eval.dao;

import eu.aitorgu.dein_examen1eval.bbdd.ConexionBBDD;
import eu.aitorgu.dein_examen1eval.model.ModeloProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class DaoProducto {
    private static ConexionBBDD conexion;

    // Método para crear un producto en la tabla 'productos' usando un objeto ModeloProducto
    public static void crearProducto(ModeloProducto producto) {
        try {
            conexion = new ConexionBBDD();
            System.out.println(producto.toString());
            String consulta = "INSERT INTO productos (codigo,nombre, precio, disponible, imagen) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setString(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setFloat(3, producto.getPrecio());
            pstmt.setInt(4, producto.getDisponible());
            pstmt.setBlob(5, producto.getImagen());
            pstmt.executeUpdate();
            System.out.println("Producto creado exitosamente.");
            conexion.CloseConexion();
        } catch (SQLException e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
        }
    }
    // Método para actualizar un producto existente en la tabla 'productos' usando un objeto ModeloProducto
    public static void actualizarProducto(ModeloProducto producto) {
        try {
            conexion = new ConexionBBDD();
            String consulta = "UPDATE productos SET nombre = ?, precio = ?, disponible = ?, imagen = ? WHERE codigo = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setString(1, producto.getNombre());
            pstmt.setFloat(2, producto.getPrecio());
            pstmt.setInt(3, producto.getDisponible());
            pstmt.setBlob(4, producto.getImagen());
            pstmt.setString(5, producto.getCodigo());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con el código especificado.");
            }
            conexion.CloseConexion();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
    }
    // Método para borrar un producto de la tabla 'productos' usando un objeto ModeloProducto
    public static void borrarProducto(ModeloProducto producto) {
        try {
            conexion = new ConexionBBDD();
            String consulta = "DELETE FROM productos WHERE codigo = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setString(1, producto.getCodigo());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto borrado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con el código especificado.");
            }
            conexion.CloseConexion();
        } catch (SQLException e) {
            System.err.println("Error al borrar el producto: " + e.getMessage());
        }
    }

    // Método para obtener todos los productos de la tabla 'productos'
    public static ObservableList<ModeloProducto> cargarProductos() {
        ObservableList<ModeloProducto> listaProductos = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBBDD();
            String consulta = "SELECT codigo, nombre, precio, disponible, imagen FROM productos";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                int disponible = rs.getInt("disponible");
                Blob imagen = rs.getBlob("imagen");

                // Creación de un objeto ModeloProducto con los datos recuperados
                ModeloProducto producto = new ModeloProducto(codigo, nombre, precio, disponible, imagen);
                listaProductos.add(producto);
            }
            rs.close();
            conexion.CloseConexion();
        } catch (SQLException e) {
            System.err.println("Error al cargar los productos: " + e.getMessage());
        }
        return listaProductos;
    }
    /**
     * Convierte un archivo de imagen en un objeto `Blob` para ser almacenado en la base de datos.
     *
     * @param file El archivo de imagen a convertir.
     * @return El objeto `Blob` que representa la imagen del archivo.
     */
    public static Blob convertFileToBlob(File file) {
        Blob blob = null;
        try {
            conexion = new ConexionBBDD();
            Connection conn = conexion.getConexion();
            blob = conn.createBlob();
            try (FileInputStream inputStream = new FileInputStream(file);
                 var outputStream = blob.setBinaryStream(1)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            conexion.CloseConexion();
        } catch (SQLException | IOException e) {
            System.err.println("Error al convertir el archivo a Blob: " + e.getMessage());
        }
        return blob;
    }

}
