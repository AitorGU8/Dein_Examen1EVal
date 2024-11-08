package eu.aitorgu.dein_examen1eval.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.swing.plaf.basic.BasicListUI;
import java.sql.Blob;

public class ModeloProducto {
    private SimpleStringProperty codigo;
    private SimpleStringProperty nombre;
    private SimpleFloatProperty precio;
    private SimpleIntegerProperty disponible;
    private Blob imagen;

    public ModeloProducto(String codigo, String nombre, float precio, int disponible, Blob imagen) {
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleFloatProperty(precio);
        this.disponible =  new SimpleIntegerProperty(disponible);
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "ModeloProducto{" +
                "codigo=" + codigo +
                ", nombre=" + nombre +
                ", precio=" + precio +
                ", disponible=" + disponible +
                ", imagen=" + imagen +
                '}';
    }

    public String getCodigo() {
        return codigo.get();
    }

    public SimpleStringProperty codigoProperty() {
        return codigo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public float getPrecio() {
        return precio.get();
    }

    public SimpleFloatProperty precioProperty() {
        return precio;
    }

    public int getDisponible() {
        return disponible.get();
    }

    public SimpleIntegerProperty disponibleProperty() {
        return disponible;
    }

    public Blob getImagen() {
        return imagen;
    }
}
