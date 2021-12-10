package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {
    static String[][][] spielfeld = new String[10][50][3];
    static final int gameBoardXLength = spielfeld[0].length;
    static final int gameBoardYLength = spielfeld.length;
    static int spielerHoehe;

    //Bilder
    static Image[] floppyBirdImages = new Image[20];
    static Image logo;
    static String bildpfadGame = "C:\\Users\\Public\\Documents\\FlappyBirdImages\\FlappyBirdImagePack.png";
    static String bildpfadLogo = "C:\\Users\\Public\\Documents\\FlappyBirdImages\\Logo.png";
    static JPanel jPanel = new JPanel();
    static Frame newFrame = new JFrame();

    //Start- und Difficult Screen
    static boolean leaveStartScreen = false;
    static boolean leaveDifficultyScreen = false;
    static boolean nochKeineSchwierigkeitGewaehlt = true;

    //Item Boxen
    static int doItemMessage = 0; // 0 ist false, 1 ist positive Message, 2 ist negativ
    static int ItemMessageCounter = 0;
    static boolean doDecreasedView = false;
    static int DecreasedViewCounter = 0;

    public static void setGameBoard(String[][][] Spielfeld) {
        spielfeld = Spielfeld;
    }

    public static void importPictures () throws IOException {
        BufferedImage all = ImageIO.read(new File(bildpfadGame));
        int index=0;
        for (int vertical = 0; vertical < 320; vertical+=64) {
            for (int horizontal = 0; horizontal < 256; horizontal+=64) {
                floppyBirdImages[index] = all.getSubimage(horizontal,vertical,64,64).getScaledInstance(24,24,BufferedImage.SCALE_DEFAULT);
                index++;
            }
        }
        BufferedImage bufferedLogo = ImageIO.read(new File(bildpfadLogo));
        logo = bufferedLogo.getScaledInstance(524,82,BufferedImage.SCALE_DEFAULT);
    }

    public static void guiErstellen (int Score) {
        newFrame = new JFrame();
        newFrame.setBounds(10,10,1200,299);
        newFrame.setLocationRelativeTo(null);
        newFrame.setUndecorated(false);
        rePrintGUI(spielfeld,Score);

        newFrame.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {}
            public void mouseMoved(MouseEvent e) {}
        });
        newFrame.addMouseListener(new MouseListener() {

            public void mousePressed(MouseEvent e) {
                Game.setSpaceIsPressed();
                //System.out.print("X=" + e.getX() + " - Y=" + e.getY());
            }
            public void mouseClicked(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        newFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == ' ') {
                    Game.setSpaceIsPressed();
                }
                else if(e.getKeyChar() == 's') {
                    leaveStartScreen = true;
                }
                else if(nochKeineSchwierigkeitGewaehlt) {
                    if(e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3' || e.getKeyChar() == '4' || e.getKeyChar() == '5' || e.getKeyChar() == '6') {
                        Main.difficulty = (int) e.getKeyChar() - 48; //Rückgabe Wert 48 zu hoch (Position von "1" bis "6" in der ASCII-Tabelle)
                        leaveDifficultyScreen = true;
                        nochKeineSchwierigkeitGewaehlt = false;
                    }
                }
            }
        });

        //newFrame.setDefaultCloseOperation(3);
        ImageIcon icon = new ImageIcon(floppyBirdImages[0]);
        newFrame.setIconImage(icon.getImage().getScaledInstance(128,128,10));
        newFrame.setVisible(true);
    }
    public static void rePrintGUI(String[][][] spielfeld,int score) {
        newFrame.remove(jPanel);
        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                for (int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
                    for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                        for (int Layer = 0; Layer < spielfeld[0][0].length; Layer++) {
                            if (!spielfeld[yAxis][xAxis][Layer].equals("%")) {
                                //Layer 0: Bird, Layer 1: Tower, Layer 2: Background, % bedeutet leer
                                switch (spielfeld[yAxis][xAxis][Layer]) {
                                    case ".":
                                        if (spielfeld[yAxis][xAxis][0].equals("%") && spielfeld[yAxis][xAxis][1].equals("%")) {
                                            g.drawImage(floppyBirdImages[2],xAxis*24,yAxis*24,this); //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                        }
                                        break;
                                    case "~":
                                        if (spielfeld[yAxis][xAxis][0].equals("%") && spielfeld[yAxis][xAxis][1].equals("%")) {
                                            //Wolken/Hintergrund NUR ANZEIGEN, wenn alles davor leer ist
                                            g.drawImage(floppyBirdImages[3],xAxis*24,yAxis*24,this);
                                        }
                                        break;
                                    case "|":
                                        if (spielfeld[yAxis][xAxis][0].equals("%")) {
                                            //Türme NUR ANZEIGEN, wenn alles davor leer ist
                                            g.drawImage(floppyBirdImages[5],xAxis*24,yAxis*24,this);
                                        }
                                        break;
                                    case "#":
                                        if (spielfeld[yAxis][xAxis][0].equals("%")) {
                                            //Türme NUR ANZEIGEN, wenn alles davor leer ist
                                            g.drawImage(floppyBirdImages[4],xAxis*24,yAxis*24,this);
                                        }
                                        break;
                                    case "+":
                                        if (spielfeld[yAxis][xAxis][0].equals("%")) {
                                            //Türme NUR ANZEIGEN, wenn alles davor leer ist
                                            g.drawImage(floppyBirdImages[6],xAxis*24,yAxis*24,this);
                                        }
                                        break;
                                    case "*":
                                        g.drawImage(floppyBirdImages[0],xAxis*24,yAxis*24,this); //Spieler IMMER ANZEIGEN
                                        break;
                                    case "/":
                                        g.drawImage(floppyBirdImages[1],xAxis*24,yAxis*24,this); //Spieler IMMER ANZEIGEN
                                        break;
                                    case "?":
                                        g.drawImage(floppyBirdImages[16],xAxis*24,yAxis*24,this); //Spieler IMMER ANZEIGEN
                                        break;
                                    default:
                                        g.drawImage(floppyBirdImages[2],xAxis*24,yAxis*24,this); //Sollte eigentlich nicht vorkommen
                                        break;
                                }
                                if(doDecreasedView) {
                                    if(xAxis <= 15 || xAxis >= (gameBoardXLength-15)) {
                                        g.drawImage(floppyBirdImages[17],xAxis*24,yAxis*24,this);
                                    }
                                }

                                //Boden
                                if(yAxis == gameBoardYLength-1) {
                                    g.drawImage(floppyBirdImages[18],xAxis*24,(yAxis+1)*24,this); //Sollte eigentlich nicht vorkommen
                                }
                            }
                        }
                    }
                }
                if(doDecreasedView && DecreasedViewCounter <= 50) {
                    DecreasedViewCounter++; //nach 50 Einheiten wird die Sicht wieder frei
                }
                else if(doDecreasedView){
                    DecreasedViewCounter = 0;
                    doDecreasedView = false;
                }

                if(doItemMessage > 0 && ItemMessageCounter < 20) {
                    g.setColor(Color.GRAY);
                    g.fillRect(943,3,243,30); // Schatten für Rahmen (Grau)
                    g.setColor(Color.BLACK);
                    g.fillRect(941,1,243,30); // Rahmen (Schwarz)
                    g.setColor(Color.WHITE);
                    g.fillRect(943,3,239,26); // Hintergrund für Text (Weiß)

                    if (doItemMessage == 1) {
                        g.setColor(Color.GREEN);
                        g.drawString("Lucky! Your Score has been increased by 2!",944, 18);
                    }
                    else {
                        g.setColor(Color.RED);
                        g.drawString("Unlucky! Your View has been reduced!",944, 18);
                    }
                    ItemMessageCounter++;
                }
                else if(doItemMessage > 0) {
                    doItemMessage = 0;
                    ItemMessageCounter = 0;
                }

                //Aktueller Score und Difficulty anzeigen
                String ScoreMessage = "Score: " + score;
                g.setColor(Color.GRAY);
                g.fillRect(3,3,120,40); // Schatten für Rahmen (Grau)
                g.setColor(Color.BLACK);
                g.fillRect(1,1,120,40); // Rahmen (Schwarz)
                g.setColor(Color.WHITE);
                g.fillRect(3,3,116,36); // Hintergrund für Text (Weiß)
                g.setColor(Color.BLACK);
                switch(Main.difficulty) {
                    case 1 -> g.drawString("Difficulty: Baby",4, 18);
                    case 2 -> g.drawString("Difficulty: Easy",4, 18);
                    case 3 -> g.drawString("Difficulty: Normal",4, 18);
                    case 4 -> g.drawString("Difficulty: Hard",4, 18);
                    case 5 -> g.drawString("Difficulty: Godmode",4, 18);
                    case 6 -> g.drawString("Difficulty: Botmode",4, 18);
                }
                g.drawString(ScoreMessage,4, 33);
            }
        };

        //Redraw wird ausgeführt
        newFrame.add(jPanel);
        newFrame.revalidate();
    }
    public static void DeathScreenGUI(String[][][] Spielfeld, int difficulty, int Score, int HighScore) {
        spielfeld = Spielfeld;

        //Höhe des Spielers suchen
        spielerHoehe = spielfeld.length/2; //Eingabe sollte nie als Ergebnis rauskommen, ist nur da, damit IntelliJ leise ist. Wenn der Spieler nicht gefunden wird, wird die Animation in der Mitte des Screens gestartet
        for(int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
            if(spielfeld[yAxis][spielfeld[0].length/2][0].equals("*")) {
                spielerHoehe = yAxis;
            }
        }
        for(int deathAnimationFrame = 8; deathAnimationFrame < 16; deathAnimationFrame++) {
            //Animatationsframe 0-7 (Der Zähler geht von 8 bis 15, da das die Positionen der Bilder im Array sind)
            int finalDeathAnimationFrame = deathAnimationFrame; //Keine Ahnung warum; IntelliJ möchte das so
            jPanel = new JPanel() {
                @Override
                public void paint(Graphics g) {
                    //Animation an der Stelle des Spielers anzeigen
                    g.drawImage(floppyBirdImages[finalDeathAnimationFrame],spielfeld[0].length/2*24,spielerHoehe*24,this); //Explosion für Vogel
                    g.drawImage(floppyBirdImages[finalDeathAnimationFrame],(spielfeld[0].length/2-1)*24,(spielerHoehe+1)*24,this); //Explosion für Vogel Anhängsel
                }
            };
            newFrame.add(jPanel);
            newFrame.revalidate();
            try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        }

        //Animatationsframe 8 - Leeren des Bildes, nur noch Hintergrund/Wolken
        displayLogo();
        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                //Todesmeldung mit HighScore
                String ScoreMessage = "Score: " + Score;
                String HighScoreMessage = "Highscore: " + HighScore;
                g.setColor(Color.GRAY);
                g.fillRect(530,130,124,94); // Schatten für Rahmen (Grau)
                g.setColor(Color.BLACK);
                g.fillRect(528,128,124,94); // Rahmen (Schwarz)
                g.setColor(Color.WHITE);
                g.fillRect(530,130,120,90); // Hintergrund für Text (Weiß)
                g.setColor(Color.RED);
                g.drawString("You're Dead.",540, 150); // Text (Schwarz)
                g.setColor(Color.BLACK);
                switch(difficulty) {
                    case 1 -> g.drawString("Difficulty: Baby",540, 170);
                    case 2 -> g.drawString("Difficulty: Easy",540, 170);
                    case 3 -> g.drawString("Difficulty: Normal",540, 170);
                    case 4 -> g.drawString("Difficulty: Hard",540, 170);
                    case 5 -> g.drawString("Difficulty: Godmode",540, 170);
                    case 6 -> g.drawString("Difficulty: Botmode",540, 170);
                }
                g.drawString(ScoreMessage,540, 190);
                g.drawString(HighScoreMessage,540, 210);
            }
        };
        newFrame.add(jPanel);
        newFrame.revalidate();
        try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public static void startScreen(int HighScore){
        new Thread(new Runnable() {
            @Override public void run() {
                while(true) {
                    try {playClip.playTheClip(new File("C:\\Users\\Public\\Documents\\FlappyBirdImages\\startmenu.wav"));
                    } catch (Exception e) {e.printStackTrace();}
                    try {playClip.playTheClip(new File("C:\\Users\\Public\\Documents\\FlappyBirdImages\\gameplay.wav"));
                    } catch (Exception e) {e.printStackTrace();}
                }
            }
        }).start();

        newFrame.remove(jPanel);
        displayLogo();
        try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                String highScoreMessage = "Highscore: " + HighScore;
                g.setColor(Color.GRAY);
                g.fillRect(515,120,144,74); // Schatten für Rahmen (Grau)
                g.setColor(Color.BLACK);
                g.fillRect(513,118,144,74); // Rahmen (Schwarz)
                g.setColor(Color.WHITE);
                g.fillRect(515,120,140,70); // Hintergrund für Text (Weiß)
                g.setColor(Color.RED);
                g.drawString(highScoreMessage,520, 140);
                g.setColor(Color.GREEN);
                g.drawString("Press 'Space' to jump!",520, 160);
                g.setColor(Color.BLUE);
                g.drawString("Press 's' to start!",520, 180);
            }
        };
        newFrame.add(jPanel);
        newFrame.revalidate();

        //Spiel pausieren - Im StartScreen bleiben, bis "s" gedrückt
        while(!leaveStartScreen) {
            try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        }
        leaveStartScreen = false;
    }
    public static void displayLogo(){
        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                for (int yAxis = 0; yAxis < spielfeld.length; yAxis++) {
                    for (int xAxis = 0; xAxis < spielfeld[0].length; xAxis++) {
                        if(spielfeld[yAxis][xAxis][2].equals("~")) {
                            g.drawImage(floppyBirdImages[3], xAxis * 24, yAxis * 24, this);
                        }
                        else {
                            g.drawImage(floppyBirdImages[2], xAxis * 24, yAxis * 24, this);
                        }
                        //Boden
                        if(yAxis == gameBoardYLength-1) {
                            g.drawImage(floppyBirdImages[18],xAxis*24,(yAxis+1)*24,this);
                        }
                    }
                }
                //Logo
                g.drawImage(logo,333,16,this);
                /*
                g.setColor(Color.GRAY);
                g.fillRect(332,18,524,82); // Schatten für Rahmen (Grau)
                g.setColor(Color.WHITE);
                g.fillRect(330,16,524,82); // Rahmen (Schwarz)
                g.setColor(Color.BLACK);
                g.fillRect(332,18,520,78); // Hintergrund für Text (Weiß)
                g.setColor(Color.RED);
                g.drawString("    #####   #             #####     #####     #####    #     #      ####      #     #####     ####  ",360, 32);
                g.setColor(Color.YELLOW);
                g.drawString("   #           #             #      #     #      #     #      #      # #        #      #    #     #      #     #      #  ",360, 46);
                g.setColor(Color.GREEN);
                g.drawString("  ###       #             #      #     #####     #####       #          ####      #     #####     #      #   ",360, 60);
                g.setColor(Color.CYAN);
                g.drawString(" #           #             #      #     #             #               #          #      #    #     #  #         #      #    ",360, 74);
                g.setColor(Color.MAGENTA);
                g.drawString("#           #####     #####     #             #               #          ####      #     #      #     ####  By me!  ",360, 88);
                */
            }
        };
        newFrame.add(jPanel);
        newFrame.revalidate();
    }


    public static void difficultyScreen() {
        newFrame.remove(jPanel);
        displayLogo();
        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.GRAY);
                g.fillRect(515,110,144,114); // Schatten für Rahmen (Grau)
                g.setColor(Color.BLACK);
                g.fillRect(513,108,144,114); // Rahmen (Schwarz)
                g.setColor(Color.WHITE);
                g.fillRect(515,110,140,110); // Hintergrund für Text (Weiß)
                g.setColor(Color.BLACK);
                g.drawString("Difficulty - Choose:",520, 120);
                g.setColor(Color.GREEN);
                g.drawString("1 - Baby",520, 135);
                g.setColor(Color.CYAN);
                g.drawString("2 - Easy",520, 150);
                g.setColor(Color.BLUE);
                g.drawString("3 - Normal",520, 165);
                g.setColor(Color.MAGENTA);
                g.drawString("4 - Hard",520, 180);
                g.setColor(Color.ORANGE);
                g.drawString("5 - Godmode",520, 195);
                g.setColor(Color.RED);
                g.drawString("6 - Botmode",520, 210);
            }
        };
        newFrame.add(jPanel);
        newFrame.revalidate();

        //Spiel pausieren - in dem Difficulty-Screen bleiben, bis Difficulty ausgewählt
        while(!leaveDifficultyScreen) {
            try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        }
        leaveDifficultyScreen = false;
    }
}
