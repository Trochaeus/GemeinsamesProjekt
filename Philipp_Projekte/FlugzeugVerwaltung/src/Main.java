public class Main {
    public static void main(String[] args) {
        //Erstellen
        Flugzeug[] flugzeuge = new Flugzeug[2];
        flugzeuge[0] = new Verkehrsflugzeug("AeroFiXX", 800, 150);
        flugzeuge[1] = new Doppeldecker("AeroFast", 1100, "IAOWGAOWIFJAWOGIJAWOGIJAWOGIAJWGOIAWJFOAWIFJAWOI");




        for(int i = 0; i<flugzeuge.length; i++) {
            System.out.println("Flugzeug " + (i+1) + ":");
            System.out.println("- Hersteller: " + flugzeuge[i].getHersteller());
            System.out.println("- Maximale Geschwindigkeit: " + flugzeuge[i].getMaxSpeed());
            if (flugzeuge[i] instanceof Verkehrsflugzeug) {
                Verkehrsflugzeug verkehrsflugzeug = (Verkehrsflugzeug) flugzeuge[i];
                System.out.println("- Anzahl der Passagiere: " + verkehrsflugzeug.getAnzahlPassagiere());
                System.out.println("- Anzahl der Flügelpaare: " + verkehrsflugzeug.getAnzahlFluegel());
            }
            else if (flugzeuge[i] instanceof Doppeldecker) {
                Doppeldecker doppeldecker = (Doppeldecker) flugzeuge[i];
                System.out.println("- Die Immatrikulationsnummer: " + doppeldecker.getImmatNummer());
                System.out.println("- Ist das Cockpit offen? " + doppeldecker.isOffenesCockpit());
                System.out.println("- Schafft der Doppeldecker einen Looping? " + doppeldecker.getLooping());
            }
            System.out.println(" ");
        }
    }
}


//if (immobilien[i] instanceof Auto) {
//    Auto auto = (Auto) immobilien[i];
//    System.out.println("- Es ist ein Auto");
//} else if (immobilien[i] instanceof Lastwagen) {
//    Lastwagen lastwagen = (Lastwagen) immobilien[i];
//    System.out.println("- Es ist ein Lastwagen");
//}