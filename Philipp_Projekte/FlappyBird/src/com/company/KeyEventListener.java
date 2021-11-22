package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventListener extends JFrame implements KeyListener, ActionListener {
    private static String[][][] spielfeld;
    static final String newline = System.getProperty("line.separator");
    static JTextArea displayArea;
    static JTextField typingArea;


    public KeyEventListener() {
        super("Floppy Bird");
    }
    public static void KeyEventMain() {
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

    public void keyTyped(KeyEvent e) {
        //
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == ' ') {
            Game.setSpaceIsPressed();
        }
        else if(e.getKeyChar() == 's') {
            typingArea.setText("");
            Game.StartGame = true;
        }
        else if(e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3' || e.getKeyChar() == '4' || e.getKeyChar() == '5' || e.getKeyChar() == '6') {
            Main.Difficulty = (int) e.getKeyChar()-48;
            Main.StartGame = true;
        }
        typingArea.setText("");
    }


    public void keyReleased(KeyEvent e) {
        //
    }

    //Handle the button click.
    public void actionPerformed(ActionEvent e) {
        displayArea.setText("");
        typingArea.setText("");
        typingArea.requestFocusInWindow();
    }

    public static void displayGame(String[][][] printSpielfeld, int Score) {
        spielfeld = printSpielfeld;
        switch(Main.Difficulty) {
            case 1 -> typingArea.setText("Baby - Score: " + Score);
            case 2 -> typingArea.setText("Easy - Score: " + Score);
            case 3 -> typingArea.setText("Normal - Score: " + Score);
            case 4 -> typingArea.setText("Hard - Score: " + Score);
            case 5 -> typingArea.setText("Godmode - Score: " + Score);
            case 6 -> typingArea.setText("Botmode - Score: " + Score);
        }
        displayArea.setText("");
        for (int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                for (int Layer = 0; Layer < spielfeld[0][0].length; Layer++) {
                    if (!spielfeld[yAxis][xAxis][Layer].equals("%")) {
                        //Layer 0: Bird, Layer 1: Tower, Layer 2: Background, % bedeutet leer
                        switch (spielfeld[yAxis][xAxis][Layer]) {
                            case ".":
                                if (spielfeld[yAxis][xAxis][0].equals("%") && spielfeld[yAxis][xAxis][1].equals("%")) {
                                    displayArea.append(" "); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "~":
                                if (spielfeld[yAxis][xAxis][0].equals("%") && spielfeld[yAxis][xAxis][1].equals("%")) {
                                    displayArea.append(spielfeld[yAxis][xAxis][2]); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "#", "|":
                                if (spielfeld[yAxis][xAxis][0].equals("%")) {
                                    displayArea.append(spielfeld[yAxis][xAxis][1]); //Türme NUR ANZEIGEN, wenn alles davor leer ist
                                }
                                break;
                            case "*", "/":
                                displayArea.append(spielfeld[yAxis][xAxis][0]); //Spieler IMMER ANZEIGEN
                                break;
                            default:
                                displayArea.append(spielfeld[yAxis][xAxis][Layer]); //Sollte eigentlich nicht vorkommen
                                break;
                        }
                    }
                }
            }
            displayArea.append(newline);
        }
    }

    public static void ShowMenu(){
        displayArea.setText(" ");
        //Start Menü
        displayArea.append(newline);
        displayArea.append("        #####  #      #####  #####  #####  #   #     ####   #  #####  ####  " + newline);
        displayArea.append("       #      #      #   #  #   #  #   #   # #      #   #  #  #   #  #   # " + newline);
        displayArea.append("      ###    #      #   #  #####  #####    #       ####   #  #####  #   # " + newline);
        displayArea.append("     #      #      #   #  #      #        #       #   #  #  # #    #   # " + newline);
        displayArea.append("    #      #####  #####  #      #        #       ####   #  #   #  ####  " + newline);
        displayArea.append("                                                                      By me!" + newline);

        displayArea.append("Info: Press 'Space' to jump!" + newline);
        displayArea.append("Press 's' to start!" + newline);
    }

    public static void Difficulty(){
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
        displayArea.setText(" ");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < SpielerYAxis; i++) {
            displayArea.append(newline);
        }

        displayArea.append("                         " + (char)92 + "|/" + newline);
        displayArea.append("                        --0--" + newline);
        displayArea.append("                         /|" + (char)92 + newline);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Fenster leeren
        displayArea.setText(" ");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < SpielerYAxis-1; i++) {
            displayArea.append(newline);
        }

        displayArea.append("                         `` ,  " + newline);
        displayArea.append("                     ` . `.' ,'" + newline);
        displayArea.append("                      . `` ,'. " + (char)34 + newline);
        displayArea.append("                       ~ (   ~ -" + newline);
        displayArea.append("                          `~ '  " + newline);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Fenster leeren
        displayArea.setText(" ");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < SpielerYAxis-1; i++) {
            displayArea.append(newline);
        }
        displayArea.append("                         `` ,  " + newline);
        displayArea.append("                    . ','|` ` ." + newline);
        displayArea.append("                     .'  " + (char)34 + "  '" + newline);
        displayArea.append("                    ,   ' , '  `" + newline);
        displayArea.append("                          `~ '  " + newline);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Fenster leeren
        displayArea.setText(" ");
        //Explosion auf der richtigen Höhe
        for(int i = 0; i < spielfeld.length/2-2; i++) {
            displayArea.append(newline);
        }
        displayArea.append("                     You're Dead "+ newline);
        displayArea.append(newline);
        displayArea.append(newline);
        displayArea.append(newline);

        switch(Main.Difficulty) {
            case 1 -> displayArea.append("Difficulty: Baby" +  newline);
            case 2 -> displayArea.append("Difficulty: Easy" +  newline);
            case 3 -> displayArea.append("Difficulty: Normal" +  newline);
            case 4 -> displayArea.append("Difficulty: Hard" +  newline);
            case 5 -> displayArea.append("Difficulty: Godmode" +  newline);
            case 6 -> displayArea.append("Difficulty: Botmode" +  newline);
        }

        displayArea.append("Your Score: "+ Score +  newline);
        displayArea.append("Your Highscore: "+ HighScore +  newline);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
