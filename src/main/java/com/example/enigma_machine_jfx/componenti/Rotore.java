package com.example.enigma_machine_jfx.componenti;

import java.util.ArrayList;

public class Rotore {
    //attributi: ArrayList per i caratteri del rotore, posizione corrente del rotore, scatto del rotore, configurazione del rotore
    //metodi: costruttore, letteraAvanti, letteraIndietro, ruotaAvanti, ruotaIndietro, setPosizione, getPosizione, getConfigurazione, isScatto
    private ArrayList<Character> caratteri;
    int posizione;
    int scatto;
    int configurazione;

    public Rotore(int configurazione, int posizione) {
        this.configurazione = configurazione;
        this.caratteri = UploadConfigurazioni.getRotore(configurazione);
        this.posizione = posizione;
        this.scatto = UploadConfigurazioni.getAlfabeto().indexOf(UploadConfigurazioni.getScatto(configurazione));
    }
    public char letteraAvanti (char c) {
        char carattere = caratteri.get((c - 'A' + posizione) % 26);
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }

    public char letteraIndietro (char c) {
        char carattere = (char) (caratteri.indexOf((char) ((c - 'A' + posizione) % 26 + 'A')) + 'A');
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }
    public boolean ruotaAvanti(int PC) {
        if (PC == 0 && (posizione % 26) == 25) {
            return false;
        }
        posizione = (posizione + 1) % 26;
        return posizione == ((scatto + 1) % 26);
    }

    public boolean ruotaIndietro(int programCounter) {
        if (programCounter == 0 && posizione == 0) {
            return false;
        }
        posizione = (posizione - 1 + 26) % 26;
        return posizione == scatto;
    }

    public void setPosizione(int posizione) {this.posizione = posizione;}

    public int getPosizione() {return posizione;}

    public int getConfigurazione() {return configurazione;}

    public boolean isScatto(boolean avanti) {
        if (avanti){
            return posizione == scatto;
        }else{
            return (posizione - 1 + 26) % 26 == scatto;
        }
    }
}
