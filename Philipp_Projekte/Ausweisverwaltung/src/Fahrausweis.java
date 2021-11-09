public class Fahrausweis extends Ausweis{
    //Attribute
    public String kategorien; // Fahrzeugkategoeiren, die der Inhaber lenken darf

    //Konstruktor
    public Fahrausweis(int nummer, String inhaber, String ablaufdatum, String kategorien) {
        super(nummer, inhaber, ablaufdatum);
        this.kategorien = kategorien;
    }
    public String getKategorien() {
        return kategorien;
    }
}
