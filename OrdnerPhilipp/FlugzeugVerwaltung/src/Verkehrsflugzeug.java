public class Verkehrsflugzeug extends Flugzeug{
    //Attribute
    private int AnzahlPassagiere;
    private int anzahlFluegel = 1;

    //Konstruktor
    public Verkehrsflugzeug(String hersteller, int maxSpeed, int anzahlPassagiere) {
        super(hersteller, maxSpeed);
        this.AnzahlPassagiere = anzahlPassagiere;
    }

    public int getAnzahlPassagiere() {
        return AnzahlPassagiere;
    }

    public void setAnzahlPassagiere(int anzahlPassagiere) {
        AnzahlPassagiere = anzahlPassagiere;
    }
    @Override
    public boolean getLooping() {
        return false;
    }
}
