package com.example.enigma_machine_jfx.componenti;

import java.util.HashMap;

public class PannelloScambiatore {
    private final HashMap<Character, Character> scambi;

    /**
     * Costruttore della classe PannelloScambiatore.
     * Inizializza una mappa per gli scambi di lettere.
     */
    public PannelloScambiatore() {
        this.scambi = new HashMap<>();
    }

    /**
     * Aggiunge una coppia di lettere da scambiare.
     *
     * @param lettera1 Prima lettera dello scambio
     * @param lettera2 Seconda lettera dello scambio
     */
    public void aggiungiScambio(char lettera1, char lettera2) {
        // Evita scambi con sé stesso o caratteri non alfabetici
        if (lettera1 == lettera2 || !Character.isLetter(lettera1) || !Character.isLetter(lettera2) || scambi.containsKey(lettera1) || scambi.containsKey(lettera2)) {
            return;
        }
        scambi.put(lettera1, lettera2);
        scambi.put(lettera2, lettera1);
    }

    /**
     * Verifica se una lettera è coinvolta in uno scambio.
     *
     * @param lettera La lettera da controllare
     * @return true se la lettera ha uno scambio attivo
     */
    public boolean contieneScambio(char lettera) {
        lettera = Character.toUpperCase(lettera);
        return scambi.containsKey(lettera);
    }

    /**
     * Restituisce la lettera scambiata corrispondente.
     *
     * @param lettera La lettera da trasformare
     * @return La lettera scambiata, o la stessa se non presente
     */
    public char scambia(char lettera) {
        return scambi.getOrDefault(lettera, lettera);
    }

    /**
     * Svuota tutti gli scambi.
     */
    public void reset() {
        scambi.clear();
    }
}