public class Identitaetskarte extends Ausweis{
    //Attribute
    public int groesse;             //Größe des Inhabers
    public String buergerort;       //Bürgerort des Inhabers

    //Konstruktor
    public Identitaetskarte(int nummer, String inhaber, String ablaufdatum, int groesse, String buergerort) {
        super(nummer, inhaber, ablaufdatum);
        this.groesse = groesse;
        this.buergerort = buergerort;
    }

    public int getGroesse() {
        return groesse;
    }

    public String getBuergerort() {
        return buergerort;
    }
}
