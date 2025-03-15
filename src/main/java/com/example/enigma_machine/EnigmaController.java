package com.example.enigma_machine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class EnigmaController {

    @FXML
    public TextArea inputtxt;
    @FXML
    public TextArea outputtxt;
    @FXML
    public Button codificabtn;

    public void onCodificaBtnClick() {
        EnigmaMachine enigma = new EnigmaMachine(1, 2, 3, 0, 0, 0, 'A', 'A', 'A');
        String input = inputtxt.getText();
        String output = enigma.codificaFrase(input);
        outputtxt.setText(output);
    }
}