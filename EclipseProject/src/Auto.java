
/**
 * 
 * @author dwilluhn
 * 
 *         Diese Klasse repräsentiert ein Auto
 * 
 *
 */
public class Auto extends Fahrzeug {

	private boolean hatDachgepaecktraeger;

	public Auto() {
	}

	/**
	 * Konstruktor initialisiert das Auto mit Angabe Dachgepaecktraeger
	 * 
	 * @param hatDachgepaecktraeger
	 */
	public Auto(boolean hatDachgepaecktraeger) {
		this.hatDachgepaecktraeger = hatDachgepaecktraeger;
	}

	public Auto(String hersteller, String typ, int hoechstgeschwindigkeit, boolean hatDachgepaecktraeger) {
		super(hersteller, typ, hoechstgeschwindigkeit);

		this.hatDachgepaecktraeger = hatDachgepaecktraeger;
	}

	/**
	 * Getter fuer Dachgepaecktraeger
	 * 
	 * @return gibt den boolschen Wert zurueck
	 */
	public boolean isHatDachgepaecktraeger() {
		return hatDachgepaecktraeger;
	}

	/**
	 * Setter fuer das Attribut Dachgepaecktraeger
	 * 
	 * @param hatDachgepaecktraeger
	 */
	public void setHatDachgepaecktraeger(boolean hatDachgepaecktraeger) {
		this.hatDachgepaecktraeger = hatDachgepaecktraeger;
	}

	@Override
	public String toString() {
		return "Auto [hatDachgepaecktraeger=" + isHatDachgepaecktraeger() + ", getHersteller()=" + getHersteller()
				+ ", getTyp()=" + getTyp() + ", getHoechstgeschwindigkeit()=" + getHoechstgeschwindigkeit() + "]";
	}

}
