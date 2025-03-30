package com.example.enigma_machine_jfx.componenti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UploadConfigurazioni {

    // Configurazioni dei rotori e riflessori
    private static final ArrayList<Character> confRotore1 = new ArrayList<>();
    private static char scatto1;
    private static final ArrayList<Character> confRotore2 = new ArrayList<>();
    private static char scatto2;
    private static final ArrayList<Character> confRotore3 = new ArrayList<>();
    private static char scatto3;
    private static final ArrayList<Character> confRiflessore1 = new ArrayList<>();
    private static final ArrayList<Character> confRiflessore2 = new ArrayList<>();
    private static final ArrayList<Character> confRiflessore3 = new ArrayList<>();

    // Blocco statico per caricare le configurazioni dai file
    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/enigma_machine_jfx/componenti/configurazioni.txt"));
            String confRotore;
            confRotore = br.readLine();
            for(int i = 0; i < confRotore.length() - 2; i++){
                confRotore1.add(confRotore.charAt(i));
            }
            scatto1 = Character.toUpperCase(confRotore.charAt(confRotore.length() - 1));
            confRotore = br.readLine();
            for(int i = 0; i < confRotore.length(); i++){
                confRotore2.add(confRotore.charAt(i));
            }
            scatto2 = Character.toUpperCase(confRotore.charAt(confRotore.length() - 1));
            confRotore = br.readLine();
            for(int i = 0; i < confRotore.length(); i++){
                confRotore3.add(confRotore.charAt(i));
            }
            scatto3 = Character.toUpperCase(confRotore.charAt(confRotore.length() - 1));
            String confRiflessore;
            confRiflessore = br.readLine();
            for(int i = 0; i < confRiflessore.length(); i++){
                confRiflessore1.add(confRiflessore.charAt(i));
            }
            confRiflessore = br.readLine();
            for(int i = 0; i < confRiflessore.length(); i++){
                confRiflessore2.add(confRiflessore.charAt(i));
            }
            confRiflessore = br.readLine();
            for(int i = 0; i < confRiflessore.length(); i++){
                confRiflessore3.add(confRiflessore.charAt(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Restituisce la configurazione del rotore specificato.
     *
     * @param numRotore il numero del rotore (1, 2 o 3)
     * @return la configurazione del rotore come ArrayList di caratteri
     */
    public static ArrayList<Character> getRotore(int numRotore) {
        switch (numRotore) {
            case 1:
                return confRotore1;
            case 2:
                return confRotore2;
            case 3:
                return confRotore3;
        }
        return null;
    }

    /**
     * Restituisce la configurazione del riflessore specificato.
     *
     * @param numRiflessore il numero del riflessore (0, 1 o 2)
     * @return la configurazione del riflessore come ArrayList di caratteri
     */
    public static ArrayList<Character> getRiflessore(int numRiflessore) {
        switch (numRiflessore) {
            case 0:
                return confRiflessore1;
            case 1:
                return confRiflessore2;
            case 2:
                return confRiflessore3;
        }
        return null;
    }

    /**
     * Restituisce il carattere di scatto del rotore specificato.
     *
     * @param numRotore il numero del rotore (1, 2 o 3)
     * @return il carattere di scatto del rotore
     */
    public static char getScatto(int numRotore) {
        switch (numRotore) {
            case 1:
                return scatto1;
            case 2:
                return scatto2;
            case 3:
                return scatto3;
        }
        return 'A';
    }

    /**
     * Restituisce l'alfabeto come ArrayList di caratteri.
     *
     * @return l'alfabeto
     */
    public static ArrayList<Character> getAlfabeto() {
        ArrayList<Character> alfabeto = new ArrayList<>();
        alfabeto = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        return alfabeto;
    }
}