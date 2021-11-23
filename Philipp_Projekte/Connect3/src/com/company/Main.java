package com.company;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main (String [] args) {
        //Farben
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        int[][][] Spielfeld = new int[10][10][3];
        int[] Ausgewaehlt = {10,10,10,10,10,10,10};
        //  x,y,z-Achse
        Random randomGenerator = new Random();
        int bound = 10;

        //Befüllen des dreidimensionalen Arrays/Spielfeldes
        for(int xAxis = 0; xAxis < 10; xAxis++) {
            for(int yAxis = 0; yAxis < 10; yAxis++) {
                for(int zAxis = 0; zAxis < 3; zAxis++) {
                    Spielfeld[xAxis][yAxis][zAxis] = randomGenerator.nextInt(bound);
                }
            }
        }

        boolean Weiterspielen = true;
        while(Weiterspielen) {
            //Darstellung des Spielfeldes
            System.out.println(ANSI_CYAN + "  A B C D E F G H I J" + ANSI_RESET);
            for (int xAxis = 0; xAxis < 10; xAxis++) {
                System.out.print(ANSI_CYAN + xAxis + " " + ANSI_RESET);
                for (int yAxis = 0; yAxis < 10; yAxis++) {
                    // 10 ist keine im Spiel angewandte Zahl, sondern ein Platzhalter (Unsichtbares Zeichen)
                    if (Spielfeld[xAxis][yAxis][0] != 10) {
                        System.out.print(ANSI_GREEN + Spielfeld[xAxis][yAxis][0] + " " + ANSI_RESET);
                    } else if (Spielfeld[xAxis][yAxis][1] != 10) {
                        System.out.print(ANSI_YELLOW + Spielfeld[xAxis][yAxis][1] + " " + ANSI_RESET);
                    } else if (Spielfeld[xAxis][yAxis][2] != 10) {
                        System.out.print(ANSI_RED + Spielfeld[xAxis][yAxis][2] + " " + ANSI_RESET);
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println(" ");
            }


            //Bubblesort
            int smaller;
            int bigger;
            boolean run = true;

            for (int i = 0; i < Ausgewaehlt.length && run == true; i++) {
                run = false;
                for (int y = 0; y < Ausgewaehlt.length - 1; y++) {
                    if (Ausgewaehlt[y] > Ausgewaehlt[y + 1]) {
                        bigger = Ausgewaehlt[y];
                        smaller = Ausgewaehlt[y + 1];
                        Ausgewaehlt[y] = smaller;
                        Ausgewaehlt[y + 1] = bigger;
                    }
                }
            }
            //Ausgabe
            System.out.println(" ");
            System.out.print("Behälter: ");
            for (int i = 0; i < Ausgewaehlt.length; i++) {
                if (Ausgewaehlt[i] != 10) {
                    System.out.print(Ausgewaehlt[i] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println(" ");

            //Auswählen einer Zahl
            System.out.println("Gebe nun ein, welches Feld du auswählen willst: ");
            Scanner scanner = new Scanner(System.in);
            String auswahl = scanner.nextLine();
            int intAuswahl;
            int xAxis = 0;
            int yAxis = 0;
            if (auswahl.matches("^\\d[a-j,A-J]$") || auswahl.matches("^[a-j,A-J]\\d$")) {
                char pos1 = auswahl.charAt(0);
                char pos2 = auswahl.charAt(1);
                String auswahl1 = "" + pos1;
                String auswahl2 = "" + pos2;
                if (auswahl1.matches("^\\d$")) {
                    xAxis = Integer.parseInt(auswahl1);
                    switch (auswahl2) {
                        case "A", "a" -> yAxis = 0;
                        case "B", "b" -> yAxis = 1;
                        case "C", "c" -> yAxis = 2;
                        case "D", "d" -> yAxis = 3;
                        case "E", "e" -> yAxis = 4;
                        case "F", "f" -> yAxis = 5;
                        case "G", "g" -> yAxis = 6;
                        case "H", "h" -> yAxis = 7;
                        case "I", "i" -> yAxis = 8;
                        case "J", "j" -> yAxis = 9;
                        default -> System.out.println("Die Eingabe ist nicht gültig");
                    }
                } else {
                    xAxis = Integer.parseInt(auswahl2);
                    switch (auswahl1) {
                        case "A", "a" -> yAxis = 0;
                        case "B", "b" -> yAxis = 1;
                        case "C", "c" -> yAxis = 2;
                        case "D", "d" -> yAxis = 3;
                        case "E", "e" -> yAxis = 4;
                        case "F", "f" -> yAxis = 5;
                        case "G", "g" -> yAxis = 6;
                        case "H", "h" -> yAxis = 7;
                        case "I", "i" -> yAxis = 8;
                        case "J", "j" -> yAxis = 9;
                        default -> System.out.println("Die Eingabe ist nicht gültig");
                    }
                }
                System.out.println("x: " + xAxis);
                System.out.println("y: " + yAxis);

                //Ausgabe
                if (Spielfeld[xAxis][yAxis][0] != 10) {
                    for (int i = 0; i < Ausgewaehlt.length; i++) {
                        if (Ausgewaehlt[i] == 10) {
                            Ausgewaehlt[i] = Spielfeld[xAxis][yAxis][0];
                            Spielfeld[xAxis][yAxis][0] = 10;
                            break;
                        }
                    }
                } else if (Spielfeld[xAxis][yAxis][1] != 10) {
                    for (int i = 0; i < Ausgewaehlt.length; i++) {
                        if (Ausgewaehlt[i] == 10) {
                            Ausgewaehlt[i] = Spielfeld[xAxis][yAxis][1];
                            Spielfeld[xAxis][yAxis][1] = 10;
                            break;
                        }
                    }
                } else if (Spielfeld[xAxis][yAxis][2] != 10) {
                    for (int i = 0; i < Ausgewaehlt.length; i++) {
                        if (Ausgewaehlt[i] == 10) {
                            Ausgewaehlt[i] = Spielfeld[xAxis][yAxis][2];
                            Spielfeld[xAxis][yAxis][2] = 10;
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Das Feld ist leer");
                }


                //3er Paar im Behälter clearen
                run = true;

                for (int i = 0; i < Ausgewaehlt.length && run == true; i++) {
                    run = false;
                    for (int y = 0; y < Ausgewaehlt.length - 1; y++) {
                        if (Ausgewaehlt[y] > Ausgewaehlt[y + 1]) {
                            bigger = Ausgewaehlt[y];
                            smaller = Ausgewaehlt[y + 1];
                            Ausgewaehlt[y] = smaller;
                            Ausgewaehlt[y + 1] = bigger;
                        }
                    }
                }






                if(Ausgewaehlt[Ausgewaehlt.length-1] != 10) {
                    System.out.println(" ");
                    System.out.print("Behälter2: ");
                    for (int i = 0; i < Ausgewaehlt.length; i++) {
                        if (Ausgewaehlt[i] != 10) {
                            System.out.print(Ausgewaehlt[i] + " ");
                        } else {
                            System.out.print(". ");
                        }
                    }
                    System.out.println(" ");
                    System.out.println("Der Behälter ist voll. Du hast verloren.");
                    Weiterspielen = false;
                }
            }
            else {
                System.out.println("Das ist keine mögliche Eingabe.");
            }
        }
    }
}