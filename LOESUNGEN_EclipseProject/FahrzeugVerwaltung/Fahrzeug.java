
public class Fahrzeug {

	private static double faktorDurchschnittsgeschwindigkeit;

	private String hersteller;
	private String typ;
	private int hoechstgeschwindigkeit;

	// Default-Konstruktor: Erzeugt nur das Objekt und macht sonst nichts.
	public Fahrzeug() {

	}

	// Konstruktor mit Parametern: Greift einem Aufruf der Setter vorweg und
	// initialisiert alle Instanzvariablen mit den übergebenen Werten
	public Fahrzeug(String hersteller, String typ, int hoechstgeschwindigkeit) {
		this.hersteller = hersteller;
		this.typ = typ;
		this.hoechstgeschwindigkeit = hoechstgeschwindigkeit;
	}

	public String fahreSchnell(int minuten) {
		double streckeInKmh = hoechstgeschwindigkeit * faktorDurchschnittsgeschwindigkeit * minuten / 60;
		return String.valueOf(streckeInKmh) + " km gefahren.";
	}

	public static double getFaktorDurchschnittsgeschwindigkeit() {
		return faktorDurchschnittsgeschwindigkeit;
	}

	public static void setFaktorDurchschnittsgeschwindigkeit(double faktorDurchschnittsgeschwindigkeit) {
		Fahrzeug.faktorDurchschnittsgeschwindigkeit = faktorDurchschnittsgeschwindigkeit;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getHoechstgeschwindigkeit() {
		return hoechstgeschwindigkeit;
	}

	public void setHoechstgeschwindigkeit(int hoechstgeschwindigkeit) {
		this.hoechstgeschwindigkeit = hoechstgeschwindigkeit;
	}

	@Override
	public String toString() {
		return "Auto [hersteller=" + hersteller + ", typ=" + typ + ", hoechstgeschwindigkeit=" + hoechstgeschwindigkeit
				+ "]";
	}

}
