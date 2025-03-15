package com.example.enigma_machine;

import com.example.enigma_machine.componenti.PannelloScambiatore;
import com.example.enigma_machine.componenti.Riflessore;
import com.example.enigma_machine.componenti.Rotore;

public class EnigmaMachine {
    Rotore rotoreDestro;
    Rotore rotoreCentrale;
    Rotore rotoreSinistro;
    Riflessore riflessore;
    PannelloScambiatore pannelloScambiatore;

    public EnigmaMachine(int confRotoreDestro, int confRotoreCentrale, int confRotoreSinistro, int posizioneRotoreDestro, int posizioneRotoreCentrale, int posizioneRotoreSinistro) {
        this.rotoreDestro = new Rotore(confRotoreDestro, posizioneRotoreDestro);
        this.rotoreCentrale = new Rotore(confRotoreCentrale, posizioneRotoreCentrale);
        this.rotoreSinistro = new Rotore(confRotoreSinistro, posizioneRotoreSinistro);
        this.riflessore = new Riflessore();
        this.pannelloScambiatore = new PannelloScambiatore();
    }

    private char codificaCarattere(char c) {
        c = Character.toUpperCase(c);
        c = this.pannelloScambiatore.scambia(c);
        c = this.rotoreDestro.letteraAvanti(c);
        c = this.rotoreCentrale.letteraAvanti(c);
        c = this.rotoreSinistro.letteraAvanti(c);
        c = this.riflessore.codifica(c);
        c = this.rotoreSinistro.letteraIndietro(c);
        c = this.rotoreCentrale.letteraIndietro(c);
        c = this.rotoreDestro.letteraIndietro(c);
        return c;
    }

    private void ruotaRotori() {
        if (rotoreDestro.ruota()){
            if (rotoreCentrale.ruota()){
                rotoreSinistro.ruota();
            }
        }
    }

    private String codificaFrase (String frase) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char c : frase.toCharArray()) {
            if (Character.isLetter(c)) {
                ruotaRotori();
                sb.append(codificaCarattere(c));
            }
            i++;
            if (i == 5){
                sb.append(' ');
                i = 0;
            }
        }
        return sb.toString();
    }
}
