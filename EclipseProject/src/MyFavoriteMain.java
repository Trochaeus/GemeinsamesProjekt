
public class MyFavoriteMain {

	public static void main(String args[]) {

		Auto meinAuto1 = new Auto("BMW", "3er", 200);
		Auto meinAuto2 = new Auto("Mercedes", "C-Klasse", 180);
		Auto meinAuto3 = new Auto("Audi", "A3", 190);

		System.out.println(meinAuto1);
		System.out.println(meinAuto2);
		System.out.println(meinAuto3);

		System.out.println(meinAuto1.fahreSchnell(30));
		System.out.println(meinAuto2.fahreSchnell(30));
		System.out.println(meinAuto3.fahreSchnell(30));

	}

}
