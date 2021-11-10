package com.company;

public class Kreis {
    //Attribute
    private double radius;
    private double umfang;
    private double flaeche;
    final double PI = 3.1415926;

    //Konstruktor
    public Kreis() {
        this.radius = 3;
    }
    public Kreis(double radius) {
        this.radius = radius;
    }

    //Getter und Setter
    public double getRadius() {
        return Math.floor(radius*100)/100;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    //Getter Umfang
    public double getUmfang() {
        return Math.floor((2*PI*radius)*100)/100;
    }
    //Getter Flächeninhalt
    public double getFlaecheninhalt() {
        return Math.floor((PI*radius*radius)*100)/100;
    }
    /*
        ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG
        ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG
        ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG ERWEITERUNG
     */
    public void setUmfang(double umfang) {
        // Umfang= 2*PI*radius
        this.umfang = umfang;
        radius = umfang/(2 * PI);
    }
    public void setFlaeche(double flaeche) {
        // Flaeche= PI*radius*radius
        this.flaeche = flaeche;
        radius = Math.sqrt(flaeche / PI);
    }
}
