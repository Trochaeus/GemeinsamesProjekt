
public class Objektreferenzen {

	public static void main(String[] args) {

		int meineZahl = 1;

		addiereZwei(meineZahl);

		System.out.println(meineZahl);

		System.out.println("");

		Auto auto1 = new Auto("VW", "Golf", 170, false);
		tuneAuto(auto1);
		System.out.println(auto1);

		System.out.println("");

		Auto auto2 = new Auto("VW", "Golf", 170, false);
		kopiereUndTuneAuto(auto2);
		System.out.println(auto2);
	}

	public static void addiereZwei(int zahl) {
		zahl = zahl + 2;
		System.out.println(zahl);
	}

	public static void tuneAuto(Auto zuTunendesAuto) {
		zuTunendesAuto.setHoechstgeschwindigkeit(210);
		System.out.println(zuTunendesAuto);
	}

	public static void kopiereUndTuneAuto(Auto zuTunendesAuto) {
		Auto neuesAuto = new Auto();
		neuesAuto.setHersteller(zuTunendesAuto.getHersteller());
		neuesAuto.setTyp(zuTunendesAuto.getTyp());
		neuesAuto.setHoechstgeschwindigkeit(zuTunendesAuto.getHoechstgeschwindigkeit());
		neuesAuto.setHatDachgepaecktraeger(zuTunendesAuto.isHatDachgepaecktraeger());
		System.out.println(neuesAuto);

		neuesAuto.setHoechstgeschwindigkeit(300);
		System.out.println(neuesAuto);
	}

}
