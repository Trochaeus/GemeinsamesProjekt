public abstract class Fahrzeug implements ZaehlbareInstanzen{
    //Instanzvariablen
    public String Hersteller;
    public String Typ;
    public int Hoechstgeschwindigkeit;
    int Fahrtdauer;
    static double DurchschnittsGeschwindigkeitFaktor = 0.5;
    private static int anzahlInstanzen;



    public Fahrzeug() {
        anzahlInstanzen++;
    }
    //Konstruktor
    public Fahrzeug(String Hersteller, String Typ, int Hoechstgeschwindigkeit) {
        this.Hersteller = Hersteller;
        this.Typ = Typ;
        this.Hoechstgeschwindigkeit = Hoechstgeschwindigkeit;
        anzahlInstanzen++;
    }

    //Getter
    public String getHersteller() {
        return Hersteller;
    }

    public void setHersteller(String hersteller) {
        Hersteller = hersteller;
    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ = typ;
    }

    public int getHoechstgeschwindigkeit() {
        return Hoechstgeschwindigkeit;
    }

    public void setHoechstgeschwindigkeit(int Hoechstgeschwindigkeit) {
        Hoechstgeschwindigkeit = Hoechstgeschwindigkeit;
    }
    public String toString() {
        String Ausgabe = ("Das Auto ist von der Marke '" + Hersteller + "' und vom Typ '" + Typ + "' und erbringt eine Hoechstgeschwindigkeit von '" + Hoechstgeschwindigkeit + "km/h'");
        return Ausgabe;
    }
    public void fahreSchnell(int Fahrtdauer) {
        this.Fahrtdauer = Fahrtdauer;
        double FahrtdauerInStunden = Fahrtdauer/60.0;
        int Entfernung = (int) (DurchschnittsGeschwindigkeitFaktor*Hoechstgeschwindigkeit*FahrtdauerInStunden);
        System.out.println("Die Entfernung beträgt " + Entfernung + "km");
    }


    public static int getAnzahlInstanzen() {
        return anzahlInstanzen;
    }

    public static void setAnzahlInstanzen(int anzahlInstanzen) {
        Fahrzeug.anzahlInstanzen = anzahlInstanzen;
    }

    //Abstrakte Methode
    public abstract int benzinVerbrauch(int verbrauchInLiter);

}

