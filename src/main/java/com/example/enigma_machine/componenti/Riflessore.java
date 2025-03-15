package com.example.enigma_machine.componenti;

import java.util.HashMap;

public class Riflessore {
    private HashMap<Character, Character> caratteri;

    public Riflessore() {
        this.caratteri = new HashMap<>();
        int i = 0;
        for (Character ch : Configurazioni.getRiflessore()) {
            this.caratteri.put((char)('A' + i), ch);
            i++;
        }
    }

    public Character codifica(Character lettera) {
        lettera = Character.toUpperCase(lettera);
        return this.caratteri.get(lettera);
    }
}
