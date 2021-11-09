public class Pass extends Identitaetskarte {
    //Attribute
    public String fingerabdruck; // Digitaler Fingerabdruck des Inhabers

    //Konstruktor
    public Pass(int nummer, String inhaber, String ablaufdatum, int groesse, String buergerort, String fingerabdruck) {
        super(nummer, inhaber, ablaufdatum, groesse, buergerort);
        this.fingerabdruck = fingerabdruck;
    }

    public String getFingerabdruck() {
        return fingerabdruck;
    }
}
