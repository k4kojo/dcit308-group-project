module com.example.dcit308group8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.dcit308group8 to javafx.fxml;
    exports com.example.dcit308group8;
    exports com.example.dcit308group8.scene_controller;
    opens com.example.dcit308group8.scene_controller to javafx.fxml;
}