package eu.aitorgu.dein_examen1eval.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Clase para leer las propiedades del sistema, como URLs, idioma instalado, directorios, etc.
 * Esta clase carga un archivo de propiedades al inicializarse y proporciona un método para obtener
 * valores específicos a partir de claves definidas en ese archivo.
 *
 * @author aitor
 * @version $Id: $Id
 */
public class Propiedades {

    private static final Properties props = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("src/main/resources/eu/aitorgu/dein_examen1eval/configuration.properties")) {
            // Carga el archivo de propiedades desde la ruta especificada
            props.load(input);
        } catch (Exception e) {
            // Manejo de excepciones, imprimiendo el mensaje de error
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene el valor asociado a una clave desde el archivo de propiedades.
     *
     * @param clave La clave cuyo valor se desea obtener.
     * @return El valor asociado a la clave.
     * @throws RuntimeException Si el archivo de configuración no existe o la clave no tiene un valor asociado.
     */
    public static String getValor(String clave) {
        String valor = props.getProperty(clave);
        if (valor != null) {
            return valor;
        }
        throw new RuntimeException("No he logrado leer esa clave en concreto: " + clave);
    }
}
