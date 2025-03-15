package com.example.enigma_machine.componenti;

import java.util.HashMap;

public class Plugboard {
    private HashMap<Character, Character> scambi;

    public Plugboard() {
        this.scambi = new HashMap<>();
    }

    public void aggiungiScambio(Character lettera1, Character lettera2) {
        lettera1 = Character.toUpperCase(lettera1);
        lettera2 = Character.toUpperCase(lettera2);
        this.scambi.put(lettera1, lettera2);
        this.scambi.put(lettera2, lettera1);
    }

    public void rimuoviScambio(Character lettera1, Character lettera2) {
        lettera1 = Character.toUpperCase(lettera1);
        lettera2 = Character.toUpperCase(lettera2);
        this.scambi.remove(lettera1);
        this.scambi.remove(lettera2);
    }

    public Character scambia (Character lettera) {
        lettera = Character.toUpperCase(lettera);
        return this.scambi.getOrDefault(lettera, lettera);
    }
}
