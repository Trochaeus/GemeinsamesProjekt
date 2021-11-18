package com.company;
import java.io.*;
import java.util.Random;

public class Game {
    //Attribute
    private String[][] spielfeld;
    Random random = new Random();
    private int spielerYAxis = 5;
    private static boolean SpaceIsPressed;
    public static boolean StartGame;
    private int Score = 0;


    //Farben
    public final String ANSI_RESET = "\u001B[0m";
    //public final String ANSI_BLACK = "\u001B[30m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";
    public final String ANSI_WHITE = "\u001B[37m";


    // Spielfeld erstellen
    public String[][] CreateSpielfeld() {
        //Erstellen des Spielfeldes
        spielfeld = new String[10][50];
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                int wahrscheinlichkeitWolke = random.nextInt(15);
                if(wahrscheinlichkeitWolke == 0 && spielfeld[yAxis][xAxis] == null && yAxis < spielfeld.length/2) {
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
                    case "." -> System.out.print(ANSI_CYAN + spielfeld[yAxis][xAxis] + ANSI_RESET); //Hintergrund
                    case "#", "|" -> System.out.print(ANSI_GREEN + spielfeld[yAxis][xAxis] + ANSI_RESET); //Türme
                    case "*", "/" -> System.out.print(ANSI_YELLOW + spielfeld[yAxis][xAxis] + ANSI_RESET); //Spieler
                    case "~" -> System.out.print(spielfeld[yAxis][xAxis]); //Wolken/Hintergrund
                    default -> System.out.print(ANSI_PURPLE + spielfeld[yAxis][xAxis] + ANSI_RESET); //Sollte eigentlich nicht vorkommen
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
        int zufallszahl = random.nextInt(3);
        int TurmUnten = TurmOben+4;

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

                //Testen auf Spieler - Kollision mit Turm
                if ((xAxis + 1) < spielfeld[0].length && spielfeld[yAxis][xAxis].equals("*") || spielfeld[yAxis][xAxis].equals("/")) {
                    //Testen auf Turm
                    if(spielfeld[yAxis][xAxis + 1].equals("|") || spielfeld[yAxis][xAxis + 1].equals("#")) {
                        this.Death();
                    }
                }
                //Wolken verschieben (Hinter den Spieler)
                if ((xAxis + 1) < spielfeld[0].length && (xAxis-1) > 0
                        && spielfeld[yAxis][xAxis + 1].equals("~")) {
                    if(spielfeld[yAxis][xAxis].equals("*") || spielfeld[yAxis][xAxis].equals("/")) {
                        if(spielfeld[yAxis][xAxis-1].equals("|") && spielfeld[yAxis][xAxis-1].equals("#"))
                        spielfeld[yAxis][xAxis - 1] = "~"; //Wolke eins hinter den Spieler verschieben
                    }
                }

                //Feld verschieben
                if ((xAxis + 1) < spielfeld[0].length
                        && !spielfeld[yAxis][xAxis + 1].equals("*")
                        && !spielfeld[yAxis][xAxis + 1].equals("/")
                        && !spielfeld[yAxis][xAxis].equals("*")
                        && !spielfeld[yAxis][xAxis].equals("/")) {
                    spielfeld[yAxis][xAxis] = spielfeld[yAxis][xAxis + 1]; //Icons eins nach links verschieben
                }


                //In letzter Spalte neue Punkte bilden
                if(spielfeld[yAxis][spielfeld[0].length-2].equals("#") || spielfeld[yAxis][spielfeld[0].length - 2].equals("|") || spielfeld[yAxis][spielfeld[0].length - 2].equals("~")) {
                    spielfeld[yAxis][spielfeld[0].length-1] = ".";         //"." in letzter Spalte - entfernt die Türme und die Wolken, die sich unendlich bilden
                }
                //Bilden neuer Wolken
                if(!spielfeld[yAxis][spielfeld[0].length-2].equals("#") && !spielfeld[yAxis][spielfeld[0].length - 2].equals("|") && !spielfeld[yAxis][spielfeld[0].length - 2].equals("~") && !spielfeld[yAxis][spielfeld[0].length-3].equals("#") && !spielfeld[yAxis][spielfeld[0].length - 3].equals("|") && !spielfeld[yAxis][spielfeld[0].length - 3].equals("~")) {
                    int zufallszahl = random.nextInt(300);
                    if(zufallszahl == 0 && yAxis < spielfeld.length/2) {
                        spielfeld[yAxis][spielfeld[0].length - 2] = "~";
                        spielfeld[yAxis][spielfeld[0].length - 3] = "~";
                    }
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

        if(SpaceIsPressed && spielerYAxis > 0){
            this.PrintSpielfeld(spielfeld);
            if(!spielfeld[spielerYAxis-1][spielfeld[0].length/2].equals("#") && !spielfeld[spielerYAxis-1][spielfeld[0].length/2-1].equals("#")) {
                spielerYAxis--; //Aufwärtsbewegung des Spielers
                SpaceIsPressed = false; //Zurücksetzen
            }
            else {
                this.Death();
            }
        }
        else if(spielerYAxis < spielfeld.length-2) { //Abwärtsbewegung des Spielers
            if(!spielfeld[spielerYAxis+1][spielfeld[0].length/2].equals("#") && !spielfeld[spielerYAxis+1][spielfeld[0].length/2-1].equals("#")) {
                spielerYAxis++; //Die spielerYAxis ist von oben nach unten; ++ bedeutet, um eins nach unten
            }
            else {
                this.Death();
            }
        }

        spielfeld = this.deleteOldPlayer(spielfeld);

        //Abfrage von dem #-Turm über einem für den Score
        if(isTowerAbove(spielfeld)) {
            Score++;
        }

        //Darstellung des aktuellen Spielers
        spielfeld[spielerYAxis][spielfeld[0].length/2] = "*";
        spielfeld[spielerYAxis+1][spielfeld[0].length/2-1] = "/";


        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }

    public String[][] deleteOldPlayer(String[][] spielfeld) {
        //Löschen des bisherigen Spielers - Alter Spieler ist über dem Aktuellen
        if((spielerYAxis-1) >= 0 && spielfeld[spielerYAxis-1][spielfeld[0].length/2].equals("*")) {
            spielfeld[spielerYAxis-1][spielfeld[0].length/2] = ".";
        }
        if(spielerYAxis >= 0 && spielfeld[spielerYAxis][spielfeld[0].length/2-1].equals("/")) {
            spielfeld[spielerYAxis][spielfeld[0].length/2-1] = ".";
        }
        //Löschen des bisherigen Spielers - Alter Spieler ist unter dem Aktuellen
        if((spielerYAxis+1) <= spielfeld.length-1 && spielfeld[spielerYAxis+1][spielfeld[0].length/2].equals("*")) {
            spielfeld[spielerYAxis+1][spielfeld[0].length/2] = ".";
        }
        if((spielerYAxis+2) <= spielfeld.length-1 && spielfeld[spielerYAxis+2][spielfeld[0].length/2-1].equals("/")) {
            spielfeld[spielerYAxis+2 ][spielfeld[0].length/2-1] = ".";
        }
        return spielfeld;
    }

    public boolean isTowerAbove(String[][] spielfeld) {
        int spielerHoehe = spielerYAxis;
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
        //Leeren des Fensters
        for(int i = 0; i < 20; i++) {
            System.out.println(" ");
        }
        //Darstellung des gestorbenen Spielers
        System.out.println(ANSI_RED + "                         *" + ANSI_RESET );
        System.out.println(ANSI_RED + "                        / " + ANSI_RESET );
        //Animation
        for(int i = 0; i < 20; i++) {
            System.out.println(" ");
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Scores
        System.out.println(ANSI_RED + "You're Dead. " + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Score: " + ANSI_RESET + ANSI_CYAN + Score + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Highscore: " + ANSI_RESET + ANSI_CYAN + this.HighScore(Score) + ANSI_RESET);
        System.exit(69420);
    }

    public int HighScore(int score) {
        this.Score = score;
        //Datei erstellen, wenn sie noch nicht existiert
        try {
            File generateFile = new File("C:\\Users\\Public\\Documents\\HighScore.txt");
            if (generateFile.createNewFile()) {
                System.out.println(ANSI_YELLOW + "File created: 'C:\\Users\\Public\\Documents\\" + generateFile.getName() + "'" + ANSI_RESET); //else würde ausgeben, dass es bereits existiert
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
        }
        //Inhalt der Datei lesen - Import des bisherigen HighScores
        String importScore = "-1";
        int HighScore = -1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Public\\Documents\\HighScore.txt"))) {
            try {
                StringBuilder sb = new StringBuilder();
                String line = bufferedReader.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = bufferedReader.readLine();
                }
                importScore = sb.toString();
            } finally {
                bufferedReader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String importScoreFiltered = "";
            if(importScore.matches("\\d{1}")) {
                importScoreFiltered += importScore.charAt(0);
            }
            else if(importScore.matches("\\d{2}")) {
                importScoreFiltered += importScore.charAt(0) + importScore.charAt(1);
            }
            else if(importScore.matches("\\d{3}")) {
                importScoreFiltered += importScore.charAt(0) + importScore.charAt(1) + importScore.charAt(2);
            }
            else if(importScore.matches("\\d{4}")) {
                importScoreFiltered += importScore.charAt(0) + importScore.charAt(1) + importScore.charAt(2) + importScore.charAt(3);
            }
            else if(importScore.matches("\\d{5}")) {
                importScoreFiltered += importScore.charAt(0) + importScore.charAt(1) + importScore.charAt(2) + importScore.charAt(3) + importScore.charAt(4);
            }
            else {
                importScoreFiltered += "-2";
            }
            HighScore = Integer.parseInt(importScoreFiltered);
            System.out.println("Int HighScore: " + HighScore);
        }
        catch (Exception e) {
            System.out.println("Error");
        }

        //In die Datei schreiben - Export HighScore
        if(Score > HighScore) {
            try {
                FileWriter writeFile = new FileWriter("C:\\Users\\Public\\Documents\\HighScore.txt");
                writeFile.write(String.valueOf(Score));
                writeFile.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
            }
            return Score;
        }
        else {
            return HighScore;
        }
    }

    public void StartMenu() {
        //Start Menü
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(ANSI_RED + "        #####  #      #####  #####  #####  #   #     ####   #  #####  #### " + ANSI_RESET);
        System.out.println(ANSI_YELLOW  + "       #      #      #   #  #   #  #   #   # #      #   #  #  #   #  #   # " + ANSI_RESET);
        System.out.println(ANSI_GREEN  + "      ###    #      #####  #####  #####    #       ####   #  #####  #   # " + ANSI_RESET);
        System.out.println(ANSI_BLUE  + "     #      #      #   #  #      #        #       #   #  #  # #    #   # " + ANSI_RESET);
        System.out.println(ANSI_PURPLE  + "    #      #####  #   #  #      #        #       ####   #  #   #  ####  " + ANSI_RESET);

        System.out.println(ANSI_BLUE + "                                                                      By me!" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "Info: Press 'Space' to jump!" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Press 's' to start!" + ANSI_RESET);
        System.out.println(" ");

        while(!StartGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StartGame = false;
    }
}