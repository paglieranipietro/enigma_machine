module com.example.enigma_machine_jfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.enigma_machine_jfx to javafx.fxml;
    exports com.example.enigma_machine_jfx;
}