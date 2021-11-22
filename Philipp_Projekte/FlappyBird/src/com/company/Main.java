package com.company;

import java.util.Random;

public class Main {
    static int Difficulty = 2;
    static boolean StartGame = false;

    public static void main(String[] args) {
        Game GameMechanik = new Game();
        KeyEventListener.KeyEventMain();
        Random random = new Random();

        //Start Menü
        GameMechanik.StartMenu();
        //Schwierigkeit
        KeyEventListener.Difficulty();
        while(!StartGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Erstellen
        String[][][] Spielfeld = GameMechanik.CreateSpielfeld();
        Spielfeld = GameMechanik.Player(Spielfeld);


        while(true) {
            Spielfeld = GameMechanik.CreateTower(Spielfeld);
            int zufallszahl = random.nextInt(7);
            for(int j = 0; j<(10+zufallszahl);j++) {
                Spielfeld = GameMechanik.MoveMap(Spielfeld);
                Spielfeld = GameMechanik.Player(Spielfeld);
                // Game pausieren, damit man es überhaupt spielen kann - abhängig von Difficulty
                try {
                    switch(Difficulty) {
                        case 1 -> Thread.sleep(300);
                        case 2 -> Thread.sleep(200);
                        case 3 -> Thread.sleep(150);
                        case 4 -> Thread.sleep(100);
                        case 5 -> Thread.sleep(75);
                        case 6 -> Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
        /*
        spielfeld[0].length 50 x
        spielfeld.length 10 y
        spielfeld[y][x]
         */



