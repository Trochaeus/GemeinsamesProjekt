package com.company;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // KREIS KREIS KREIS KREIS KREIS KREIS KREIS KREIS KREIS KREIS KREIS KREIS KREIS KREIS
        Kreis kreis = new Kreis(13);
        System.out.println("Kreis");
        System.out.println("- Radius: " + kreis.getRadius());
        System.out.println("- Umfang: " + kreis.getUmfang());
        System.out.println("- Flächeninhalt: " + kreis.getFlaecheninhalt());
        kreis.setFlaeche(30);
        System.out.println("- Radius (Abhängig von dem Flächeninhalt 30): " + kreis.getRadius());
        kreis.setUmfang(15);
        System.out.println("- Radius (Abhängig von dem Umfang 15): " + kreis.getRadius());
        System.out.println(" ");

        // RECHTECK RECHTECK RECHTECK RECHTECK RECHTECK RECHTECK RECHTECK RECHTECK RECHTECK RECHTECK
        Rechteck rechteck = new Rechteck(9,6);
        rechteck.breiteVergroessern(3);
        rechteck.breiteVerkleinern(4);
        //Rechteck rechteck = new Rechteck(1);
        System.out.println("Rechteck");
        System.out.println("- Breite: " + rechteck.getBreite() + " und Länge: " + rechteck.getLaenge());
        System.out.println("- Lange Seite: " + rechteck.getLangeSeite() + " und kurze Seite: " + rechteck.getKurzeSeite());
        System.out.println("- Umfang: " + rechteck.getUmfang());
        System.out.println("- Flächeninhalt: " + rechteck.getFlaecheninhalt());
        System.out.println("- Diagonale: " + rechteck.getDiagonale());
        System.out.println(" ");

        // KREISTABELLE KREISTABELLE KREISTABELLE KREISTABELLE KREISTABELLE KREISTABELLE KREISTABELLE
        kreis.setRadius(5);
        System.out.println("Radius   Umfang    Fläche");
        for(int i = 0; i < 31; i++) {
            kreis.setRadius((5+5*i));
            System.out.println(kreis.getRadius() + "    " + kreis.getUmfang() + "    " + kreis.getFlaecheninhalt());
        }
        System.out.println(" ");

        Flaechengleichheit flaechengleichheit = new Flaechengleichheit();
        flaechengleichheit.Aufrufen(10,12);
    }
}