final class Doppeldecker extends Flugzeug{ //Du kannst deine Klasse mit dem Modifikator final ausstatten. Bedeutet wie bei den Variablen, dass die Klasse dann zu einer Konstanten wird mit dem Unterschied das du in der Klasse selbst Daten ändern kannst. Es ist aber nicht mehr möglich das andere Klasse von dieser Klasse erben und diese erweitern bzw. Methoden aufrufen und überschreiben.
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
