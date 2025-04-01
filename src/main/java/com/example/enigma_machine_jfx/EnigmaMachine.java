package com.example.enigma_machine_jfx;

import com.example.enigma_machine_jfx.componenti.PannelloScambiatore;
import com.example.enigma_machine_jfx.componenti.Riflessore;
import com.example.enigma_machine_jfx.componenti.Rotore;
import javafx.scene.control.TextField;

/**
 * Classe per la gestione della macchina enigma vera e propria.
 *
 * @author Pietro Paglierani
 * @author Francesco Grassi
 * @author Aminata Diame
 */
public class EnigmaMachine {
    private Rotore rotoreDestro;
    private Rotore rotoreCentrale;
    private Rotore rotoreSinistro;
    private Riflessore riflessore;
    private PannelloScambiatore pannelloScambiatore;
    public int PC = 0;
    private int posInizialedx, posInizialecn, posInizialesx;
    public boolean pannelloScambiatoreAttivo = false;

    /**
     * Costruttore della classe EnigmaMachine.
     *
     * @param confRotoreDestro configurazione del rotore destro
     * @param confRotoreCentrale configurazione del rotore centrale
     * @param confRotoreSinistro configurazione del rotore sinistro
     * @param confRiflessore configurazione del riflessore
     */
    public EnigmaMachine(int confRotoreDestro, int confRotoreCentrale, int confRotoreSinistro, int confRiflessore) {
        this.rotoreDestro = new Rotore(confRotoreDestro + 1, 0);
        this.rotoreCentrale = new Rotore(confRotoreCentrale + 1, 0);
        this.rotoreSinistro = new Rotore(confRotoreSinistro + 1, 0);
        this.posInizialedx = 0;
        this.posInizialecn = 0;
        this.posInizialesx = 0;
        this.riflessore = new Riflessore(confRiflessore);
        this.pannelloScambiatore = new PannelloScambiatore();
    }

    /**
     * Incrementa il Program Counter (PC).
     *
     * @param bottoni indica se l'incremento è causato da un'azione sui bottoni dei rotori
     */
    private void incrementaPC(boolean bottoni){
        if (!bottoni)PC++;
    }

    /**
     * Decrementa il Program Counter (PC).
     */
    private void decrementaPC(){
        if (PC > 0) PC--;
    }

    /**
     * Codifica un carattere utilizzando i componenti della macchina Enigma.
     * Pannello scambiatore - rotori da destra a sinistra - riflessore - rotori da sinistra a destra - pannello scambiatore.
     *
     * @param c il carattere da codificare
     * @return il carattere codificato
     */
    public char codificaCarattere(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            incrementaPC(false);
            ruotaRotoriAvanti(); // Ruota i rotori in avanti per il nuovo carattere
            c = Character.toUpperCase(c);
            if (pannelloScambiatoreAttivo) c = this.pannelloScambiatore.scambia(c);
            c = this.rotoreDestro.letteraAvanti(c);
            c = this.rotoreCentrale.letteraAvanti(c);
            c = this.rotoreSinistro.letteraAvanti(c);
            c = this.riflessore.codifica(c);
            c = this.rotoreSinistro.letteraIndietro(c);
            c = this.rotoreCentrale.letteraIndietro(c);
            c = this.rotoreDestro.letteraIndietro(c);
            if (pannelloScambiatoreAttivo) c = this.pannelloScambiatore.scambia(c);
            return c;
        } else {
            return '!'; // Carattere non valido
        }
    }

    /**
     * Ruota i rotori in avanti tendendo conto dello scatto.
     */
    private void ruotaRotoriAvanti() {
        if (rotoreDestro.ruotaAvanti(PC)){
            if (rotoreCentrale.ruotaAvanti(PC)){
                rotoreSinistro.ruotaAvanti(PC);
            }
        } else if (rotoreCentrale.isScatto(true)) {
            if (rotoreCentrale.ruotaAvanti(PC)){
                rotoreSinistro.ruotaAvanti(PC);
            }
        }
    }

    /**
     * Ruota i rotori indietro tendendo conto dello scatto.
     */
    public void ruotaRotoriIndietro() {
        if (rotoreDestro.ruotaIndietro(PC)){
            if (rotoreCentrale.ruotaIndietro(PC)){
                rotoreSinistro.ruotaIndietro(PC);
            }
        }else if (rotoreCentrale.isScatto(false)) {
            if (rotoreCentrale.ruotaIndietro(PC)){
                rotoreSinistro.ruotaIndietro(PC);
            }
        }
    }

    /**
     * Resetta i rotori alle posizioni iniziali.
     * A - A - A predefinito ma può essere modificato con i bottoni dei rotori graficamente.
     */
    public void resettaRotori() {
        rotoreDestro.setPosizione(posInizialedx);
        rotoreCentrale.setPosizione(posInizialecn);
        rotoreSinistro.setPosizione(posInizialesx);
        PC = 0;
    }

    /**
     * Ruota il rotore destro in avanti o indietro ed eventualmente imposta la posizione iniziale.
     *
     * @param avanti indica se ruotare in avanti
     * @param iniziale indica se è la posizione iniziale
     */
    public void ruotaRotoreDestro(boolean avanti, boolean iniziale) {
        if (avanti){
            incrementaPC(true);
            rotoreDestro.ruotaAvanti(PC);
        }else {
            decrementaPC();
            rotoreDestro.ruotaIndietro(PC);
        }
        if (iniziale) posInizialedx = rotoreDestro.getPosizione();
    }

    /**
     * Ruota il rotore centrale in avanti o indietro ed eventualmente imposta la posizione iniziale.
     *
     * @param avanti indica se ruotare in avanti
     * @param iniziale indica se è la posizione iniziale
     */
    public void ruotaRotoreCentrale(boolean avanti, boolean iniziale) {
        if (avanti){
            incrementaPC(true);
            rotoreCentrale.ruotaAvanti(PC);
        }else {
            decrementaPC();
            rotoreCentrale.ruotaIndietro(PC);
        }
        if (iniziale) posInizialecn = rotoreCentrale.getPosizione();
    }

    /**
     * Ruota il rotore sinistro in avanti o indietro ed eventualmente imposta la posizione iniziale.
     *
     * @param avanti indica se ruotare in avanti
     * @param iniziale indica se è la posizione iniziale
     */
    public void ruotaRotoreSinistro(boolean avanti, boolean iniziale) {
        if (avanti){
            incrementaPC(true);
            rotoreSinistro.ruotaAvanti(PC);
        }else {
            decrementaPC();
            rotoreSinistro.ruotaIndietro(PC);
        }
        if (iniziale) posInizialesx = rotoreSinistro.getPosizione();
    }

    /**
     * Restituisce la posizione attuale del rotore destro.
     *
     * @return la posizione del rotore destro
     */
    public int getPosizioneRotoreDestro() {
        return rotoreDestro.getPosizione();
    }

    /**
     * Restituisce la posizione attuale del rotore centrale.
     *
     * @return la posizione del rotore centrale
     */
    public int getPosizioneRotoreCentrale() {
        return rotoreCentrale.getPosizione();
    }

    /**
     * Restituisce la posizione attuale del rotore sinistro.
     *
     * @return la posizione del rotore sinistro
     */
    public int getPosizioneRotoreSinistro() {
        return rotoreSinistro.getPosizione();
    }

    /**
     * Aggiorna il testo del TextField con la posizione del rotore corrispondente.
     * Se è da incrementare la incrementa, altrimenti decrementa oppure imposta la posizione passata come riferimento.
     *
     * @param txt il TextField da aggiornare
     * @param aggiungi indica se incrementare la posizione
     * @param pos la posizione del rotore
     */
    public static void aggiornaRotoreTxt(TextField txt, boolean aggiungi, int pos){
        if (pos != -1){
            txt.setText(String.valueOf((char)('A' + pos)));
        }else if (aggiungi){
            String temp = txt.getText();
            char c = temp.charAt(0);
            c = c >= 'Z' ? 'Z' : (char)(c + 1);
            txt.setText(String.valueOf(c));
        }else{
            String temp = txt.getText();
            char c = temp.charAt(0);
            c = c <= 'A' ? 'A' : (char)(c - 1);
            txt.setText(String.valueOf(c));
        }
    }

    /**
     * Restituisce la configurazione del rotore.
     *
     * @param rotore il rotore di cui ottenere la configurazione
     * @return la configurazione del rotore
     */
    public String getConfRotore(String rotore) {
        switch (rotore) {
            case "dx":
                switch (rotoreDestro.getConfigurazione()) {
                    case 1:
                        return "I";
                    case 2:
                        return "II";
                    case 3:
                        return "III";
                }
            case "cn":
                switch (rotoreCentrale.getConfigurazione()) {
                    case 1:
                        return "I";
                    case 2:
                        return "II";
                    case 3:
                        return "III";
                }
            case "sx":
                switch (rotoreSinistro.getConfigurazione()) {
                    case 1:
                        return "I";
                    case 2:
                        return "II";
                    case 3:
                        return "III";
                }
        }
        return null;
    }

    /**
     * Aggiunge uno scambio al pannello scambiatore.
     *
     * @param c1 il primo carattere dello scambio
     * @param c2 il secondo carattere dello scambio
     */
    public void aggiungiScambio(char c1, char c2){
        pannelloScambiatore.aggiungiScambio(c1, c2);
    }

    /**
     * Resetta gli scambi del pannello scambiatore.
     */
    public void resettaScambi(){
        pannelloScambiatore.reset();
    }

    /**
     * Verifica se il pannello scambiatore contiene uno scambio.
     *
     * @param c il primo carattere dello scambio da verificare
     * @param c2 il secondo carattere dello scambio da verificare
     * @return true se il pannello scambiatore contiene lo scambio, altrimenti false
     */
    public boolean contieneScambio(char c, char c2){
        return pannelloScambiatore.contieneScambio(c) || pannelloScambiatore.contieneScambio(c2);
    }
}