package eu.aitorgu.dein_examen1eval.bbdd;

import eu.aitorgu.dein_examen1eval.util.Propiedades;
import eu.aitorgu.dein_examen1eval.util.Utilidades;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase responsable de la conexión a la base de datos.
 * Esta clase establece la conexión con la base de datos MySQL y proporciona métodos
 * para obtener y cerrar la conexión.
 *
 * @author aitor
 * @version $Id: $Id
 */
public class ConexionBBDD {
    private final Connection conexion;

    /**
     * Constructor de la clase {@code ConexionBBDD}.
     * Inicializa una nueva conexión a la base de datos utilizando las propiedades
     * definidas externamente.
     *
     * @throws SQLException si ocurre un error al intentar establecer la conexión con la base de datos.
     */
    public ConexionBBDD() {
        // los parámetros de la conexión leídos desde fuera
        String user = Propiedades.getValor("user");
        String password = Propiedades.getValor("password");

        // las propiedades de la conexión
        Properties connConfig = new Properties();
        connConfig.setProperty("user", user);
        connConfig.setProperty("password", password);

        // la conexión en sí
        try {
            conexion = DriverManager.getConnection(Propiedades.getValor("conex"), connConfig);
            conexion.setAutoCommit(true);
            DatabaseMetaData databaseMetaData = conexion.getMetaData();

             //Información de depuración (puede ser comentada o eliminada en producción)
//             System.out.println();
//             System.out.println("--- Datos de conexión ------------------------------------------");
//             System.out.printf("Base de datos: %s%n", databaseMetaData.getDatabaseProductName());
//             System.out.printf("  Versión: %s%n", databaseMetaData.getDatabaseProductVersion());
//             System.out.printf("Driver: %s%n", databaseMetaData.getDriverName());
//             System.out.printf("  Versión: %s%n", databaseMetaData.getDriverVersion());
//             System.out.println("----------------------------------------------------------------");
//             System.out.println();
//            conexion.setAutoCommit(true);
        } catch (SQLException e) {
            Utilidades.mostrarAlerta(Alert.AlertType.ERROR,"Error al conectar con la base de datos","ERROR Base Datos");
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene la conexión establecida con la base de datos.
     *
     * @return la conexión a la base de datos.
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Cierra la conexión con la base de datos.
     *
     * @return la conexión cerrada.
     * @throws SQLException si ocurre un error al intentar cerrar la conexión.
     */
    public Connection CloseConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }

    /**
     * Método principal para probar la conexión a la base de datos.
     * Este método crea una instancia de {@code ConexionBBDD} y cierra la conexión.
     *
     * @param args los argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        ConexionBBDD conexion = new ConexionBBDD();
        conexion.CloseConexion();
    }
}
