package com.example.enigma_machine_jfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EnigmaMachineController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}