package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventListener extends JFrame implements KeyListener, ActionListener {
    private static String[][][] gameBoard;
    static final String newline = System.getProperty("line.separator");

    static JTextArea displayArea;
    static JTextField typingArea;

    public KeyEventListener() {
        super("Floppy Bird");
    }
    public static void keyEventMain() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error SetLookAndFeel");
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(KeyEventListener::createAndShowGUI);
    }
    private static void createAndShowGUI() {
        KeyEventListener frame = new KeyEventListener();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane();
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(425, 205));
        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    //Brauche ich nicht, kann es aber auch nicht löschen - abstrakt funktioniert nicht
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void actionPerformed(ActionEvent e) {} //War für den Button

    @Override
    public void keyPressed(KeyEvent e) {}

    public static void displayGame(String[][][] printgameBoard, int Score) {
        gameBoard = printgameBoard;
        switch(Main.difficulty) {
            case 1 -> typingArea.setText("Baby - Score: " + Score);
            case 2 -> typingArea.setText("Easy - Score: " + Score);
            case 3 -> typingArea.setText("Normal - Score: " + Score);
            case 4 -> typingArea.setText("Hard - Score: " + Score);
            case 5 -> typingArea.setText("Godmode - Score: " + Score);
            case 6 -> typingArea.setText("Botmode - Score: " + Score);
        }
    }

    public static void showMenu(){
        //Start Menü
        String[] banner = {"    #####  #      #####  #####  #####  #   #     ####   #  #####  ####  ",
                "   #      #      #   #  #   #  #   #   # #      #   #  #  #   #  #   #  ",
                "  ###    #      #   #  #####  #####    #       ####   #  #####  #   #   ",
                " #      #      #   #  #      #        #       #   #  #  # #    #   #    ",
                "#      #####  #####  #      #        #       ####   #  #   #  ####      "};

        String[] movingBanner = new String[5];
        System.arraycopy(banner, 0, movingBanner, 0, banner.length);

        for(int animation = 0; animation < 20; animation++) {
            displayArea.setText("");
            displayArea.append(newline);
            for(int zeile = 0; zeile < banner.length; zeile++) {
                movingBanner[zeile] = movingBanner[zeile].substring(1); //Ersten Character entfernen
                displayArea.append(movingBanner[zeile] + newline);
            }
            displayArea.append("                                              By me!" + newline);
            displayArea.append("Info: Press 'Space' to jump!" + newline);
            displayArea.append("Press 's' to start!" + newline);
            try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
        }

        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
        //Am Ende einen unbeweglichen Banner anzeigen
        displayArea.setText("");
        displayArea.append(newline);
        for (String zeile : banner) {
            displayArea.append(zeile + newline);
        }
        displayArea.append("                                              By me!" + newline);
        displayArea.append("Info: Press 'Space' to jump!" + newline);
        displayArea.append("Press 's' to start!" + newline);
    }

    public static void difficulty(){
        displayArea.setText("");
        //Schwierigkeit
        displayArea.append("Choose: " + newline);
        displayArea.append("  1 - Baby" + newline);
        displayArea.append("  2 - Easy" + newline);
        displayArea.append("  3 - Normal" + newline);
        displayArea.append("  4 - Hard" + newline);
        displayArea.append("  5 - Godmode" + newline);
        displayArea.append("  6 - Botmode" + newline);
    }



    public static void deathScreen(int SpielerYAxis, int Score, int HighScore) {
        //Fenster leeren
        displayArea.setText("");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < SpielerYAxis; i++) {
            displayArea.append(newline);
        }

        displayArea.append("                         " + (char)92 + "|/" + newline);
        displayArea.append("                        --0--" + newline);
        displayArea.append("                         /|" + (char)92 + newline);
        try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

        //Fenster leeren
        displayArea.setText("");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < SpielerYAxis-1; i++) {
            displayArea.append(newline);
        }

        displayArea.append("                         `` ,  " + newline);
        displayArea.append("                     ` . `.' ,'" + newline);
        displayArea.append("                      . `` ,'. " + (char)34 + newline);
        displayArea.append("                       ~ (   ~ -" + newline);
        displayArea.append("                          `~ '  " + newline);
        try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

        //Fenster leeren
        displayArea.setText("");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < SpielerYAxis-1; i++) {
            displayArea.append(newline);
        }
        displayArea.append("                         `` ,  " + newline);
        displayArea.append("                    . ','|` ` ." + newline);
        displayArea.append("                     .'  " + (char)34 + "  '" + newline);
        displayArea.append("                    ,   ' , '  `" + newline);
        displayArea.append("                          `~ '  " + newline);
        try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

        //Fenster leeren
        displayArea.setText("");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < gameBoard.length/2-2; i++) {
            displayArea.append(newline);
        }
        displayArea.append("                     You're Dead "+ newline);
        displayArea.append(newline);
        displayArea.append(newline);
        displayArea.append(newline);

        switch(Main.difficulty) {
            case 1 -> displayArea.append("Difficulty: Baby" +  newline);
            case 2 -> displayArea.append("Difficulty: Easy" +  newline);
            case 3 -> displayArea.append("Difficulty: Normal" +  newline);
            case 4 -> displayArea.append("Difficulty: Hard" +  newline);
            case 5 -> displayArea.append("Difficulty: Godmode" +  newline);
            case 6 -> displayArea.append("Difficulty: Botmode" +  newline);
        }

        displayArea.append("Your Score: "+ Score +  newline);
        displayArea.append("Your Highscore: "+ HighScore +  newline);

        try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}

    }
}



/*
//ASCII-GUI Ausgabe
        /*
        displayArea.setText("");
        for (int yAxis = 0; yAxis < gameBoard.length; yAxis++) {
            for (int xAxis = 0; xAxis < gameBoard[0].length; xAxis++) {
                for (int Layer = 0; Layer < gameBoard[0][0].length; Layer++) {
                    if (!gameBoard[yAxis][xAxis][Layer].equals("%")) {
                        //Layer 0: Bird, Layer 1: Tower, Layer 2: Background, % bedeutet leer
                        switch (gameBoard[yAxis][xAxis][Layer]) {
                            case ".":
                                if (gameBoard[yAxis][xAxis][0].equals("%") && gameBoard[yAxis][xAxis][1].equals("%")) {
                                    displayArea.append(" "); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "~":
                                if (gameBoard[yAxis][xAxis][0].equals("%") && gameBoard[yAxis][xAxis][1].equals("%")) {
                                    displayArea.append(gameBoard[yAxis][xAxis][2]); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "#", "|","+":
                                if (gameBoard[yAxis][xAxis][0].equals("%")) {
                                    if(gameBoard[yAxis][xAxis][1] == "+") {
                                        displayArea.append("#"); //Diese Ausnahme besteht, da ich in der neuen GUI eine unterscheidung zwischen den oberen und den unteren Türmen brauche
                                    }
                                    else {
                                        displayArea.append(gameBoard[yAxis][xAxis][1]); //Türme NUR ANZEIGEN, wenn alles davor leer ist
                                    }
                                }
                                break;
                            case "*", "/":
                                displayArea.append(gameBoard[yAxis][xAxis][0]); //Spieler IMMER ANZEIGEN
                                break;
                            default:
                                displayArea.append(gameBoard[yAxis][xAxis][Layer]); //Sollte eigentlich nicht vorkommen
                                break;
                        }
                    }
                }
            }
            displayArea.append(newline);
        }

        //Konsolen Ausgabe
        //Farben
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        for (int i = 0; i<20; i++) {
            System.out.println(" ");
        }

        for (int yAxis = 0; yAxis < gameBoard.length; yAxis++) {
            for (int xAxis = 0; xAxis < gameBoard[0].length; xAxis++) {
                for (int Layer = 0; Layer < gameBoard[0][0].length; Layer++) {
                    if (!gameBoard[yAxis][xAxis][Layer].equals("%")) {
                        //Layer 0: Bird, Layer 1: Tower, Layer 2: Background, % bedeutet leer
                        switch (gameBoard[yAxis][xAxis][Layer]) {
                            case ".":
                                if (gameBoard[yAxis][xAxis][0].equals("%") && gameBoard[yAxis][xAxis][1].equals("%")) {
                                    System.out.print(ANSI_BLUE + gameBoard[yAxis][xAxis][2] + ANSI_RESET); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "~":
                                if (gameBoard[yAxis][xAxis][0].equals("%") && gameBoard[yAxis][xAxis][1].equals("%")) {
                                    System.out.print(gameBoard[yAxis][xAxis][2]); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "#", "|","+":
                                if (gameBoard[yAxis][xAxis][0].equals("%")) {
                                    if(gameBoard[yAxis][xAxis][1] == "+") {
                                        System.out.print(ANSI_GREEN  + "#" + ANSI_RESET); //Diese Ausnahme besteht, da ich in der neuen GUI eine unterscheidung zwischen den oberen und den unteren Türmen brauche
                                    }
                                    else {
                                        System.out.print(ANSI_GREEN  + gameBoard[yAxis][xAxis][1] + ANSI_RESET); //Türme NUR ANZEIGEN, wenn alles davor leer ist
                                    }
                                }
                                break;
                            case "*", "/":
                                System.out.print(ANSI_YELLOW  + gameBoard[yAxis][xAxis][0] + ANSI_RESET); //Spieler IMMER ANZEIGEN
                                break;
                            default:
                                System.out.print(ANSI_PURPLE  + gameBoard[yAxis][xAxis][Layer] + ANSI_RESET); //Sollte eigentlich nicht vorkommen
                                break;
                        }
                    }
                }
            }
            System.out.println(" ");
        }
*/