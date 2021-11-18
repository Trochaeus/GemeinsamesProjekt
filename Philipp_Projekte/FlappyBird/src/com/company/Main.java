package com.company;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Game GameMechanik = new Game();
        KeyEventListener.KeyEventMain();
        Random random = new Random();

        //Start Menü
        GameMechanik.StartMenu();

        //Erstellen
        String[][][] Spielfeld = GameMechanik.CreateSpielfeld();
        Spielfeld = GameMechanik.Player(Spielfeld);


        for(int i = 0; i<100000;i++) {
            Spielfeld = GameMechanik.CreateTower(Spielfeld);
            int zufallszahl = random.nextInt(7);
            for(int j = 0; j<(10+zufallszahl);j++) {
                Spielfeld = GameMechanik.MoveMap(Spielfeld);
                Spielfeld = GameMechanik.Player(Spielfeld);
                // Game pausieren, damit man es überhaupt spielen kann
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Du spielst zu viel. Du hast gewonnen. Aber war es das wirklich wert?");
    }
}
        /*
        spielfeld[0].length 50 x
        spielfeld.length 10 y
        spielfeld[y][x]
         */



