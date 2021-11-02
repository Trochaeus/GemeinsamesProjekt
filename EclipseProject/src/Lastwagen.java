
public class Lastwagen extends Fahrzeug {

	private int frachtvolumen;

	public Lastwagen() {
	}

	public Lastwagen(int frachtvolumen) {
		this.frachtvolumen = frachtvolumen;
	}

	public Lastwagen(String hersteller, String typ, int hoechstgeschwindigkeit, int frachtvolumen) {
		super(hersteller, typ, hoechstgeschwindigkeit);

		this.frachtvolumen = frachtvolumen;
	}

	public int getFrachtvolumen() {
		return frachtvolumen;
	}

	public void setFrachtvolumen(int frachtvolumen) {
		this.frachtvolumen = frachtvolumen;
	}

	@Override
	public String toString() {
		return "Lastwagen [frachtvolumen=" + frachtvolumen + ", getHersteller()=" + getHersteller() + ", getTyp()="
				+ getTyp() + ", getHoechstgeschwindigkeit()=" + getHoechstgeschwindigkeit() + "]";
	}

}
