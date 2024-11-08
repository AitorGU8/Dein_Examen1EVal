package eu.aitorgu.dein_examen1eval;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Clase principal de la aplicación que gestiona la interfaz gráfica y la configuración de idioma.
 * Hereda de la clase Application de JavaFX y se encarga de iniciar la aplicación.
 */
public class ProductosApplication extends Application {

        /**
         * Método que inicia la aplicación y configura la ventana principal.
         *
         * @param stage El escenario principal de la aplicación.
         * @throws IOException Si ocurre un error al cargar el archivo FXML.
         */
        @Override
        public void start(Stage stage) throws IOException {

            // Cargar el archivo FXML con el ResourceBundle
            FXMLLoader fxmlLoader = new FXMLLoader(ProductosApplication.class.getResource("fxml/principal.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Productos");
            stage.setHeight(600);
            stage.setWidth(650);

            // Cargar el ícono
            Image icon = new Image(getClass().getResourceAsStream("media/img/carrito.png"));
            stage.getIcons().add(icon);

            // Vincular el CSS
            scene.getStylesheets().add(getClass().getResource("css/estilos.css").toExternalForm());

            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }


        /**
         * Método principal que inicia la aplicación JavaFX.
         *
         * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
         */

    public static void main(String[] args) {
        launch();
    }
}