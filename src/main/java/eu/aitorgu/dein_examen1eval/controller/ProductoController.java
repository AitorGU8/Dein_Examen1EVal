package eu.aitorgu.dein_examen1eval.controller;

import eu.aitorgu.dein_examen1eval.model.ModeloProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductoController {
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSelFoto;

    @FXML
    private CheckBox cbDisponible;

    @FXML
    private TableColumn<ModeloProducto, Integer> tcCodigo;

    @FXML
    private TableColumn<ModeloProducto, ?> tcDisponible;

    @FXML
    private TableColumn<ModeloProducto, String> tcNombre;

    @FXML
    private TableColumn<ModeloProducto, Float> tcPrecio;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPrecio;

    @FXML
    private TableView<ModeloProducto> tvProducto;

    @FXML
    void initialize() {
        btnActualizar.setDisable(true);
    }

    @FXML
    void acercaDe(ActionEvent event) {

    }

    @FXML
    void actualizar(ActionEvent event) {

    }

    @FXML
    void crear(ActionEvent event) {

    }

    @FXML
    void limpiar(ActionEvent event) {

    }

    @FXML
    void selFoto(ActionEvent event) {

    }
}
