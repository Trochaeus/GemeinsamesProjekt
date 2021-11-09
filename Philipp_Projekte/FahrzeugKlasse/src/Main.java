public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Aufgabe 1-3
        //Fahrzeug auto1 = new Fahrzeug("BMW","A8",331);
        //Fahrzeug auto2 = new Fahrzeug("Audi","B8",279);
        //Fahrzeug auto3 = new Fahrzeug("Porsche","C8",360);

        //System.out.println("Auto 1: " + auto1.toString());
        //System.out.println("Auto 2: " + auto2.toString());
        //System.out.println("Auto 3: " + auto3.toString());

        //System.out.println(" ");

        //auto1.fahreSchnell(25168);
        //auto2.fahreSchnell(30);
        //auto3.fahreSchnell(140);


        Fahrzeug[] fahrzeuge = new Fahrzeug[4];
        fahrzeuge[0] = new Auto("BMW","A8",331,false);
        fahrzeuge[1] = new Auto("Audi","B8",279,false);
        fahrzeuge[2] = new Lastwagen("Opel","Lastwagen 9000",140,1000);
        fahrzeuge[3] = new Lastwagen("Fiat","Lastwagen 9001",141,1001);


        for(int i = 0; i<fahrzeuge.length; i++) {
            System.out.println("Fahrzeug " + (i+1) + ":");
            System.out.println("- Das Fahrzeug wurde von " + fahrzeuge[i].Hersteller + " hergestellt.");
            System.out.println("- Der Typ: " + fahrzeuge[i].Typ);
            System.out.println("- Der Höchstgeschwindigkeit beträgt " + fahrzeuge[i].Hoechstgeschwindigkeit + "km/h.");
            if (fahrzeuge[i] instanceof Auto) {
                Auto auto = (Auto) fahrzeuge[i];
                System.out.println("- Es ist ein Auto");
            } else if (fahrzeuge[i] instanceof Lastwagen) {
                Lastwagen lastwagen = (Lastwagen) fahrzeuge[i];
                System.out.println("- Es ist ein Lastwagen");
            }
        }
    }
}