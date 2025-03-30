package com.example.enigma_machine_jfx.componenti;

import java.util.ArrayList;

public class Rotore {
    // Attributi: ArrayList per i caratteri del rotore, posizione corrente del rotore, scatto del rotore, configurazione del rotore
    private ArrayList<Character> caratteri;
    int posizione;
    int scatto;
    int configurazione;

    /**
     * Costruttore della classe Rotore.
     *
     * @param configurazione la configurazione del rotore
     * @param posizione la posizione iniziale del rotore
     */
    public Rotore(int configurazione, int posizione) {
        this.configurazione = configurazione;
        this.caratteri = UploadConfigurazioni.getRotore(configurazione);
        this.posizione = posizione;
        this.scatto = UploadConfigurazioni.getAlfabeto().indexOf(UploadConfigurazioni.getScatto(configurazione));
    }

    /**
     * Codifica un carattere passando attraverso il rotore in avanti.
     *
     * @param c il carattere da codificare
     * @return il carattere codificato
     */
    public char letteraAvanti(char c) {
        char carattere = caratteri.get((c - 'A' + posizione) % 26);
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }

    /**
     * Codifica un carattere passando attraverso il rotore indietro.
     *
     * @param c il carattere da codificare
     * @return il carattere codificato
     */
    public char letteraIndietro(char c) {
        char carattere = (char) (caratteri.indexOf((char) ((c - 'A' + posizione) % 26 + 'A')) + 'A');
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }

    /**
     * Ruota il rotore in avanti.
     *
     * @param PC il Program Counter
     * @return true se il rotore ha raggiunto la posizione di scatto, altrimenti false
     */
    public boolean ruotaAvanti(int PC) {
        if (PC == 0 && (posizione % 26) == 25) {
            return false;
        }
        posizione = (posizione + 1) % 26;
        return posizione == ((scatto + 1) % 26);
    }

    /**
     * Ruota il rotore indietro.
     *
     * @param programCounter il Program Counter
     * @return true se il rotore ha raggiunto la posizione di scatto, altrimenti false
     */
    public boolean ruotaIndietro(int programCounter) {
        if (programCounter == 0 && posizione == 0) {
            return false;
        }
        posizione = (posizione - 1 + 26) % 26;
        return posizione == scatto;
    }

    /**
     * Imposta la posizione del rotore.
     *
     * @param posizione la nuova posizione del rotore
     */
    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    /**
     * Restituisce la posizione corrente del rotore.
     *
     * @return la posizione corrente del rotore
     */
    public int getPosizione() {
        return posizione;
    }

    /**
     * Restituisce la configurazione del rotore.
     *
     * @return la configurazione del rotore
     */
    public int getConfigurazione() {
        return configurazione;
    }

    /**
     * Verifica se il rotore è nella posizione di scatto.
     *
     * @param avanti true se il rotore ruota in avanti, false se ruota indietro
     * @return true se il rotore è nella posizione di scatto, altrimenti false
     */
    public boolean isScatto(boolean avanti) {
        if (avanti) {
            return posizione == scatto;
        } else {
            return (posizione - 1 + 26) % 26 == scatto;
        }
    }
}