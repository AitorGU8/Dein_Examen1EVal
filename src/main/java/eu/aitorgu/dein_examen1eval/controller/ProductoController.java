package eu.aitorgu.dein_examen1eval.controller;

import eu.aitorgu.dein_examen1eval.dao.DaoProducto;
import eu.aitorgu.dein_examen1eval.model.ModeloProducto;
import eu.aitorgu.dein_examen1eval.util.Utilidades;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;


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
    private ImageView ivFoto;

    @FXML
    private CheckBox cbDisponible;

    @FXML
    private TableColumn<ModeloProducto, String> tcCodigo;

    @FXML
    private TableColumn<ModeloProducto, Integer> tcDisponible;

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

    private ObservableList<ModeloProducto> oList=DaoProducto.cargarProductos();

    @FXML
    void initialize() {
        oList = DaoProducto.cargarProductos();
        btnActualizar.setDisable(true);
        cargarProductos();

        // Listener para detectar selección de fila en la tabla
        tvProducto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Llenar los campos con los datos del producto seleccionado
                tfCodigo.setText(String.valueOf(newValue.getCodigo())); // Código
                tfNombre.setText(newValue.getNombre()); // Nombre
                tfPrecio.setText(String.valueOf(newValue.getPrecio())); // Precio
                cbDisponible.setSelected(newValue.getDisponible() == 1); // Disponible (true si 1, false si 0)
                if(newValue.getImagen()!=null){
                    try {
                        Image img= new Image(newValue.getImagen().getBinaryStream());
                        ivFoto.setImage(img);

                    } catch (SQLException e) {
                        Utilidades.mostrarAlerta(Alert.AlertType.ERROR, "Error al cargar la imagen","ERROR");
                        ivFoto.setImage(null);
                    }
                }


                btnActualizar.setDisable(false); // Habilitar el botón de actualizar
                btnCrear.setDisable(true);
            }
        });
    }

    private void cargarProductos() {
        // Configurar las celdas de la tabla para mostrar correctamente los datos
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Configurar la columna 'disponible' para que muestre un CheckBox
        tcDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        tcDisponible.setCellFactory(column -> new TableCell<ModeloProducto, Integer>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item == 1); // Si el valor es 1, el CheckBox se marca
                    checkBox.setDisable(true); // Deshabilitar el CheckBox para que no sea editable
                    setGraphic(checkBox); // Asignamos el CheckBox a la celda
                }
            }
        });

        tcDisponible.setEditable(false); // Asegura que la columna no sea editable

        // Cargar la lista de productos en la tabla
        tvProducto.setItems(oList);
    }







    @FXML
    void acercaDe(ActionEvent event) {
        String msg="Gestión de prodcutos 1.0\nAutor: Aitor Gamarra";
        Utilidades.mostrarAlerta(Alert.AlertType.INFORMATION,msg,"INFO");
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
