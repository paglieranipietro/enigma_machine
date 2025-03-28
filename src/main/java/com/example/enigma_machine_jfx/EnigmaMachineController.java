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
        inizializzazioneListenerInputtxt();
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

    private void inizializzazioneListenerInputtxt() {
        inputtxt.textProperty().addListener((observable, oldValue, newValue) -> {
            // Calcola la differenza di lunghezza tra il nuovo valore e il vecchio valore
            int differenza = newValue.length() - oldValue.length();

            if (differenza > 0) {
                // Nuovi caratteri inseriti
                for (int i = oldValue.length(); i < newValue.length(); i++) {
                    char newChar = newValue.charAt(i);
                    if (okCarattere(newChar)) {
                        // Codifica il carattere valido e aggiungilo a outputtxt
                        char transformedChar = enigma.codificaCarattere(newChar);
                        if (transformedChar != '!') {
                            outputtxt.appendText(String.valueOf(transformedChar));

                            // Aggiungi uno spazio ogni 5 caratteri (escludendo gli spazi)
                            String outputTextWithoutSpaces = outputtxt.getText().replace(" ", "");
                            if (outputTextWithoutSpaces.length() % 5 == 0) {
                                outputtxt.appendText(" ");
                            }
                        }
                    }
                }
            } else if (differenza < 0) {
                // Caratteri rimossi (backspace o cancellazione)
                int numCaratteriRimossi = oldValue.length() - newValue.length();

                // Verifica se i caratteri rimossi erano validi
                for (int i = oldValue.length() - 1; i >= newValue.length(); i--) {
                    char removedChar = oldValue.charAt(i);
                    if (okCarattere(removedChar)) {
                        // Ruota i rotori all'indietro per ogni carattere valido rimosso
                        enigma.codificaCarattere('\b');

                        // Rimuovi l'ultimo carattere valido da outputtxt (ignorando gli spazi)
                        String currentOutput = outputtxt.getText();
                        if (currentOutput.length() > 0) {
                            // Trova l'ultimo carattere valido (non spazio)
                            int lastValidCharIndex = currentOutput.length() - 1;
                            while (lastValidCharIndex >= 0 && currentOutput.charAt(lastValidCharIndex) == ' ') {
                                lastValidCharIndex--;
                            }

                            // Se è stato trovato un carattere valido, rimuovilo
                            if (lastValidCharIndex >= 0) {
                                outputtxt.setText(currentOutput.substring(0, lastValidCharIndex));
                            } else {
                                outputtxt.setText(""); // Se non ci sono caratteri validi, svuota outputtxt
                            }
                        }
                    }
                }
            }

            // Aggiorna le visualizzazioni dei rotori
            EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, true, enigma.getPosizioneRotoreSinistro());
            EnigmaMachine.aggiornaRotoreTxt(rotcntxt, true, enigma.getPosizioneRotoreCentrale());
            EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, true, enigma.getPosizioneRotoreDestro());
        });
    }

    private boolean okCarattere(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
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
}