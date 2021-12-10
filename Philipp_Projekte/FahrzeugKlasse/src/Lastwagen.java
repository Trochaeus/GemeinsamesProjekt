public class Lastwagen extends Fahrzeug{

    int Frachtvolumen;

    public Lastwagen(String Hersteller, String Typ, int Hoechstgeschwindigkeit, int Frachtvolumen) {
        this.Hersteller = Hersteller;
        this.Typ = Typ;
        this.Hoechstgeschwindigkeit = Hoechstgeschwindigkeit;
        this.Frachtvolumen = Frachtvolumen;
    }

    public int getFrachtvolumen() {
        return Frachtvolumen;
    }

    public void setFrachtvolumen(int frachtvolumen) {
        Frachtvolumen = frachtvolumen;
    }


    @Override
    public int gibAnzahlInstanzen() {
        return 0;
    }

    @Override
    public int benzinVerbrauch(int verbrauchInLiter) {
        return 0;
    }
}
