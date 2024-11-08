module eu.aitorgu.dein_examen1eval {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eu.aitorgu.dein_examen1eval to javafx.fxml;
    exports eu.aitorgu.dein_examen1eval;
    exports eu.aitorgu.dein_examen1eval.model;
    opens eu.aitorgu.dein_examen1eval.model to javafx.fxml;
    exports eu.aitorgu.dein_examen1eval.controller;
    opens eu.aitorgu.dein_examen1eval.controller to javafx.fxml;
}