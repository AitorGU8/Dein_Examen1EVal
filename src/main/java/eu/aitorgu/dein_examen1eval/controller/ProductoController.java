package eu.aitorgu.dein_examen1eval.controller;

import eu.aitorgu.dein_examen1eval.dao.DaoProducto;
import eu.aitorgu.dein_examen1eval.model.ModeloProducto;
import eu.aitorgu.dein_examen1eval.util.Utilidades;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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

    private Blob fotoProducto;

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
                tfCodigo.setEditable(false);
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
                }else{
                    ivFoto.setImage(null);
                }


                btnActualizar.setDisable(false); // Habilitar el botón de actualizar
                btnCrear.setDisable(true);
            }
        });
    }

    private void cargarProductos() {
        oList=DaoProducto.cargarProductos();
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
        String msgErr= validarCampos();

        if(msgErr.isEmpty()){
            int dispo=0;

            if(cbDisponible.isSelected()){
                dispo=1;
            }

            ModeloProducto mPro=new ModeloProducto(
                    tfCodigo.getText(),
                    tfNombre.getText(),
                    Float.parseFloat(tfPrecio.getText()),
                    dispo,
                    fotoProducto);
            DaoProducto.actualizarProducto(mPro);
        }
        cargarProductos();
    }

    @FXML
    void crear(ActionEvent event) {
        String msgErr= validarCampos();

        if(msgErr.isEmpty()){
            int dispo=0;

            if(cbDisponible.isSelected()){
                dispo=1;
            }

            ModeloProducto mPro=new ModeloProducto(
                    tfCodigo.getText(),
                    tfNombre.getText(),
                    Float.parseFloat(tfPrecio.getText()),
                    dispo,
                    fotoProducto);
            DaoProducto.crearProducto(mPro);
        }else{
            Utilidades.mostrarAlerta(Alert.AlertType.ERROR,msgErr,"ERROR");
        }
        cargarProductos();
    }

    @FXML
    void limpiar(ActionEvent event) {
        limpiarDatos();
    }



    @FXML
    void selFoto(ActionEvent event) {
        File archivo = seleccionarArchivo();

        if (archivo != null) {

            if (!esTamanoValido(archivo)) {
                Utilidades.mostrarAlerta(Alert.AlertType.ERROR, "La imagen no puede tener un tamaño mayor a 64kb", "ERROR tamaño imagen");
                return;
            }
            try {
                // Carga la imagen y la muestra en el ImageView
                InputStream inputStream = new FileInputStream(archivo);

                ivFoto.setImage(new Image(inputStream));
                fotoProducto=DaoProducto.convertFileToBlob(archivo);
            } catch (IOException e) {
                Utilidades.mostrarAlerta(Alert.AlertType.ERROR, "Error al cargar Imagen","ERROR");
            }
        }
    }
    /**
     * Abre un selector de archivos que permite al usuario seleccionar una imagen (.jpg o .png).
     * @return El archivo seleccionado, o null si no se seleccionó ninguno.
     */
    private File seleccionarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar foto");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Image", "*.jpg", "*.png"));
        return fileChooser.showOpenDialog(null);
    }

    @FXML
    private String validarCampos() {
        StringBuilder mensajeError = new StringBuilder();

        // Validar el código
        if(!tfCodigo.isDisabled()){
            if (tfCodigo.getText().trim().isEmpty()) {
                mensajeError.append("El código no puede estar vacío.\n");
            }
            if(tfCodigo.getText().trim().length()>5){
                mensajeError.append("El código no puede tener mas de 4 caracteres.\n");
            }
        }

        // Validar el nombre
        if (tfNombre.getText().trim().isEmpty()) {
            mensajeError.append("El nombre no puede estar vacío.\n");
        }

        // Validar el precio
        try {
            Float.parseFloat(tfPrecio.getText().trim());  // Intentar convertir el precio a un número
        } catch (NumberFormatException e) {
            mensajeError.append("El precio debe ser un número válido.\n");
        }

        return mensajeError.toString();  // Devuelve todos los errores concatenados

    }
    @FXML
    void eliminar(ActionEvent event) {
        ModeloProducto mpro=tvProducto.getSelectionModel().getSelectedItem();

        if(mpro!=null){
            if(Utilidades.mostrarConfirmacionBorrado(mpro.getCodigo())){
                DaoProducto.borrarProducto(mpro);
                limpiarDatos();
                cargarProductos();
            }else{
                Utilidades.mostrarAlerta(Alert.AlertType.WARNING,"Operacion cancelada","CANCELADA");
            }

        }else {
            Utilidades.mostrarAlerta(Alert.AlertType.ERROR,"No has seleccionado ningun producto","ERROR");
        }
    }

    @FXML
    void verFoto(ActionEvent event) {
        // Obtiene el producto seleccionado de la tabla
        ModeloProducto productoSeleccionado = tvProducto.getSelectionModel().getSelectedItem();

        if (productoSeleccionado != null) {
            // Verifica si el producto tiene imagen
            if (productoSeleccionado.getImagen() != null) {
                // Crea una nueva ventana emergente para mostrar la imagen
                Stage ventanaFoto = new Stage();
                ventanaFoto.setTitle("Imagen del Producto");

                // Crea un ImageView para mostrar la imagen
                ImageView imageView = new ImageView();
                try {
                    // Carga la imagen desde el Blob
                    Image img = new Image(productoSeleccionado.getImagen().getBinaryStream());
                    imageView.setImage(img);
                    imageView.setFitWidth(300);  // Ajusta el tamaño de la imagen
                    imageView.setFitHeight(300); // Ajusta el tamaño de la imagen
                } catch (SQLException e) {
                    Utilidades.mostrarAlerta(Alert.AlertType.ERROR, "Error al cargar la imagen", "ERROR");
                    return;
                }

                // Crea un layout para la ventana emergente
                VBox vbox = new VBox(imageView);
                vbox.setAlignment(Pos.CENTER);

                // Crea la escena y la asocia con la ventana
                Scene scene = new Scene(vbox, 350, 350);
                ventanaFoto.setScene(scene);

                // Muestra la ventana emergente
                ventanaFoto.show();
            } else {
                Utilidades.mostrarAlerta(Alert.AlertType.WARNING, "El producto no tiene imagen", "ADVERTENCIA");
            }
        } else {
            Utilidades.mostrarAlerta(Alert.AlertType.ERROR, "No has seleccionado ningún producto", "ERROR");
        }
    }

    /**
     * Verifica si el tamaño del archivo es válido (máximo 64 KB).
     * @param file El archivo a verificar.
     * @return true si el tamaño es válido, false de lo contrario.
     */
    private boolean esTamanoValido(File file) {
        double kbs = (double) file.length() / 1024;
        return kbs <= 64; // Tamaño máximo de 64 KB
    }


    private void limpiarDatos(){
        // Limpiar los campos de texto
        tfCodigo.clear();  // Limpia el campo de código
        tfNombre.clear();  // Limpia el campo de nombre
        tfPrecio.clear();  // Limpia el campo de precio

        // Desmarcar el CheckBox de disponible
        cbDisponible.setSelected(false);  // Desmarca el CheckBox

        // Limpiar la imagen (restablecer la imagen a null o una imagen predeterminada)
        ivFoto.setImage(null);  // Limpia la imagen

        // Deshabilitar el botón de actualizar
        btnActualizar.setDisable(true);  // Deshabilita el botón de actualizar
        btnCrear.setDisable(false);  // Habilita el botón de crear, si es necesario

        // Deseleccionar cualquier fila seleccionada en la tabla
        tvProducto.getSelectionModel().clearSelection();  // Elimina la selección en la tabla
    }

}
