package com.company;
import java.io.*;
import java.util.Random;

public class Game {
    //Attribute
    private String[][][] spielfeld;
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
    public String[][][] CreateSpielfeld() {
        //Erstellen des Spielfeldes
        spielfeld = new String[10][50][3];
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for(int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                for(int Layer = 0;Layer < spielfeld[0][0].length;Layer++) {
                    //Layer 0: Bird, Layer 1: Tower, Layer 2: Background
                    //Bird und Tower Layer bekommen das (später) nicht sichtbare Zeichen "%"
                    if(Layer < 2) {
                        spielfeld[yAxis][xAxis][Layer] = "%";
                    }
                    //BackgroundLayer
                    if(Layer == 2) {
                        int wahrscheinlichkeitWolke = random.nextInt(15);
                        if (wahrscheinlichkeitWolke == 0) {
                            spielfeld[yAxis][xAxis][2] = "~";
                            if ((xAxis + 1) < spielfeld[0].length - 1 && yAxis < spielfeld.length/1.5) {
                                spielfeld[yAxis][xAxis + 1][2] = "~";
                            }
                        }
                        else if (spielfeld[yAxis][xAxis][2] == null) {
                            spielfeld[yAxis][xAxis][2] = ".";
                        }
                    }
                }
            }
        }
        return spielfeld;
    }

    // Spielfeld Ausgabe
    public void PrintSpielfeld(String[][][] spielfeld) {
        this.spielfeld = spielfeld;

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(ANSI_WHITE + " __________________________________________________");
        System.out.print(ANSI_WHITE + "|");
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                for (int Layer = 0; Layer < spielfeld[0][0].length; Layer++) {
                    if(!spielfeld[yAxis][xAxis][Layer].equals("%")) {
                        //Layer 0: Bird, Layer 1: Tower, Layer 2: Background, % bedeutet leer
                        switch (spielfeld[yAxis][xAxis][Layer]) {
                            case ".":
                                if(spielfeld[yAxis][xAxis][0].equals("%") && spielfeld[yAxis][xAxis][1].equals("%")) {
                                    System.out.print(ANSI_CYAN + spielfeld[yAxis][xAxis][2] + ANSI_RESET); //Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "~":
                                if(spielfeld[yAxis][xAxis][0].equals("%") && spielfeld[yAxis][xAxis][1].equals("%")) {
                                    System.out.print(spielfeld[yAxis][xAxis][2]); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "#", "|":
                                if(spielfeld[yAxis][xAxis][0].equals("%")) {
                                    System.out.print(ANSI_GREEN + spielfeld[yAxis][xAxis][1] + ANSI_RESET); //Türme NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "*", "/":
                                System.out.print(ANSI_YELLOW + spielfeld[yAxis][xAxis][0] + ANSI_RESET); //Spieler IMMER ANZEIGEN
                                break;
                            default:
                                System.out.print(ANSI_PURPLE + spielfeld[yAxis][xAxis][Layer] + ANSI_RESET); //Sollte eigentlich nicht vorkommen
                                break;
                        }
                    }
                }
            }
            if(yAxis == spielfeld.length-1){
                System.out.println(ANSI_WHITE + "|"  + ANSI_RESET + ANSI_GREEN + " Score: " + ANSI_RESET + ANSI_CYAN + Score + ANSI_RESET);
            }
            else {
                System.out.println(ANSI_WHITE + "|" + ANSI_RESET);
                System.out.print(ANSI_WHITE + "|" + ANSI_RESET);
            }
        }
        System.out.println(ANSI_WHITE + " ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯ " + ANSI_RESET);
    }

    // Erstellen eines Turmes
    public String[][][] CreateTower(String[][][] spielfeld) {
        this.spielfeld = spielfeld;

        int TurmOben = random.nextInt(5);
        int zufallszahl = random.nextInt(3);
        int TurmUnten = TurmOben + 4 + zufallszahl;

        spielfeld[TurmOben][spielfeld[0].length - 1][1] = "#";
        while (TurmOben > 0) {
            spielfeld[TurmOben - 1][spielfeld[0].length - 1][1] = "|";
            TurmOben--;
        }
        if(TurmUnten <= spielfeld.length) {
            spielfeld[TurmUnten][spielfeld[0].length - 1][1] = "#";
            while ((TurmUnten + 1) != spielfeld.length) {
                spielfeld[TurmUnten + 1][spielfeld[0].length - 1][1] = "|";
                TurmUnten++;
            }
        }

        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }

    //Spielfeld nach Links bewegen
    public String[][][] MoveMap(String[][][] spielfeld) {
        this.spielfeld = spielfeld;
        for (int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                //Testen auf Spieler - Kollision mit Turm
                if(spielerYAxis < spielfeld.length
                        && spielfeld[spielerYAxis][spielfeld[0].length/2][1].equals("#")
                        || spielfeld[spielerYAxis][spielfeld[0].length/2][1].equals("|")
                        || spielfeld[spielerYAxis+1][(spielfeld[0].length/2)-1][1].equals("#")
                        || spielfeld[spielerYAxis+1][(spielfeld[0].length/2)-1][1].equals("|")) {
                    this.Death();
                }
                //Feld verschieben
                if ((xAxis + 1) < spielfeld[0].length) {
                    spielfeld[yAxis][xAxis][1] = spielfeld[yAxis][xAxis+1][1]; //Icons eins nach links verschieben
                }
                //In letzte Spalte leeren bilden
                if(spielfeld[yAxis][spielfeld[0].length-2][1].equals("#") || spielfeld[yAxis][spielfeld[0].length - 2][1].equals("|") || spielfeld[yAxis][spielfeld[0].length - 2][1].equals("~")) {
                    spielfeld[yAxis][spielfeld[0].length-1][1] = "%";         //"." in letzter Spalte - entfernt die Türme und die Wolken, die sich unendlich bilden
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
    public String[][][] Player(String[][][] spielfeld) {
        this.spielfeld = spielfeld;

        if(SpaceIsPressed && spielerYAxis > 0){
            this.PrintSpielfeld(spielfeld);
            spielerYAxis--; //Aufwärtsbewegung des Spielers
            SpaceIsPressed = false; //Zurücksetzen
        }
        else if(spielerYAxis < spielfeld.length-2) { //Abwärtsbewegung des Spielers
            spielerYAxis++; //Die spielerYAxis ist von oben nach unten; ++ bedeutet, um eins nach unten
        }

        spielfeld = this.deleteOldPlayer(spielfeld);

        //Abfrage von dem #-Turm über einem für den Score
        if(isTowerAbove(spielfeld)) {
            Score++;
        }

        //Darstellung des aktuellen Spielers
        spielfeld[spielerYAxis][spielfeld[0].length/2][0] = "*";
        spielfeld[spielerYAxis+1][spielfeld[0].length/2-1][0] = "/";

        //Berührt der Spieler einen Turm
        if(!spielfeld[spielerYAxis][spielfeld[0].length/2][1].equals("%") && !spielfeld[spielerYAxis+1][spielfeld[0].length/2-1][1].equals("%")) {
            this.Death();
        }

        this.PrintSpielfeld(spielfeld);
        return spielfeld;
    }

    public String[][][] deleteOldPlayer(String[][][] spielfeld) {
        //Löschen des bisherigen Spielers - Alter Spieler ist über dem Aktuellen
        if((spielerYAxis-1) >= 0 && spielfeld[spielerYAxis-1][spielfeld[0].length/2][0].equals("*")) {
            spielfeld[spielerYAxis-1][spielfeld[0].length/2][0] = "%";
        }
        if(spielerYAxis >= 0 && spielfeld[spielerYAxis][spielfeld[0].length/2-1][0].equals("/")) {
            spielfeld[spielerYAxis][spielfeld[0].length/2-1][0] = "%";
        }
        //Löschen des bisherigen Spielers - Alter Spieler ist unter dem Aktuellen
        if((spielerYAxis+1) <= spielfeld.length-1 && spielfeld[spielerYAxis+1][spielfeld[0].length/2][0].equals("*")) {
            spielfeld[spielerYAxis+1][spielfeld[0].length/2][0] = "%";
        }
        if((spielerYAxis+2) <= spielfeld.length-1 && spielfeld[spielerYAxis+2][spielfeld[0].length/2-1][0].equals("/")) {
            spielfeld[spielerYAxis+2 ][spielfeld[0].length/2-1][0] = "%";
        }
        return spielfeld;
    }

    public boolean isTowerAbove(String[][][] spielfeld) {
        int spielerHoehe = spielerYAxis;
        while(spielerHoehe > 0) {
            if(spielfeld[spielerHoehe][spielfeld[0].length/2-1][1].equals("#")) {
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
            String importScoreFiltered = importScore.replace("\n", "").replace("\r", "");
            HighScore = Integer.parseInt(importScoreFiltered);
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