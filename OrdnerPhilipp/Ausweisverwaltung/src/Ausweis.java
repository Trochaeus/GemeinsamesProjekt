public class Ausweis {
    //Attribute
    protected int nummer;           //Nummer des Ausweises
    protected String inhaber;       //Inhaber des Ausweises
    protected String ablaufdatum;   //Ablaufdatum des Ausweises

    //Konstruktor
    public Ausweis(int nummer, String inhaber, String ablaufdatum) {
        this.nummer = nummer;
        this.inhaber = inhaber;
        this.ablaufdatum = ablaufdatum;
    }

    public int getNummer() {
        return nummer;
    }

    public String getInhaber() {
        return inhaber;
    }

    public String getAblaufdatum() {
        return ablaufdatum;
    }
}
