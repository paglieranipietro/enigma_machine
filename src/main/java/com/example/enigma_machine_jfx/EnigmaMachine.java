package com.example.enigma_machine_jfx;

import com.example.enigma_machine_jfx.componenti.PannelloScambiatore;
import com.example.enigma_machine_jfx.componenti.Riflessore;
import com.example.enigma_machine_jfx.componenti.Rotore;
import javafx.scene.control.TextField;

public class EnigmaMachine {
    //gestione della vera e propria macchina enigma con la codifica delle lettere
    private Rotore rotoreDestro;
    private Rotore rotoreCentrale;
    private Rotore rotoreSinistro;
    private Riflessore riflessore;
    private PannelloScambiatore pannelloScambiatore;
    private int PC = 0;
    private int posInizialedx, posInizialecn, posInizialesx;
    public boolean pannelloScambiatoreAttivo = false;


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
        } else if (c == '\b') {
            decrementaPC();
            ruotaRotoriIndietro(); // Ruota i rotori all'indietro per il backspace
            return '!'; // Indica che è stato premuto backspace
        } else {
            return '!'; // Carattere non valido
        }
    }

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

    public void resettaRotori() {
        rotoreDestro.setPosizione(posInizialedx);
        rotoreCentrale.setPosizione(posInizialecn);
        rotoreSinistro.setPosizione(posInizialesx);
        PC = 0;
    }

    public void aggiungiScambio(char c1, char c2){
        pannelloScambiatore.aggiungiScambio(c1, c2);
    }

    public void resettaScambi(){
        pannelloScambiatore.reset();
    }

    public boolean contieneScambio(char c, char c2){
        return pannelloScambiatore.contieneScambio(c) || pannelloScambiatore.contieneScambio(c2);
    }

    private void incrementaPC(boolean bottoni){
        if (!bottoni)PC++;
    }

    private void decrementaPC(){
        if (PC > 0) PC--;
    }
}
