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

/**
 * La classe EnigmaMachineController gestisce l'interfaccia utente della macchina Enigma.
 * Contiene i metodi per inizializzare i componenti dell'interfaccia e gestire gli eventi.
 */
public class EnigmaMachineController {
    @FXML
    public TextArea inputtxt; // Area di testo per l'input
    @FXML
    public TextArea outputtxt; // Area di testo per l'output
    @FXML
    public TextField rotsxtxt; // Campo di testo per visualizzare la posizione del rotore sinistro
    @FXML
    public TextField rotcntxt; // Campo di testo per visualizzare la posizione del rotore centrale
    @FXML
    public TextField rotdxtxt; // Campo di testo per visualizzare la posizione del rotore destro
    @FXML
    public TextField rifltxt; // Campo di testo per visualizzare la posizione del riflessore
    @FXML
    public ComboBox rotsxcmb; // ComboBox per selezionare la configurazione rotore sinistro
    @FXML
    public ComboBox rotcncmb; // ComboBox per selezionare la configurazione rotore centrale
    @FXML
    public ComboBox rotdxcmb; // ComboBox per selezionare la configurazione rotore destro
    @FXML
    public ComboBox riflcmb; // ComboBox per selezionare la configurazione riflessore
    @FXML
    private GridPane tastierabtns; // Griglia per i pulsanti della tastiera
    @FXML
    private GridPane tastieraLampadine; // Griglia per le lampadine
    @FXML
    private GridPane pannelloscambiatore; // Griglia per il pannello scambiatore
    @FXML
    private Button pannellobtn; // Pulsante per attivare/disattivare il pannello scambiatore
    @FXML
    private EnigmaMachine enigma; // Istanza della macchina Enigma
    @FXML
    private Button[] tasti; // Array di pulsanti della tastiera
    @FXML
    private Lampadina[] lampadine; // Array di lampadine
    @FXML
    private TextField[] scambiatori; // Array di campi di testo per gli scambiatori

    /**
     * Metodo di inizializzazione chiamato automaticamente dopo il caricamento del file FXML.
     */
    @FXML
    public void initialize() {
        inputtxt.setFont(new Font("Open Sans", 16));
        outputtxt.setFont(new Font("Open Sans", 16));
        inizializzaMenuRotori();
        inizializzaPannelloScambiatore();
        inizializzaEnigmaMachine();
        inizializzaLampadine();
        inizializzaTastiera();
        inizializzaListenerInputtxt();
        inizializzaListenerRotoriCmb();
    }

    /**
     * Inizializza il menu dei rotori, inserisce le configurazioni possibili e li imposta su quelle predefinite.
     */
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

    /**
     * Inizializza il pannello scambiatore, crea 6 campi di testo in cui inserire le coppie da scambiare.
     * Aggiunge il listener per gestire correttamente l'input del campo si testo e il pulsante per attivare/disattivare il pannello.
     */
    private void inizializzaPannelloScambiatore(){
        scambiatori = new TextField[6];
        pannelloscambiatore.setHgap(10);
        pannelloscambiatore.setVgap(5);
        pannelloscambiatore.setAlignment(Pos.CENTER);
        for (int i = 0; i < 6; i++) {
            scambiatori[i] = new TextField();
            scambiatori[i].setPrefWidth(80);
            scambiatori[i].setPrefHeight(15);
            scambiatori[i].setFont(Font.font(18));
            scambiatori[i].setAlignment(Pos.CENTER);
            scambiatori[i].setEditable(true);
            TextField textField = scambiatori[i];
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 2) {
                    textField.setText(oldValue);
                    return;
                }

                if (!newValue.equals(newValue.toUpperCase())) {
                    String upperValue = newValue.toUpperCase();
                    textField.setText(upperValue);
                    textField.positionCaret(upperValue.length());
                }

                if (!textField.getText().equals(oldValue)) {
                    enigma.pannelloScambiatoreAttivo = false;
                    pannellobtn.setText("OFF");
                }
            });
            pannelloscambiatore.add(scambiatori[i], i, 0);
        }

        pannellobtn = new Button("OFF");
        pannellobtn.setOnAction(e -> {
            if (enigma.pannelloScambiatoreAttivo){
                enigma.pannelloScambiatoreAttivo = false;
                pannellobtn.setText("OFF");
            }else {
                enigma.resettaScambi();
                boolean is = false;
                for (int i = 0; i < 6; i++) {
                    if (scambiatori[i].getText().trim().length() == 2) {
                        char carattere1 = scambiatori[i].getText().charAt(0);
                        char carattere2 = scambiatori[i].getText().charAt(1);
                        if (enigma.contieneScambio(carattere1, carattere2)) {
                            scambiatori[i].setText("");
                        } else if (carattere1 >= 'A' && carattere1 <= 'Z' && carattere2 >= 'A' && carattere2 <= 'Z' && carattere1 != carattere2) {
                            is = true;
                            enigma.aggiungiScambio(carattere1, carattere2);
                        }
                    }else scambiatori[i].setText("");
                }
                if (is) {
                    enigma.pannelloScambiatoreAttivo = true;
                    pannellobtn.setText("ON");
                }
            }

        });
        pannellobtn.setFont(Font.font(18));
        pannellobtn.setAlignment(Pos.CENTER);
        pannellobtn.setPrefWidth(80);
        pannellobtn.setPrefHeight(20);
        pannelloscambiatore.add(pannellobtn, 6, 0);
    }

    /**
     * Inizializza la macchina Enigma con le configurazioni selezionate nei ComboBox dei rotori e del riflessore.
     * Imposta le posizioni iniziali dei rotori e del riflessore.
     */
    private void inizializzaEnigmaMachine(){
        enigma = new EnigmaMachine(rotdxcmb.getSelectionModel().getSelectedIndex(), rotcncmb.getSelectionModel().getSelectedIndex(), rotsxcmb.getSelectionModel().getSelectedIndex(), riflcmb.getSelectionModel().getSelectedIndex());
        rotsxtxt.setText("" + 'A');
        rotcntxt.setText("" + 'A');
        rotdxtxt.setText("" + 'A');
        rifltxt.setText("" + 'A');
    }

    /**
     * Gestisce il click sul pulsante per incrementare il rotore sinistro.
     */
    public void onPiusxBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreSinistro(true, true);
            EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, true, -1);
        }

    }

    /**
     * Gestisce il click sul pulsante per decrementare il rotore sinistro.
     */
    public void onMenosxBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreSinistro(false, true);
            EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, false, -1);
        }
    }

    /**
     * Gestisce il click sul pulsante per incrementare il rotore centrale.
     */
    public void onPiucnBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreCentrale(true, true);
            EnigmaMachine.aggiornaRotoreTxt(rotcntxt, true, -1);
        }
    }

    /**
     * Gestisce il click sul pulsante per decrementare il rotore centrale.
     */
    public void onMenocnBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreCentrale(false, true);
            EnigmaMachine.aggiornaRotoreTxt(rotcntxt, false, -1);
        }
    }

    /**
     * Gestisce il click sul pulsante per incrementare il rotore destro.
     */
    public void onPiudxBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreDestro(true, true);
            EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, true, -1);
        }
    }

    /**
     * Gestisce il click sul pulsante per decrementare il rotore destro.
     */
    public void onMenodxBtnClick(){
        if (enigma.PC == 0){
            enigma.ruotaRotoreDestro(false, true);
            EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, false, -1);
        }
    }

    /**
     * Inizializza le lampadine della tastiera per visualizzare i caratteri codificati.
     * Crea un layout QWERTY, fedele all'originale, per le lampadine e aggiunge il tasto backspace.
     */
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

    /**
     * Inizializza i pulsanti della tastiera e aggiunge i listener per gestire gli eventi di pressione dei tasti.
     * Crea un layout QWERTY, fedele all'originale, per la tastiera e aggiunge il tasto backspace.
     */
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

    /**
     * Gestisce la pressione di un tasto sulla tastiera.
     *
     * @param lettera il carattere del tasto premuto
     */
    public void gestisciTasto(char lettera) {
        if (lettera == '\b') {
            if (inputtxt.getText().length() > 0) {
                inputtxt.setText(inputtxt.getText().substring(0, inputtxt.getText().length() - 1));
            }
        }else inputtxt.appendText("" + lettera);

    }

    /**
     * Inizializza il listener per l'area di testo dell'input e gestisce gli eventi di modifica del testo.
     * Codifica i caratteri inseriti e aggiorna l'output accendendo le lampadine corrispondenti.
     */
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
                        if (transformedChar >= 'A' && transformedChar <= 'Z') {
                            gestioneLampadine(true, lampadine[transformedChar - 'A']);
                        }
                    }
                }
            } else if (differenza < 0) {
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
                gestioneLampadine(true, lampadine['T' - 'A']);

                /*Reset rotori e lampadine se l'input è vuoto, togliere commento se serve
                if (newValue.length() == 0) {
                    enigma.resettaRotori();
                    gestioneLampadine(false, null);
                }
                */
            }

            // Aggiorna le visualizzazioni dei rotori
            EnigmaMachine.aggiornaRotoreTxt(rotsxtxt, true, enigma.getPosizioneRotoreSinistro());
            EnigmaMachine.aggiornaRotoreTxt(rotcntxt, true, enigma.getPosizioneRotoreCentrale());
            EnigmaMachine.aggiornaRotoreTxt(rotdxtxt, true, enigma.getPosizioneRotoreDestro());
        });
    }

    /**
     * Verifica se un carattere è valido.
     *
     * @param c il carattere da verificare
     * @return true se il carattere è valido, false altrimenti
     */
    private boolean okCarattere(char c) {
        return carattereMaiuscolo(c) || carattereMinuscolo(c);
    }

    /**
     * Verifica se un carattere è maiuscolo.
     *
     * @param c il carattere da verificare
     * @return true se il carattere è maiuscolo, false altrimenti
     */
    private boolean carattereMaiuscolo(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    /**
     * Verifica se un carattere è minuscolo.
     *
     * @param c il carattere da verificare
     * @return true se il carattere è minuscolo, false altrimenti
     */
    private boolean carattereMinuscolo(char c) {
        return (c >= 'a' && c <= 'z');
    }

    /**
     * Gestisce l'accensione e lo spegnimento delle lampadine.
     *
     * @param accendere true se c'è da accendere una lampadina, false se sono da spegnere tutte
     * @param lampadina la lampadina eventualmente da accendere
     */
    private void gestioneLampadine(boolean accendere, Lampadina lampadina) {
        for (Lampadina lamp : lampadine) {
            if (lamp != null) lamp.spegni();
        }
        if (accendere) lampadina.accendi();
    }

    /**
     * Inizializza i listener per i ComboBox dei rotori e del riflessore.
     * Quando viene selezionata una nuova configurazione, viene chiamata la funzione per inizializzare la macchina Enigma.
     */
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