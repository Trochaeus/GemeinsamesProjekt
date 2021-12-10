package com.company;
import java.io.*;
import java.util.Random;

public class Game {
    //Objekte
    Random random = new Random();

    //Attribute
    private String[][][] gameBoard = new String[10][50][3];
    private final int gameBoardXLength = gameBoard[0].length;
    private final int gameBoardYLength = gameBoard.length;


    private int spielerYAxis = 5;
    private static boolean spaceIsPressed;
    public static String spaceStatistics = "";
    private int Score = 0;
    //Farben
    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";

    // gameBoard erstellen
    public String[][][] createGameBoard() {
        //Erstellen des gameBoards
        for(int yAxis = 0; yAxis < gameBoardYLength; yAxis++) {
            for(int xAxis = 0; xAxis < gameBoardXLength; xAxis++) {
                for(int Layer = 0;Layer < gameBoard[0][0].length;Layer++) {
                    //Layer 0: Bird, Layer 1: Tower, Layer 2: Background
                    //Bird und Tower Layer bekommen das (später) nicht sichtbare Zeichen "%"
                    if(Layer < 2) {
                        gameBoard[yAxis][xAxis][Layer] = "%";
                    }
                    //BackgroundLayer
                    if(Layer == 2) {
                        int wahrscheinlichkeitWolke = random.nextInt(15);
                        if (wahrscheinlichkeitWolke == 0) {
                            gameBoard[yAxis][xAxis][2] = "~";
                            if ((xAxis + 1) < gameBoardXLength - 1 && yAxis < gameBoardYLength/1.5) {
                                gameBoard[yAxis][xAxis + 1][2] = "~";
                            }
                        }
                        else if (gameBoard[yAxis][xAxis][2] == null) {
                            gameBoard[yAxis][xAxis][2] = ".";
                        }
                    }
                }
            }
        }
        return gameBoard;
    }

    // Erstellen eines Turmes
    public String[][][] createTower(String[][][] gameBoard) {
        this.gameBoard = gameBoard;

        int TurmOben = random.nextInt(4);
        int zufallszahl = random.nextInt(3);
        int TurmUnten = TurmOben + 4 + zufallszahl;

        gameBoard[TurmOben][gameBoardXLength - 1][1] = "#";
        while (TurmOben > 0) {
            gameBoard[TurmOben - 1][gameBoardXLength - 1][1] = "|";
            TurmOben--;
        }
        if(TurmUnten < gameBoardYLength) {
            gameBoard[TurmUnten][gameBoardXLength - 1][1] = "+"; //NORMALERWEISE EIN #
            while ((TurmUnten + 1) != gameBoardYLength) {
                gameBoard[TurmUnten + 1][gameBoardXLength - 1][1] = "|";
                TurmUnten++;
            }
        }

        GUI.rePrintGUI(gameBoard,Score);
        return gameBoard;
    }

    //Erstellen einer ItemBox
    public String[][][] createItemBox(String[][][] gameBoard) {
        this.gameBoard = gameBoard;
        int wahrscheinlichkeitItem = random.nextInt(15); //Wahrscheinlichkeit von 1:10
        int itemHeight = random.nextInt(gameBoardYLength);
        if (wahrscheinlichkeitItem == 0 && gameBoard[itemHeight][gameBoardXLength - 1][1].equals("%")) {
            gameBoard[itemHeight][gameBoardXLength - 1][1] = "?";
        }
        return gameBoard;
    }


    //gameBoard nach Links bewegen
    public String[][][] moveMap(String[][][] gameBoard) {
        this.gameBoard = gameBoard;
        //boolean isTouchingItemBox = false;

        for (int yAxis = 0; yAxis < gameBoardYLength; yAxis++) {
            for (int xAxis = 0; xAxis < gameBoardXLength; xAxis++) {
                //Testen auf Spieler - Kollision mit Turm
                if(spielerYAxis < gameBoardYLength
                        && gameBoard[spielerYAxis][gameBoardXLength/2][1].equals("#")
                        || gameBoard[spielerYAxis][gameBoardXLength/2][1].equals("|")
                        || gameBoard[spielerYAxis][gameBoardXLength/2][1].equals("+")
                        || gameBoard[spielerYAxis+1][(gameBoardXLength/2)-1][1].equals("#")
                        || gameBoard[spielerYAxis+1][(gameBoardXLength/2)-1][1].equals("|")
                        || gameBoard[spielerYAxis+1][(gameBoardXLength/2)-1][1].equals("+")) {
                    this.death();
                }
                //Feld verschieben
                if ((xAxis + 1) < gameBoardXLength) {
                    gameBoard[yAxis][xAxis][1] = gameBoard[yAxis][xAxis+1][1]; //Icons eins nach links verschieben
                }
                //In letzte Spalte leere Felder bilden gegen unendliche Wolken, Türme etc
                switch(gameBoard[yAxis][gameBoardXLength-2][1]) {
                    case "#":
                        if(gameBoard[yAxis][gameBoardXLength - 1][1].equals("#")) {
                            gameBoard[yAxis][gameBoardXLength - 1][1] = "%";
                        }
                        break;
                    case "+":
                        if(gameBoard[yAxis][gameBoardXLength - 1][1].equals("+")) {
                            gameBoard[yAxis][gameBoardXLength - 1][1] = "%";
                        }
                        break;
                    case "|":
                        if(gameBoard[yAxis][gameBoardXLength - 1][1].equals("|")) {
                            gameBoard[yAxis][gameBoardXLength - 1][1] = "%";
                        }
                        break;
                    case "~":
                        if(gameBoard[yAxis][gameBoardXLength - 1][2].equals("~")) {
                            gameBoard[yAxis][gameBoardXLength - 1][2] = "%";
                        }
                        break;
                    case "?":
                        if(gameBoard[yAxis][gameBoardXLength - 1][1].equals("?")) {
                            gameBoard[yAxis][gameBoardXLength - 1][1] = "%";
                        }
                        break;
                }
                if(spielerYAxis < gameBoardYLength
                        && gameBoard[spielerYAxis][gameBoardXLength/2][1].equals("?")
                        || gameBoard[spielerYAxis+1][(gameBoardXLength/2)-1][1].equals("?")) {
                    TouchingItem();
                }
            }
        }

        //Abfrage von dem #-Turm über einem für den Score
        if(isTowerAbove(gameBoard)) {
            Score++;
            new Thread(new Runnable() {
                @Override public void run() {
                    // do stuff in this thread
                    try {playClip.playTheClip(new File("C:\\Users\\Public\\Documents\\FlappyBirdImages\\pling.wav"));
                    } catch (Exception e) {e.printStackTrace();}
                }
            }).start();
        }
        GUI.rePrintGUI(gameBoard,Score);
        return gameBoard;
    }
    public void TouchingItem(){
        //Entfernen der ItemBox
        if(gameBoard[spielerYAxis][gameBoardXLength/2][1].equals("?")) {
            gameBoard[spielerYAxis][gameBoardXLength/2][1] = "%";
        }
        else if(spielerYAxis < gameBoardYLength && gameBoard[spielerYAxis+1][(gameBoardXLength/2)-1][1].equals("?")) {
            gameBoard[spielerYAxis + 1][gameBoardXLength / 2 - 1][1] = "%";
        }

        //Effekte
        int wahrscheinlichkeitGutesItem = random.nextInt(4); //Wahrscheinlichkeit von 25:75
        if (wahrscheinlichkeitGutesItem == 0) {
            //Negativer Effekt
            GUI.doDecreasedView = true;
            GUI.doItemMessage = 2; // 0 ist keine Message, 1 ist positiv, 2 ist negativ
        }
        else {
            //Positiver Effekt
            Score += 2;
            GUI.doItemMessage = 1; // 0 ist keine Message, 1 ist positiv, 2 ist negativ
            //Soundeffekt
            Thread myThread = new Thread(new Runnable() {
                @Override public void run() {
                    // do stuff in this thread
                    try {playClip.playTheClip(new File("C:\\Users\\Public\\Documents\\FlappyBirdImages\\item.wav"));
                    } catch (Exception e) {e.printStackTrace();}
                }
            });
            myThread.start();
            //myThread.stop();

        }
    }

    public boolean isTowerAbove(String[][][] gameBoard) {
        int spielerHoehe = spielerYAxis;
        while(spielerHoehe >= 0) {
            if(gameBoard[spielerHoehe][gameBoardXLength/2][1].equals("#")) {
                return true;
            }
            else {
                spielerHoehe--;
            }
        }
        return false;
    }

    //Import Boolean Value
    public static void setSpaceIsPressed(){
        spaceIsPressed = true;
    }

    //Spieler
    public String[][][] player(String[][][] gameBoard) {
        this.gameBoard = gameBoard;

        if(spaceIsPressed && spielerYAxis > 0){
            GUI.rePrintGUI(gameBoard,Score);
            spielerYAxis--; //Aufwärtsbewegung des Spielers
            spaceStatistics += "x";
            spaceIsPressed = false; //Zurücksetzen
        }
        else if(spielerYAxis < gameBoardYLength-2) { //Abwärtsbewegung des Spielers
            spielerYAxis++; //Die spielerYAxis ist von oben nach unten; ++ bedeutet, um eins nach unten
            spaceStatistics += " ";
        }

        gameBoard = this.deleteOldPlayer(gameBoard);

        //Darstellung des aktuellen Spielers
        gameBoard[spielerYAxis][gameBoardXLength/2][0] = "*";
        gameBoard[spielerYAxis+1][gameBoardXLength/2-1][0] = "/";

        //Berührt der Spieler einen Turm
        if(!gameBoard[spielerYAxis][gameBoardXLength/2][1].equals("%") && !gameBoard[spielerYAxis+1][gameBoardXLength/2-1][1].equals("%")) {
            this.death();
        }

        GUI.rePrintGUI(gameBoard,Score);
        return gameBoard;
    }

    public String[][][] deleteOldPlayer(String[][][] gameBoard) {
        //Löschen des bisherigen Spielers - Alter Spieler ist über dem Aktuellen
        if((spielerYAxis-1) >= 0 && gameBoard[spielerYAxis-1][gameBoardXLength/2][0].equals("*")) {
            gameBoard[spielerYAxis-1][gameBoardXLength/2][0] = "%";
        }
        if(spielerYAxis >= 0 && gameBoard[spielerYAxis][gameBoardXLength/2-1][0].equals("/")) {
            gameBoard[spielerYAxis][gameBoardXLength/2-1][0] = "%";
        }
        //Löschen des bisherigen Spielers - Alter Spieler ist unter dem Aktuellen
        if((spielerYAxis+1) <= gameBoardYLength-1 && gameBoard[spielerYAxis+1][gameBoardXLength/2][0].equals("*")) {
            gameBoard[spielerYAxis+1][gameBoardXLength/2][0] = "%";
        }
        if((spielerYAxis+2) <= gameBoardYLength-1 && gameBoard[spielerYAxis+2][gameBoardXLength/2-1][0].equals("/")) {
            gameBoard[spielerYAxis+2 ][gameBoardXLength/2-1][0] = "%";
        }
        return gameBoard;
    }

    public void death() {
        new Thread(new Runnable() {
            @Override public void run() {
                // do stuff in this thread
                try {playClip.playTheClip(new File("C:\\Users\\Public\\Documents\\FlappyBirdImages\\gameover.wav"));
                } catch (Exception e) {e.printStackTrace();}
            }
        }).start();
        GUI.DeathScreenGUI(gameBoard,Main.difficulty,Score,this.highScore(Score));
        System.out.println(ANSI_PURPLE + "Space Statistics: " + ANSI_CYAN + spaceStatistics + ANSI_RESET);
        System.exit(69420);
    }

    public int highScore(int score) {
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
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();

                while (line != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                    line = bufferedReader.readLine();
                }
                importScore = stringBuilder.toString();
            } finally {
                bufferedReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String importScoreFiltered = importScore.replace("\n", "").replace("\r", "");
            HighScore = Integer.parseInt(importScoreFiltered);
        }
        catch (Exception e) {
            System.out.println("[Error] Ungültiger Highscore");
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
}