import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String args[]) throws Exception {
        FarbMixGenerator farbMixGenerator = new FarbMixGenerator();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Zur Auswahl stehen:");
        System.out.println("- Korrekter Graufilter (1)");
        System.out.println("- Gleichartiger Graufilter (2)");
        System.out.println("- Binär Filter (3)");
        System.out.println("- Simplifizierte Farben(4)");
        System.out.println("- Zufällige Farbvertauschung pro Pixel (5)");
        System.out.println("- Zufällige Farbvertauschung pro Bild (6)");

        System.out.println(" ");
        System.out.println("Gebe die jeweilige Zahl ein: ");
        String auswahl = scanner.nextLine();

        //Für Nummer 6
        Random random = new Random();
        int zufallszahl1 = random.nextInt(3);
        int zufallszahl2 = random.nextInt(3);
        int zufallszahl3 = random.nextInt(3);

        BufferedImage image;
        try {
            File input = new File("C:\\Users\\NAME\\GIT\\GemeinsamesProjekt\\Philipp_Projekte\\SchwarzWeissFilter\\color.jpg");
            image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();
            for(int i=0; i<height; i++) {
                for(int j=0; j<width; j++) {
                    Color color = new Color(image.getRGB(j, i));

                    int red = (color.getRed());
                    int green = (color.getGreen());
                    int blue = (color.getBlue());
                    //Hier Methoden
                    int[] ausgabe = {color.getRed(), color.getGreen(), color.getBlue()};
                    if(auswahl.matches("^[1,2,3,4,5,6]$")){
                        switch (auswahl) {
                            case "1" -> ausgabe = farbMixGenerator.KorrekterGraufilter(red,green,blue); //fehler
                            case "2" -> ausgabe = farbMixGenerator.gleichartigerGraufilter(red,green,blue);
                            case "3" -> ausgabe = farbMixGenerator.BinaeirFilter(red,green,blue);
                            case "4" -> ausgabe = farbMixGenerator.SimplifizierteFarben(red,green,blue);
                            case "5" -> ausgabe = farbMixGenerator.ZufaelligeVertauschung(red,green,blue);
                            case "6" -> ausgabe = farbMixGenerator.ZufaelligeVertauschung2(red,green,blue,zufallszahl1,zufallszahl2,zufallszahl3);
                            default -> System.out.println("Die Eingabe ist nicht gültig");
                        }
                    }
                    else {
                        System.out.println("Ungültige Eingabe");
                    }

                    Color newColor = new Color(ausgabe[0], ausgabe[1], ausgabe[2]);
                    image.setRGB(j,i,newColor.getRGB());
                }
            }
            File ouptut = new File("C:\\Users\\NAME\\GIT\\GemeinsamesProjekt\\Philipp_Projekte\\SchwarzWeissFilter\\GetauschteFarben.jpg");
            System.out.println("Done");
            ImageIO.write(image, "jpg", ouptut);
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}

