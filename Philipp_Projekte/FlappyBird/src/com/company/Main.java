package com.company;

import java.util.Random;


public class Main {
    static int difficulty = 4;

    public static void main(String[] args) {
        Game gameMechanik = new Game();
        Random random = new Random();
        //KeyEventListener.keyEventMain();
        //Warten, damit sich die GUI aufbauen kann
        try {Thread.sleep(600);} catch (InterruptedException e) {e.printStackTrace();}

        //Erstellen
        String[][][] gameBoard = gameMechanik.createGameBoard();
        gameBoard = gameMechanik.player(gameBoard);
        try {
            GUI.setGameBoard(gameBoard);
            GUI.importPictures();
            GUI.guiErstellen(0);
            GUI.startScreen(gameMechanik.highScore(0));
            GUI.difficultyScreen();
        } catch (Exception e) {
            System.out.println("Ein Fehler ist aufgetreten");
        }

        //Unendliche Schleife (da das Spiel erst mit dem Tod des Spielers endet)
        while(true) {
            gameBoard = gameMechanik.createTower(gameBoard);
            int zufallszahl = random.nextInt(7); //zufällige Abstandsänderung der Türme (Horizontal)
            for(int j = 0; j<(10+zufallszahl);j++) {
                gameBoard = gameMechanik.moveMap(gameBoard);
                gameBoard = gameMechanik.player(gameBoard);
                gameBoard = gameMechanik.createItemBox(gameBoard);
                // Game pausieren, damit man es überhaupt spielen kann - abhängig von Difficulty
                try {
                    switch(difficulty) {
                        case 1 -> Thread.sleep(300);
                        case 2 -> Thread.sleep(200);
                        case 3 -> Thread.sleep(150);
                        case 4 -> Thread.sleep(100);
                        case 5 -> Thread.sleep(75);
                        case 6 -> Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



