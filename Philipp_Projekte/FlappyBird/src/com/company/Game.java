package com.company;
import java.util.Random;

public class Game {
    //Attribute
    private String[][] spielfeld;
    Random random = new Random();
    private int spieleryAxis = 5;
    private static boolean SpaceIsPressed;
    private int Score = 0;


    //Farben
    public final String ANSI_RESET = "\u001B[0m";
    //public final String ANSI_BLACK = "\u001B[30m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_YELLOW = "\u001B[33m";
    //public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";
    //public final String ANSI_WHITE = "\u001B[37m";


    // Spielfeld erstellen
    public String[][] CreateSpielfeld() {
        //Erstellen des Spielfeldes
        spielfeld = new String[10][50];
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                int WkeitWolke = random.nextInt(15);
                if(WkeitWolke == 0 && spielfeld[yAxis][xAxis] == null && yAxis < spielfeld.length/2) {
                    spielfeld[yAxis][xAxis] = "~";
                    if((xAxis+1) < spielfeld[0].length-1){
                        spielfeld[yAxis][xAxis + 1] = "~";
                    }
                }
                else {
                    if(spielfeld[yAxis][xAxis] == null) {
                        spielfeld[yAxis][xAxis] = ".";
                    }
                }
            }
        }
        return spielfeld;
    }

    // Spielfeld Ausgabe
    public void PrintSpielfeld(String[][] spielfeld) {
        this.spielfeld = spielfeld;

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                switch (spielfeld[yAxis][xAxis]) {
                    case "." -> System.out.print(ANSI_CYAN + spielfeld[yAxis][xAxis] + ANSI_RESET);
                    case "#", "|" -> System.out.print(ANSI_GREEN + spielfeld[yAxis][xAxis] + ANSI_RESET);
                    case "*", "/" -> System.out.print(ANSI_YELLOW + spielfeld[yAxis][xAxis] + ANSI_RESET);
                    case "~" -> System.out.print(spielfeld[yAxis][xAxis]);
                    default -> System.out.print(ANSI_PURPLE + spielfeld[yAxis][xAxis] + ANSI_RESET);
                }
            }
            if(yAxis == spielfeld.length-1){
                System.out.println("    Score: " + Score);
            }
            else {
                System.out.println(" ");
            }
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
                //Testen auf Spieler
                if ((xAxis + 1) < spielfeld[0].length && spielfeld[yAxis][xAxis].equals("*") || spielfeld[yAxis][xAxis].equals("/")) {
                    //Testen auf Turm
                    if(spielfeld[yAxis][xAxis + 1].equals("|") || spielfeld[yAxis][xAxis + 1].equals("#")) {
                        this.Death();
                    }
                }
                if ((xAxis + 1) < spielfeld[0].length
                        && !spielfeld[yAxis][xAxis + 1].equals("*")
                        && !spielfeld[yAxis][xAxis + 1].equals("/")
                        && !spielfeld[yAxis][xAxis].equals("*")
                        && !spielfeld[yAxis][xAxis].equals("/")) {
                    spielfeld[yAxis][xAxis] = spielfeld[yAxis][xAxis + 1]; //Icons eins nach links verschieben
                }
                if(spielfeld[yAxis][spielfeld[0].length-2].equals("#") || spielfeld[yAxis][spielfeld[0].length - 2].equals("|")) {
                    spielfeld[yAxis][spielfeld[0].length-1] = ".";         //"." in letzter Spalte - entfernt die Türme
                }
            }
        }
        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }

    //KeyEventImport Boolean Value
    public static void setSpaceIsPressed(){
        SpaceIsPressed = true;
    }

    //Spieler
    public String[][] Player(String[][] spielfeld) {
        this.spielfeld = spielfeld;

        if(SpaceIsPressed && spieleryAxis > 0){
            if(!spielfeld[spieleryAxis-1][spielfeld[0].length/2-1].equals("#") || !spielfeld[spieleryAxis-1][spielfeld[0].length/2-2].equals("#")) {
                spieleryAxis -= 1; //Aufwärtsbewegung des Spielers
                SpaceIsPressed = false;
            }
            else {
                this.Death();
            }
        }
        else if(spieleryAxis < spielfeld.length-2) { //Abwärtsbewegung des Spielers
            if(!spielfeld[spieleryAxis+1][spielfeld[0].length/2-1].equals("#") || !spielfeld[spieleryAxis+1][spielfeld[0].length/2-2].equals("#")) {
                spieleryAxis++; //Die spieleryAxis ist von oben nach unten; ++ bedeutet, um eins nach unten
            }
            else {
                this.Death();
            }
        }

        //Löschen des bisherigen Spielers - Alter Spieler ist über dem Aktuellen
        if((spieleryAxis-1) >= 0 && spielfeld[spieleryAxis-1][spielfeld[0].length/2].equals("*")) {
            spielfeld[spieleryAxis-1][spielfeld[0].length/2] = ".";
        }
        if(spieleryAxis >= 0 && spielfeld[spieleryAxis][spielfeld[0].length/2-1].equals("/")) {
            spielfeld[spieleryAxis][spielfeld[0].length/2-1] = ".";
        }
        //Löschen des bisherigen Spielers - Alter Spieler ist unter dem Aktuellen
        if((spieleryAxis+1) <= spielfeld.length-1 && spielfeld[spieleryAxis+1][spielfeld[0].length/2].equals("*")) {
            spielfeld[spieleryAxis+1][spielfeld[0].length/2] = ".";
        }
        if((spieleryAxis+2) <= spielfeld.length-1 && spielfeld[spieleryAxis+2][spielfeld[0].length/2-1].equals("/")) {
            spielfeld[spieleryAxis+2 ][spielfeld[0].length/2-1] = ".";
        }
        //Abfrage von dem #-Turm über einem für den Score
        if(isTowerAbove(spielfeld)) {
            Score++;
        }

        //Darstellung des aktuellen Spielers
        spielfeld[spieleryAxis][spielfeld[0].length/2] = "*";
        spielfeld[spieleryAxis+1][spielfeld[0].length/2-1] = "/";


        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }

    public boolean isTowerAbove(String[][] spielfeld) {
        int spielerHoehe = spieleryAxis;
        while(spielerHoehe > 0) {
            if(spielfeld[spielerHoehe][spielfeld[0].length/2-1].equals("#")) {
                return true;
            }
            else {
                spielerHoehe--;
            }
        }
        return false;
    }
    public void Death() {
        this.DeathAnimation(spielfeld);
        System.exit(69420);
    }

    public void DeathAnimation(String[][] spielfeld) {
        this.spielfeld = spielfeld;

        for(int i = 0; i < 20; i++) {
            System.out.println(" ");
        }
        System.out.println(ANSI_RED + "                         *" + ANSI_RESET );
        System.out.println(ANSI_RED + "                        / " + ANSI_RESET );
        for(int i = 0; i < 20; i++) {
            System.out.println(" ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("You're Dead. ");
        System.out.println("Score: " + Score);
    }
}