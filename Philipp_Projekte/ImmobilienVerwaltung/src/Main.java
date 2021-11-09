public class Main {
    public static void main(String[] args) {
        //Erstellen
        Immobilie[] immobilien = new Immobilie[2];
        Wohnhaus wohnhaus = new Wohnhaus();
        wohnhaus.setGrundstueckflaeche(450);
        immobilien[0] = new Immobilie("Ulm",2005,85,185000.00);
        immobilien[1] = new Immobilie("Esslingen",1996,180,575000.00); // + Grundstückfläche 450m²



        for(int i = 0; i<immobilien.length; i++) {
            System.out.println("Immobilie " + (i+1) + ":");
            System.out.println("- Stadt: " + immobilien[i].getStadt());
            System.out.println("- Baujahr: " + immobilien[i].getBaujahr());
            System.out.println("- Wohnfläche: " + immobilien[i].getWohnflaeche() + "m²");
            System.out.println("- Preis: " + immobilien[i].getPreis() + "€");
            if(i==1) {
                System.out.println("- Grundstückfläche: " + wohnhaus.getGrundstueckflaeche() + "m²");
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