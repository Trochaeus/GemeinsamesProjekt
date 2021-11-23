public class Immobilie {
    //Attribute
    private String Stadt;
    private int Baujahr;
    private int Wohnflaeche;
    double Preis;

    //Konstruktor
    public Immobilie(String stadt, int baujahr, int wohnflaeche, double preis) {
        Stadt = stadt;
        Baujahr = baujahr;
        Wohnflaeche = wohnflaeche;
        Preis = preis;
    }

    public Immobilie() {
    }

    //Getter und Setter
    public String getStadt() {
        return Stadt;
    }

    public void setStadt(String name) {
        Stadt = name;
    }

    public int getBaujahr() {
        return Baujahr;
    }

    public void setBaujahr(int baujahr) {
        Baujahr = baujahr;
    }

    public int getWohnflaeche() {
        return Wohnflaeche;
    }

    public void setWohnflaeche(int wohnflaeche) {
        Wohnflaeche = wohnflaeche;
    }

    public double getPreis() {
        return Preis;
    }

    public void setPreis(double preis) {
        Preis = preis;
    }
}