package com.example.enigma_machine.componenti;

import java.util.ArrayList;

public class Rotore {
    private ArrayList<Character> caratteri;
    int posizione;
    int scatto;

    public Rotore(int numRotore, int posizione, char scatto) {
        this.caratteri = Configurazioni.getRotore(numRotore);
        this.posizione = posizione;
        scatto = Character.toUpperCase(scatto);
        this.scatto = (scatto - 'A' + 26) % 26;
    }

    public char letteraAvanti (char c) {
        char carattere = caratteri.get((c - 'A' + posizione) % 26);
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }

    public char letteraIndietro (char c) {
        char carattere = (char) (caratteri.indexOf((char) ((c - 'A' + posizione) % 26 + 'A')) + 'A');
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }

    public boolean ruota() {
        posizione = (posizione + 1) % 26;
        return posizione == scatto;
    }
}