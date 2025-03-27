package com.example.enigma_machine_jfx.componenti;

import java.util.ArrayList;
import java.util.HashMap;

public class Riflessore {
    private HashMap<Character, Character> caratteri;

    public Riflessore(int num) {
        ArrayList<Character> temp = UploadConfigurazioni.getRiflessore(num);
        this.caratteri = new HashMap<>();
        int i = 0;
        for (Character ch : temp) {
            this.caratteri.put((char)('A' + i), ch);
            i++;
        }
    }

    public Character codifica(Character lettera) {
        return this.caratteri.get(lettera);
    }
}
