package com.example.enigma_machine.componenti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Configurazioni {

    public static final ArrayList<Character> confRotore1 = new ArrayList<>();
    public static final ArrayList<Character> confRotore2 = new ArrayList<>();
    public static final ArrayList<Character> confRotore3 = new ArrayList<>();
    public static final ArrayList<Character> confRiflessore1 = new ArrayList<>();

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
            String confRotore;
            confRotore = br.readLine();
            for(int i = 0; i < confRotore.length(); i++){
                confRotore1.add(confRotore.charAt(i));
            }
            confRotore = br.readLine();
            for(int i = 0; i < confRotore.length(); i++){
                confRotore2.add(confRotore.charAt(i));
            }
            confRotore = br.readLine();
            for(int i = 0; i < confRotore.length(); i++){
                confRotore3.add(confRotore.charAt(i));
            }
            String confRiflessore;
            confRiflessore = br.readLine();
            for(int i = 0; i < confRiflessore.length(); i++){
                confRiflessore1.add(confRiflessore.charAt(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Character> getRotore(int numRotore) {
        ArrayList<Character> rotore = new ArrayList<>();
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

    public static ArrayList<Character> getRiflessore() {
        return confRiflessore1;
    }

    public static ArrayList<Character> getAlfabeto() {
        ArrayList<Character> alfabeto = new ArrayList<>();
        alfabeto = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        return alfabeto;
    }
}

