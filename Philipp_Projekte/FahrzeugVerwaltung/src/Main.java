
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		fahrzeuge[0] = new Fahrzeug("BMW","A8",331,new Auto(false));
		fahrzeuge[1] = new Fahrzeug("Audi","B8",279,new Auto(false));
		fahrzeuge[2] = new Fahrzeug("Opel","Lastwagen 9000",140,new Lastwagen(1000));
		fahrzeuge[3] = new Fahrzeug("Fiat","Lastwagen 9001",141,new Lastwagen(1001));
				

	}

}
