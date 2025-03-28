package com.example.enigma_machine_jfx;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EnigmaMachineController {
    @FXML
    public TextArea inputtxt;
    @FXML
    public TextArea outputtxt;
    @FXML
    public TextField rotsxtxt;
    @FXML
    public TextField rotcntxt;
    @FXML
    public TextField rotdxtxt;
    @FXML
    public TextField rifltxt;
    @FXML
    public ComboBox rotsxcmb;
    @FXML
    public ComboBox rotcncmb;
    @FXML
    public ComboBox rotdxcmb;
    @FXML
    public ComboBox riflcmb;
    @FXML
    private EnigmaMachine enigma;


    @FXML
    public void initialize() {
        inizializzaMenuRotore();
        inizializzaEnigmaMachine();
        aggiungiListenerComboBox();
    }

    private void inizializzaMenuRotore(){
        riflcmb.getItems().addAll(new String[]{"A", "B", "C"});
        riflcmb.getSelectionModel().select(1);
        rotsxcmb.getItems().addAll(new String[]{"I", "II", "III"});
        rotsxcmb.getSelectionModel().select(2);
        rotcncmb.getItems().addAll(new String[]{"I", "II", "III"});
        rotcncmb.getSelectionModel().select(1);
        rotdxcmb.getItems().addAll(new String[]{"I", "II", "III"});
        rotdxcmb.getSelectionModel().select(0);
    }

    private void inizializzaEnigmaMachine(){
        enigma = new EnigmaMachine(rotdxcmb.getSelectionModel().getSelectedIndex(), rotcncmb.getSelectionModel().getSelectedIndex(), rotsxcmb.getSelectionModel().getSelectedIndex(), riflcmb.getSelectionModel().getSelectedIndex());
        rotsxtxt.setText("" + 'A');
        rotcntxt.setText("" + 'A');
        rotdxtxt.setText("" + 'A');
        rifltxt.setText("" + 'A');
    }

    private void aggiungiListenerComboBox() {
        // Listener per riflcmb
        riflcmb.setOnAction(event -> {
            String nuovoValore = (String) riflcmb.getValue();
            if (!nuovoValore.equals(enigma.getConfRotore("rif"))) {
                inizializzaEnigmaMachine(); // Chiama la funzione se il valore è cambiato
            }
        });
        // Listener per rotsxcmb
        rotsxcmb.setOnAction(event -> {
            String nuovoValore = (String) rotsxcmb.getValue();
            if (!nuovoValore.equals(enigma.getConfRotore("sx"))) {
                inizializzaEnigmaMachine(); // Chiama la funzione se il valore è cambiato
            }
        });

        // Listener per rotcncmb
        rotcncmb.setOnAction(event -> {
            String nuovoValore = (String) rotcncmb.getValue();
            if (!nuovoValore.equals(enigma.getConfRotore("cn"))) {
                inizializzaEnigmaMachine(); // Chiama la funzione se il valore è cambiato
            }
        });

        // Listener per rotdxcmb
        rotdxcmb.setOnAction(event -> {
            String nuovoValore = (String) rotdxcmb.getValue();
            if (!nuovoValore.equals(enigma.getConfRotore("dx"))) {
                inizializzaEnigmaMachine(); // Chiama la funzione se il valore è cambiato
            }
        });
    }

    public void onPiusxBtnClick(){
        enigma.ruotaRotoreSinistro(true, true);
        EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, true, -1);
    }

    public void onMenosxBtnClick(){
        enigma.ruotaRotoreSinistro(false, true);
        EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, false, -1);
    }

    public void onPiucnBtnClick(){
        enigma.ruotaRotoreCentrale(true, true);
        EnigmaMachine.aggiornaRotoreTxt(rotcntxt, true, -1);
    }

    public void onMenocnBtnClick(){
        enigma.ruotaRotoreCentrale(false, true);
        EnigmaMachine.aggiornaRotoreTxt(rotcntxt, false, -1);
    }

    public void onPiudxBtnClick(){
        enigma.ruotaRotoreDestro(true, true);
        EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, true, -1);
    }

    public void onMenodxBtnClick(){
        enigma.ruotaRotoreDestro(false, true);
        EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, false, -1);
    }
}