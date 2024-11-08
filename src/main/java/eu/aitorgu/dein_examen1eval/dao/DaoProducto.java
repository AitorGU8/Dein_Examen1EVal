package eu.aitorgu.dein_examen1eval.dao;

import eu.aitorgu.dein_examen1eval.bbdd.ConexionBBDD;
import eu.aitorgu.dein_examen1eval.model.ModeloProducto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoProducto {
    private static ConexionBBDD conexion;

    // Método para crear un producto en la tabla 'productos' usando un objeto ModeloProducto
    public static void crearProducto(ModeloProducto producto) {
        try {
            conexion = new ConexionBBDD();
            String consulta = "INSERT INTO productos (nombre, precio, disponible, imagen) VALUES (, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setString(1, producto.getNombre());
            pstmt.setFloat(2, producto.getPrecio());
            pstmt.setInt(3, producto.getDisponible());
            pstmt.setBlob(4, producto.getImagen());
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
            pstmt.setInt(5, producto.getCodigo());
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
            pstmt.setInt(1, producto.getCodigo());
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


}
