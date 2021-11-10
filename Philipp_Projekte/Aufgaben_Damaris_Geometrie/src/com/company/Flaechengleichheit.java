package com.company;

public class Flaechengleichheit {
    private double breite;
    private double laenge;

    public void Aufrufen(double breite, double laenge) {
        this.breite = breite;
        this.laenge = laenge;
        //Flächengleichheit
        Rechteck rechteckFlaecheGleich = new Rechteck(laenge, breite);
        Kreis kreisFlaecheGleich = new Kreis(0);
        kreisFlaecheGleich.setFlaeche(rechteckFlaecheGleich.getFlaecheninhalt());
        System.out.println("Flächengleichheit (Kreis und Rechteck)");
        System.out.println("- Kreisradius Flächengleichheit: " + kreisFlaecheGleich.getRadius());
        System.out.println("   - Flächeninhalt: " + kreisFlaecheGleich.getFlaecheninhalt());
        System.out.println("- Breite: " + rechteckFlaecheGleich.getBreite() + " und Länge: " + rechteckFlaecheGleich.getLaenge());
        System.out.println("   - Flächeninhalt: " + rechteckFlaecheGleich.getFlaecheninhalt());
    }
    public void Aufrufen(double Seiten) {
        this.breite = Seiten;
        this.laenge = Seiten;
        //Flächengleichheit
        Rechteck rechteckFlaecheGleich = new Rechteck(laenge, breite);
        Kreis kreisFlaecheGleich = new Kreis(0);
        kreisFlaecheGleich.setFlaeche(rechteckFlaecheGleich.getFlaecheninhalt());
        System.out.println("Flächengleichheit (Kreis und Rechteck)");
        System.out.println("- Kreisradius Flächengleichheit: " + kreisFlaecheGleich.getRadius());
        System.out.println("   - Flächeninhalt: " + kreisFlaecheGleich.getFlaecheninhalt());
        System.out.println("- Breite: " + rechteckFlaecheGleich.getBreite() + " und Länge: " + rechteckFlaecheGleich.getLaenge());
        System.out.println("   - Flächeninhalt: " + rechteckFlaecheGleich.getFlaecheninhalt());
    }
}
