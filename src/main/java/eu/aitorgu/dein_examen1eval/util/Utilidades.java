package eu.aitorgu.dein_examen1eval.util;

import eu.aitorgu.dein_examen1eval.ProductosApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Clase de utilidades que proporciona métodos para manejar ventanas y alertas
 * en la aplicación.
 */
public class Utilidades {

    /**
     * Abre una nueva ventana con el contenido de un archivo FXML.
     *
     * @param titulo Título de la ventana.
     * @param archivo Nombre del archivo FXML a cargar (sin la extensión).
     */
    public static void abrirVentana(String titulo, String archivo) {
        try {
            // Cargar archivo FXML
            FXMLLoader loader = new FXMLLoader(ProductosApplication.class.getResource("fxml/" + archivo + ".fxml"));
            Parent root = loader.load();

            // Crear la escena y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Cargar el ícono
            Image icon = new Image(ProductosApplication.class.getResourceAsStream("media/img/carrito.png"));
            stage.getIcons().add(icon);

            // Configurar propiedades de la ventana
            stage.setTitle(titulo);
            stage.setResizable(false);
            stage.setScene(scene);

            // Mostrar y esperar a que se cierre la ventana
            stage.showAndWait();

        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML '" + archivo + ".fxml'. Verifique que el archivo exista y sea accesible.");
            e.printStackTrace();
        }
    }

    /**
     * Cierra la ventana actual.
     *
     * @param event Evento que activa el cierre de la ventana.
     */
    public static void cerrarVentana(ActionEvent event) {
        // Obtiene la ventana actual y la cierra
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una alerta con un mensaje específico.
     *
     * @param tipo Tipo de alerta (informativa, error, etc.).
     * @param mensaje Mensaje a mostrar en la alerta.
     * @param title Título de la alerta.
     */
    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje, String title) {
        Alert alert = new Alert(tipo);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una confirmación de borrado al usuario.
     *
     * @param mensaje Mensaje a mostrar en la alerta de confirmación.
     * @return true si el usuario confirma el borrado, false en caso contrario.
     */
    public static boolean mostrarConfirmacionBorrado(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de Borrado");
        alert.setHeaderText("¿Está seguro de que desea eliminar este elemento?");
        alert.setContentText(mensaje);

        ButtonType buttonSi = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonSi, buttonNo);

        // Mostrar la alerta y esperar la respuesta
        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == buttonSi; // Retorna true si el usuario selecciona "Sí"
    }

    /**
     * Muestra una confirmación de edición al usuario.
     *
     * @param mensaje Mensaje a mostrar en la alerta de confirmación.
     * @return true si el usuario confirma la edición, false en caso contrario.
     */
    public static boolean mostrarConfirmacionEditado(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de Borrado");
        alert.setHeaderText("¿Está seguro de que desea editar este elemento?");
        alert.setContentText(mensaje);

        ButtonType buttonSi = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonSi, buttonNo);

        // Mostrar la alerta y esperar la respuesta
        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == buttonSi; // Retorna true si el usuario selecciona "Sí"
    }

    /**
     * Carga el `ResourceBundle` que contiene los mensajes y traducciones de la aplicación.
     *
     * @return El `ResourceBundle` con los mensajes.
     */
}
