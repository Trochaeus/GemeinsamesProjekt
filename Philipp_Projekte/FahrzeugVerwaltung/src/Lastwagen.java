
public class Lastwagen extends Fahrzeug{
	
	int Frachtvolumen;
	
	public Lastwagen(int frachtvolumen) { //Alle Vier Variablen werden benötigt, auch die super()-Variablen
		super(Hersteller, Typ, Hoechstgeschwindigkeit);
		this.Frachtvolumen = frachtvolumen;
	}

	public int getFrachtvolumen() {
		return Frachtvolumen;
	}

	public void setFrachtvolumen(int frachtvolumen) {
		Frachtvolumen = frachtvolumen;
	}
	
}
