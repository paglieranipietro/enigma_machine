module com.example.enigma_machine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.enigma_machine to javafx.fxml;
    exports com.example.enigma_machine;
}