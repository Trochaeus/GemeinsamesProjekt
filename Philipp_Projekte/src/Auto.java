public class Auto extends Fahrzeug{
    //Variablen
    boolean hatDachgepaecktraeger;

    public Auto(boolean hatDachgepaecktraeger) {
        super(Hersteller, Typ, Hoechstgeschwindigkeit);
        Auto.thisSuper(hatDachgepaecktraeger);
    }
    static void thisSuper(boolean hatDachgepaecktraeger) {
    	this.hatDachgepaecktraeger = hatDachgepaecktraeger;
    }
    public boolean isHatDachgepaecktraeger() {
        return hatDachgepaecktraeger;
    }

    public void setHatDachgepaecktraeger(boolean hatDachgepaecktraeger) {
        this.hatDachgepaecktraeger = hatDachgepaecktraeger;
    }
}
