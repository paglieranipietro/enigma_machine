package com.example.enigma_machine.rotori;

public class Rotori {

    private int indiceRotore1 = 0, indiceRotore2 = 0, indiceRotore3 = 0;
    private Rotore rotore1;
    private Rotore rotore2;
    private Rotore rotore3;

    public Rotori(int confRot1, int confRot2, int confRot3) {
        rotore1 = new Rotore(confRot1);
        rotore2 = new Rotore(confRot2);
        rotore3 = new Rotore(confRot3);
    }

    public Character getLettera(char lettera){
        if (indiceRotore1 == 26){
            indiceRotore1 = 0;

            if (indiceRotore2 == 26){
                indiceRotore2 = 0;

                if (indiceRotore3 == 26){
                    indiceRotore3 = 0;
                }else indiceRotore3++;

            }else indiceRotore2++;

        }else indiceRotore1++;

        char carattere;
        carattere = rotore1.getLettera(lettera, indiceRotore1);
        carattere = rotore2.getLettera(carattere == 'A' ? 'Z' : carattere-indiceRotore2, indiceRotore2);
        carattere = rotore3.getLettera(carattere == 'A' ? 'Z' : carattere-indiceRotore3, indiceRotore3);
    }
}
