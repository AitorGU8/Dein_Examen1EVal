module eu.aitorgu.dein_examen1eval {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.aitorgu.dein_examen1eval to javafx.fxml;
    exports eu.aitorgu.dein_examen1eval;
}