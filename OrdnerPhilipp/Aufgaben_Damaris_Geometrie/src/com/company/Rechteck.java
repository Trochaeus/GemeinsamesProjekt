package com.company;

public class Rechteck {
    //Attribute
    private double laenge;
    private double breite;

    //Konstruktor
    public Rechteck(double laenge, double breite) {
        this.laenge = laenge;
        this.breite = breite;
    }
    public Rechteck(double Seite) {
        this.laenge = Seite;
        this.breite = Seite;
    }

    //Getter und Setter
    public double getLaenge() {
        return laenge;
    }

    public void setLaenge(double laenge) {
        this.laenge = laenge;
    }

    public double getBreite() {
        return breite;
    }

    public void setBreite(double breite) {
        this.breite = breite;
    }
    //Setter Seiten
    public void setSeiten(double laenge, double breite) {
        this.laenge = laenge;
        this.breite = breite;
    }
    //Getter Umfang
    public double getUmfang(){
        return 2*breite+2*laenge;
    }

    //Getter Flächeninhalt
    public double getFlaecheninhalt() {
        return breite*laenge;
    }

    //Getter Diagonale
    public double getDiagonale() {
        return Math.floor((Math.sqrt(laenge*laenge+breite*breite))*100)/100;
    }

    //Getter Lange Seite
    public double getLangeSeite() {
        if(breite>laenge) {
            return breite;
        }
        else {
            return laenge;
        }
    }
    //Getter Kurze Seite
    public double getKurzeSeite() {
        if(breite<laenge) {
            return breite;
        }
        else {
            return laenge;
        }
    }
    /*
        ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG
        ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG
        ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG
     */
    public void laengeVergroessern(double diff) {
        laenge += diff;
    }
    public void breiteVergroessern(double diff) {
        breite += diff;
    }
    public void laengeVerkleinern(double diff) {
        laenge -= diff;
    }
    public void breiteVerkleinern(double diff) {
        breite -= diff;
    }
}
