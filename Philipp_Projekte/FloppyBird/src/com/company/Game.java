package com.company;

import java.util.Random;

public class Game {
    //Attribute
    private String[][] spielfeld;
    Random random = new Random();
    private int spielerHoehe = 5;
    //Farben
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_WHITE = "\u001B[37m";


    // Spielfeld erstellen
    public String[][] CreateSpielfeld() {
        //Erstellen des Spielfeldes
        spielfeld = new String[10][50];
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                spielfeld[yAxis][xAxis] = ".";
            }
        }
        return spielfeld;
    }

    // Spielfeld Ausgabe
    public void PrintSpielfeld(String[][] spielfeld) {
        this.spielfeld = spielfeld;

        System.out.println(" ");
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                if(spielfeld[yAxis][xAxis] == ".") {
                    System.out.print(ANSI_CYAN + spielfeld[yAxis][xAxis]+ ANSI_RESET);
                }
                else if(spielfeld[yAxis][xAxis] == "#" || spielfeld[yAxis][xAxis] == "|") {
                    System.out.print(ANSI_GREEN + spielfeld[yAxis][xAxis]+ ANSI_RESET);
                }
                else if(spielfeld[yAxis][xAxis] == "*" || spielfeld[yAxis][xAxis] == "/") {
                    System.out.print(ANSI_YELLOW + spielfeld[yAxis][xAxis]+ ANSI_RESET);
                }
                else {
                    System.out.print(ANSI_RED + spielfeld[yAxis][xAxis] + ANSI_RESET);
                }
            }
            System.out.println(" ");
        }
    }

    // Erstellen eines Turmes
    public String[][] CreateTower(String[][] spielfeld) {
        this.spielfeld = spielfeld;


        int TurmOben = random.nextInt(5);
        int TurmUnten = TurmOben+5;

        spielfeld[TurmOben][spielfeld[0].length-1] = "#";
        while(TurmOben > 0) {
            spielfeld[TurmOben-1][spielfeld[0].length-1] = "|";
            TurmOben--;
        }


        if(TurmOben <= 4) {
            spielfeld[TurmUnten][spielfeld[0].length-1] = "#";
            while((TurmUnten+1) != spielfeld.length) {
                spielfeld[TurmUnten+1][spielfeld[0].length-1] = "|";
                TurmUnten++;
            }
        }
        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }

    //Spielfeld nach Links bewegen
    public String[][] MoveMap(String[][] spielfeld) {
        this.spielfeld = spielfeld;

        for (int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                if ((xAxis + 1) < spielfeld[0].length) {
                    spielfeld[yAxis][xAxis] = spielfeld[yAxis][xAxis + 1];
                    spielfeld[yAxis][xAxis+ 1] = ".";
                }
            }
        }
        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }

    //Spieler
    public String[][] Player(String[][] spielfeld) {
        this.spielfeld = spielfeld;

        spielfeld[spielerHoehe][spielfeld[0].length/2] = "*";
        spielfeld[spielerHoehe+1][spielfeld[0].length/2-1] = "/";

        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }
}