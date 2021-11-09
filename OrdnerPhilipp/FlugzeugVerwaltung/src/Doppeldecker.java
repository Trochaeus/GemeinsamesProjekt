class Doppeldecker extends Flugzeug{ //Man kann Vererbung von der Klasse Doppeldecker verhindern, wenn man die Klasse/den Konstruktor auf private setzt. Keine Klasse kann dann von Doppeldecker erben
    //Attribute
    private int anzahlFluegel = 2;
    private final int LOOPINGSPEED = 320;
    private boolean offenesCockpit;
    String immatNummber;

    public boolean isOffenesCockpit() {
        return offenesCockpit;
    }

    public void setOffenesCockpit(boolean offenesCockpit) {
        this.offenesCockpit = offenesCockpit;
    }

    //Konstruktor
    public Doppeldecker(String hersteller, int maxSpeed) {
        super(hersteller, maxSpeed);
    }


    //Konstruktor 1
    public Doppeldecker(String hersteller, int maxSpeed, String immatNummer, boolean offenesCockpit) {
        super(hersteller, maxSpeed);
        this.immatNummber = immatNummer;
        this.offenesCockpit = offenesCockpit;
    }
    //Konstruktor 2
    public Doppeldecker(String hersteller, int maxSpeed, String immatNummer) {
        super(hersteller, maxSpeed);
        this.immatNummber = immatNummer;
        this.offenesCockpit = true;
    }
    @Override
    public boolean getLooping() {
        if(this.getMaxSpeed() > LOOPINGSPEED) {
            return true;
        }
        else {
            return false;
        }
    }
}
