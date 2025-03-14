package com.example.enigma_machine.rotori;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CablaggioRotori {

    public static final ArrayList<Character> confRotore1 = new ArrayList<>();
    public static final ArrayList<Character> confRotore2 = new ArrayList<>();
    public static final ArrayList<Character> confRotore3 = new ArrayList<>();

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/enigma_machine/rotori/input.txt"));
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
}
