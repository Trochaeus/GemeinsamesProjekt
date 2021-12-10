public class Auto extends Fahrzeug{
    //Variablen
    boolean hatDachgepaecktraeger;

    //Konstruktor
    public Auto(String Hersteller, String Typ, int Hoechstgeschwindigkeit,boolean hatDachgepaecktraeger) {
        super(Hersteller, Typ, Hoechstgeschwindigkeit);
        this.hatDachgepaecktraeger = hatDachgepaecktraeger;
    }

    public boolean isHatDachgepaecktraeger() {
        return hatDachgepaecktraeger;
    }

    public void setHatDachgepaecktraeger(boolean hatDachgepaecktraeger) {
        this.hatDachgepaecktraeger = hatDachgepaecktraeger;
    }



    @Override
    public int gibAnzahlInstanzen() {
        return 0;
    }

    //@Override
    public int benzinVerbrauch(int verbrauchInLiter) {
        return 0;
    }
}