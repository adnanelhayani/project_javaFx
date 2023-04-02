module com.example.project_esalaf {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.project_esalaf to javafx.fxml;
    exports com.example.project_esalaf;
}