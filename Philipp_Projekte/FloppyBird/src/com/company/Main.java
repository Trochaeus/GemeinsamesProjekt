package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        Game GameMechanik = new Game();
        String[][] Spielfeld = GameMechanik.CreateSpielfeld();
        //GameMechanik.PrintSpielfeld(Spielfeld);
        Spielfeld = GameMechanik.CreateTower(Spielfeld);
        Spielfeld = GameMechanik.MoveMap(Spielfeld);
        Spielfeld = GameMechanik.Player(Spielfeld);


        /*
        spielfeld[0].length 50 x
        spielfeld.length 10 y
        spielfeld[y][x]
         */
        System.out.println("TestBereich");


    }
}


