package com.example.enigma_machine.rotori;

import java.util.ArrayList;

public class Rotore {
    private ArrayList<Character> caratteri;
    int posizione;

    public Rotore(int numRotore, int posizione) {
        this.caratteri = CablaggioRotori.getRotore(numRotore);
        this.posizione = posizione;
    }

    public char codifica (char c) {
        char carattere = caratteri.get((c - 'A' + posizione) % 26);
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }

    public char decodifica (char c) {
        char carattere = caratteri.get(((c - 'A' + posizione) % 26));
        return (char) ((carattere - 'A' - posizione + 26) % 26 + 'A');
    }
}