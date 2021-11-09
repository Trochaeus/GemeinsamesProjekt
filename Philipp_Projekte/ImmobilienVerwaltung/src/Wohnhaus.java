public class Wohnhaus extends Immobilie {
    //Attribute
    private int Grundstueckflaeche;

    //Konstruktor
    public Wohnhaus(String stadt, int baujahr, int wohnflaeche, double preis) {
        super(stadt, baujahr, wohnflaeche, preis);
    }
    public Wohnhaus() {
    }

    public int getGrundstueckflaeche() {
        return Grundstueckflaeche;
    }

    public void setGrundstueckflaeche(int grundstueckflaeche) {
        Grundstueckflaeche = grundstueckflaeche;
    }
}