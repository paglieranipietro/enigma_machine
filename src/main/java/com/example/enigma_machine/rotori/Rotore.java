package com.example.enigma_machine.rotori;

import java.util.ArrayList;

public class Rotore {
    private ArrayList<Character> lettere;

    public Rotore(int numRotore) {
        this.lettere = CablaggioRotori.getRotore(numRotore);
    }

    public Character getLettera(int lettera, int indice) {
        return this.lettere.get(lettera-indice >= 'A'? 'Z' - 'A' - indice);
    }
}
