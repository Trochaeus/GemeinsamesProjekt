public class AusweisVerwaltung {
    private Ausweis[] ausweise;
    public AusweisVerwaltung() {
        ausweise = new Ausweis[100];

        //Erzeugen diverser Ausweise (es ist nur eine AUswahl angegeben)
        ausweise[0] = new Identitaetskarte(1234567,"Max Meier", "10.05.2010", 178, "Chur");
        ausweise[7] = new Pass(76596678,"Eva Müller", "31.12.2008", 169, "Zuerich","10010110");
        ausweise[16] = new Fahrausweis(5126578,"Ida Fuchs", "20.03.2015", "A B D1");

        //Verwendung der von Ihnen zu implementierenden Methoden
        System.out.println("Totale Ausweise: " + countAusweise(ausweise));
        int[] countResult = countAusweisTyp(ausweise);
        System.out.println("Identitätskarten: " + countResult[0] + ", Pässe: " + countResult[1] + ", Fahrausweise: " + countResult[2]);
        printPaesse(ausweise);
    }
    public static void main (String args[]) {
        AusweisVerwaltung av = new AusweisVerwaltung();
    }

    //Zählt die Anzahl Ausweise im Array ausweise
    public int countAusweise(Ausweis[] ausweise) {
        int Anzahlausweise = 0;
        for(int i = 0; i<ausweise.length; i++) {
            if(ausweise[i] != null) {
                Anzahlausweise++;
            }
        }
        //Anzahlausweise = ausweise.length;
        return Anzahlausweise;
    }

    //Zählt die Anzahl Ausweise im Array ausweise nach Ausweis-Typ
    public int[] countAusweisTyp(Ausweis[] ausweise) {
        int identitaetskarte_counter = 0;
        int pass_counter = 0;
        int fahrausweis_counter = 0;

        for(int i = 0; i<ausweise.length; i++) {
            if(ausweise[i] != null) {
                if(ausweise[i] instanceof Identitaetskarte) {
                    identitaetskarte_counter++;
                }
                if(ausweise[i] instanceof Pass) {
                    pass_counter++;
                }
                if(ausweise[i] instanceof Fahrausweis) {
                    fahrausweis_counter++;
                }
            }
        }


        int[] countResult = {identitaetskarte_counter,pass_counter,fahrausweis_counter};
        return countResult;
    }

    //Gibt die Paesse im Array ausweise auf der Konsole aus
    public void printPaesse(Ausweis[] ausweise) {
       for(int i = 0; i<ausweise.length; i++) {
           if(ausweise[i] != null) {
               //System.out.println("- Inhaber: " + ausweise[i].getInhaber());
               //System.out.println("- Nummer: " + ausweise[i].getNummer());
               //System.out.println("- Ablaufdatum: " + ausweise[i].getAblaufdatum());
               System.out.print(ausweise[i].getInhaber() + ", " + ausweise[i].getNummer() + ", " + ausweise[i].getAblaufdatum());
               if(ausweise[i] instanceof Identitaetskarte && !(ausweise[i] instanceof Pass)) {
                   //System.out.println("- Typ: IDENTITÄTSKARTE");
                   System.out.print(", Identitätskarte");
               }
               if(ausweise[i] instanceof Pass) {
                   //System.out.println("- Typ: PASS");
                   System.out.print(", Pass");
               }
               if(ausweise[i] instanceof Fahrausweis) {
                   //System.out.println("- Typ: FAHRAUSWEIS");
                   System.out.print(", Fahrausweis");
               }
               System.out.println(" ");
           }
       }
    }

}
