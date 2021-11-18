package com.company;

public class Main {
    public static void main(String[] args) {
        Game GameMechanik = new Game();
        KeyEventListener.KeyEventMain();
        //Erstellen
        String[][] Spielfeld = GameMechanik.CreateSpielfeld();
        Spielfeld = GameMechanik.Player(Spielfeld);


        for(int i = 0; i<100000;i++) {
            Spielfeld = GameMechanik.CreateTower(Spielfeld);
            for(int j = 0; j<16;j++) {
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
    }
}
        /*
        spielfeld[0].length 50 x
        spielfeld.length 10 y
        spielfeld[y][x]
         */



