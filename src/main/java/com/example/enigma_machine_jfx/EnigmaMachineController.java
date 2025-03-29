package com.example.enigma_machine_jfx;

import com.example.enigma_machine_jfx.componenti.Lampadina;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

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
    private GridPane tastierabtns;
    @FXML
    private GridPane tastieraLampadine;
    @FXML
    private EnigmaMachine enigma;
    @FXML
    private Button[] tasti;
    @FXML
    private Lampadina[] lampadine;


    @FXML
    public void initialize() {
        inizializzaMenuRotori();
        inizializzaEnigmaMachine();
        inizializzaLampadine();
        inizializzaTastiera();
        inizializzaListenerInputtxt();
        inizializzaListenerRotoriCmb();
    }

    private void inizializzaMenuRotori(){
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
        if (enigma.PC == 0){
            enigma.ruotaRotoreSinistro(true, true);
            EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, true, -1);
        }

    }

    public void onMenosxBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreSinistro(false, true);
            EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, false, -1);
        }
    }

    public void onPiucnBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreCentrale(true, true);
            EnigmaMachine.aggiornaRotoreTxt(rotcntxt, true, -1);
        }
    }

    public void onMenocnBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreCentrale(false, true);
            EnigmaMachine.aggiornaRotoreTxt(rotcntxt, false, -1);
        }
    }

    public void onPiudxBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreDestro(true, true);
            EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, true, -1);
        }
    }

    public void onMenodxBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreDestro(false, true);
            EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, false, -1);
        }
    }

    private void inizializzaLampadine() {
        tastieraLampadine.setHgap(10);
        tastieraLampadine.setVgap(5);
        tastieraLampadine.setAlignment(Pos.CENTER);

        lampadine = new Lampadina[27];

        // Layout QWERTY per le lampadine
        String[] righeQWERTY = {
                "QWERTZUIO",  // Prima riga QWERTY (con Z al posto di Y per layout tedesco)
                "ASDFGHJK",   // Seconda riga
                "PYXCVBNML"   // Terza riga (con Y e X invertiti rispetto a layout moderno)
        };

        for (int i = 0; i < righeQWERTY.length; i++) {
            String riga = righeQWERTY[i];
            for (int j = 0; j < riga.length(); j++) {
                char lettera = riga.charAt(j);
                lampadine[lettera - 'A'] = new Lampadina(lettera);
                tastieraLampadine.add(lampadine[lettera - 'A'], j, i);
            }
        }
        // Aggiungi tasto backspace
        tastieraLampadine.add(lampadine['T' - 'A'] = new Lampadina('⌫'), 8, 1);
    }

    private void inizializzaTastiera() {
        tasti = new Button[27];
        tastierabtns.setVgap(5);
        tastierabtns.setHgap(10);

        // Layout QWERTY per la tastiera
        String[] righeQWERTY = {
                "QWERTZUIO",  // Prima riga QWERTY (con Z al posto di Y per layout tedesco)
                "ASDFGHJK",   // Seconda riga
                "PYXCVBNML"   // Terza riga (con Y e X invertiti rispetto a layout moderno)
        };

        // Creazione tasti lettere
        for (int i = 0; i < righeQWERTY.length; i++) {
            String riga = righeQWERTY[i];
            for (int j = 0; j < riga.length(); j++) {
                char lettera = riga.charAt(j);
                tasti[i * 9 + j] = new Button("" + lettera);
                tasti[i * 9 + j].setFont(Font.font(18));
                tasti[i * 9 + j].setPrefWidth(80);

                final char letteraF = lettera;
                tasti[i * 9 + j].setOnAction(e -> {
                    gestisciTasto(letteraF);
                });
                tastierabtns.add(tasti[i * 9 + j], j, i);
            }
        }

        // Aggiungi tasto spazio
        Button tastoSpazio = new Button("\u2423");
        tastoSpazio.setFont(Font.font(12));
        tastoSpazio.setPrefWidth(260);
        tastoSpazio.setOnAction(e -> gestisciTasto(' '));
        tastierabtns.add(tastoSpazio, 3, 3, 5, 1);

        // Aggiungi tasto backspace
        tasti['T' - 'A'] = new Button("⌫");
        tasti['T' - 'A'].setFont(Font.font(18));
        tasti['T' - 'A'].setPrefWidth(80);
        tasti['T' - 'A'].setOnAction(e -> gestisciTasto('\b'));
        tastierabtns.add(tasti['T' - 'A'], 8, 1);
    }

    public void gestisciTasto(char lettera) {
        if (lettera == '\b') {
            if (inputtxt.getText().length() > 0) {
                inputtxt.setText(inputtxt.getText().substring(0, inputtxt.getText().length() - 1));
            }
        }else inputtxt.appendText("" + lettera);

    }

    private void inizializzaListenerInputtxt() {
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
                            outputtxt.appendText(String.valueOf(carattereMaiuscolo(newChar) ? transformedChar : Character.toLowerCase(transformedChar)));

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

    private boolean carattereMaiuscolo(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    private void inizializzaListenerRotoriCmb() {
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