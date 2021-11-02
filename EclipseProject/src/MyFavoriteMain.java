import java.util.ArrayList;
import java.util.List;

public class MyFavoriteMain {

	public static void main(String args[]) {

		Auto.setFaktorDurchschnittsgeschwindigkeit(0.5);

		Auto meinAuto1 = new Auto("BMW", "3er", 200, false);
		Auto meinAuto2 = new Auto("Mercedes", "C-Klasse", 180, true);
		Auto meinAuto3 = new Auto("Audi", "A3", 190, false);

		System.out.println(meinAuto1);
		System.out.println(meinAuto2);
		System.out.println(meinAuto3);

		System.out.println(meinAuto1.fahreSchnell(30));
		System.out.println(meinAuto2.fahreSchnell(30));
		System.out.println(meinAuto3.fahreSchnell(30));

		// Für Aufgabe 3: Klassenvariable (static) anpassen und Methode für alle 3 Autos
		// erneut ausführen...
		Auto.setFaktorDurchschnittsgeschwindigkeit(0.8);

		System.out.println(meinAuto1.fahreSchnell(30));
		System.out.println(meinAuto2.fahreSchnell(30));
		System.out.println(meinAuto3.fahreSchnell(30));

		// Für Aufgabe 5: zusätzlich Lastwagen anlegen...
		Lastwagen lastwagen1 = new Lastwagen("Mercedes", "Sprinter", 170, 10);
		Lastwagen lastwagen2 = new Lastwagen("VW", "Transporter", 160, 8);

		// ... alles in einem Array ablegen ...
		Fahrzeug[] meineFahrzeuge = new Fahrzeug[5];
		meineFahrzeuge[0] = meinAuto1;
		meineFahrzeuge[1] = meinAuto2;
		meineFahrzeuge[2] = meinAuto3;
		meineFahrzeuge[3] = lastwagen1;
		meineFahrzeuge[4] = lastwagen2;

		// .. und in einer Schleife ausgeben
		for (int i = 0; i < meineFahrzeuge.length; i++) {
			Fahrzeug fahrzeug = meineFahrzeuge[i];
			gibFahrzeugEigenschaftenAus(fahrzeug);
		}

		// Aufgabe 6:
		List<Fahrzeug> meinefahrzeugListe = new ArrayList<>();
		meinefahrzeugListe.add(meinAuto1);
		meinefahrzeugListe.add(meinAuto2);
		meinefahrzeugListe.add(meinAuto3);
		meinefahrzeugListe.add(lastwagen1);
		meinefahrzeugListe.add(lastwagen2);

		// Klassische for-each-Schleife
		for (Fahrzeug fahrzeug : meinefahrzeugListe) {
			gibFahrzeugEigenschaftenAus(fahrzeug);
		}

		// Lambda-Variante (kein Corona)
		meinefahrzeugListe.forEach(fahrzeug -> gibFahrzeugEigenschaftenAus(fahrzeug));
	}

	public static void gibFahrzeugEigenschaftenAus(Fahrzeug fahrzeug) {
		if (fahrzeug instanceof Auto) {
			Auto auto = (Auto) fahrzeug;
			System.out.println(auto);
		} else if (fahrzeug instanceof Lastwagen) {
			Lastwagen lastwagen = (Lastwagen) fahrzeug;
			System.out.println(lastwagen);
		}
	}

}
