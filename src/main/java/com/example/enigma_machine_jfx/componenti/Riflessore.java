package com.example.enigma_machine_jfx.componenti;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe Riflessore rappresenta un riflessore della macchina Enigma.
 * Ogni riflessore ha una configurazione che mappa i caratteri.
 */
public class Riflessore {
    private HashMap<Character, Character> caratteri;

    /**
     * Costruttore della classe Riflessore.
     *
     * @param num il numero del riflessore da caricare
     */
    public Riflessore(int num) {
        ArrayList<Character> temp = UploadConfigurazioni.getRiflessore(num);
        this.caratteri = new HashMap<>();
        int i = 0;
        for (Character ch : temp) {
            this.caratteri.put((char)('A' + i), ch);
            i++;
        }
    }

    /**
     * Codifica un carattere utilizzando il riflessore.
     *
     * @param lettera il carattere da codificare
     * @return il carattere codificato
     */
    public Character codifica(Character lettera) {
        return this.caratteri.get(lettera);
    }
}