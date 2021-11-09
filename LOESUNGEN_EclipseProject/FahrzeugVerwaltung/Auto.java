
public class Auto extends Fahrzeug {

	private boolean hatDachgepaecktraeger;

	public Auto() {
	}

	public Auto(boolean hatDachgepaecktraeger) {
		this.hatDachgepaecktraeger = hatDachgepaecktraeger;
	}

	public Auto(String hersteller, String typ, int hoechstgeschwindigkeit, boolean hatDachgepaecktraeger) {
		super(hersteller, typ, hoechstgeschwindigkeit);

		this.hatDachgepaecktraeger = hatDachgepaecktraeger;
	}

	public boolean isHatDachgepaecktraeger() {
		return hatDachgepaecktraeger;
	}

	public void setHatDachgepaecktraeger(boolean hatDachgepaecktraeger) {
		this.hatDachgepaecktraeger = hatDachgepaecktraeger;
	}

	@Override
	public String toString() {
		return "Auto [hatDachgepaecktraeger=" + hatDachgepaecktraeger + ", getHersteller()=" + getHersteller()
				+ ", getTyp()=" + getTyp() + ", getHoechstgeschwindigkeit()=" + getHoechstgeschwindigkeit() + "]";
	}

}
