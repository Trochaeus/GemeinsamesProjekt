
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hersteller == null) ? 0 : hersteller.hashCode());
		result = prime * result + hoechstgeschwindigkeit;
		result = prime * result + ((typ == null) ? 0 : typ.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Fahrzeug other = (Fahrzeug) obj;
		if (hersteller == null) {
			if (other.hersteller != null)
				return false;
		} else if (!hersteller.equals(other.hersteller))
			return false;
		if (hoechstgeschwindigkeit != other.hoechstgeschwindigkeit)
			return false;
		if (typ == null) {
			if (other.typ != null)
				return false;
		} else if (!typ.equals(other.typ))
			return false;
		return true;
	}

}
