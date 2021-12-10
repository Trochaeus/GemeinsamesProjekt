import java.util.Random;

public class FarbMixGenerator {
    private int red;
    private int green;
    private int blue;
    private int random1;
    private int random2;
    private int random3;
    private int filteredRed;
    private int filteredGreen;
    private int filteredBlue;

    public int[] KorrekterGraufilter(int red,int green,int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        filteredRed = (int) Math.floor(red * 0.299);
        filteredGreen = (int) Math.floor(green * 0.587);
        filteredBlue = (int) Math.floor(blue * 0.114);
        int farblos = filteredRed+filteredGreen+filteredBlue;
        int[] ausgabe = {farblos,farblos,farblos};

        return ausgabe;
    }
    public int[] gleichartigerGraufilter(int red,int green,int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        int farblos = (int) Math.floor((red+green+blue)/3);
        int[] ausgabe = {farblos,farblos,farblos};

        return ausgabe;
    }
    public int[] BinaeirFilter(int red,int green,int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        int farblos = (int) Math.round((red + green + blue)/3);
        if(farblos > 128) {
            farblos = 255;
        }
        else {
            farblos = 0;
        }
        int[] ausgabe = {farblos,farblos,farblos};

        return ausgabe;
    }
    public int[] SimplifizierteFarben(int red,int green,int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        if(red > 128) {
            filteredRed = 255;
        }
        else {
            filteredRed = 0;
        }
        if(green > 128) {
            filteredGreen = 255;
        }
        else {
            filteredGreen = 0;
        }
        if(blue > 128) {
            filteredBlue = 255;
        }
        else {
            filteredBlue = 0;
        }
        int[] ausgabe = {filteredRed,filteredGreen,filteredBlue};

        return ausgabe;
    }
    public int[] ZufaelligeVertauschung(int red,int green,int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        Random random = new Random();
        int[] farben = {red,green,blue};
        int[] ausgabe = {farben[random.nextInt(3)],farben[random.nextInt(3)],farben[random.nextInt(3)]};

        return ausgabe;
    }
    public int[] ZufaelligeVertauschung2(int red,int green,int blue,int random1,int random2,int random3) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.random1 = random1;
        this.random2 = random2;
        this.random3 = random3;
        int[] farben = {red,green,blue};
        int[] ausgabe = {farben[random1],farben[random2],farben[random3]};

        return ausgabe;
    }
    public int[] RotePflanzen(int red,int green,int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        int[] ausgabe = {green,red,green};

        return ausgabe;
    }
    public int[] GleichmäßigesWeiß(int red,int green,int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        filteredRed = red;
        filteredGreen = green;
        filteredBlue = blue;

        if(red > 180) {
            filteredRed = 255;
        }
        if(green > 180) {
            filteredGreen = 255;
        }
        if(blue > 180) {
            filteredBlue = 255;
        }
        int[] ausgabe = {filteredRed,filteredGreen,filteredBlue};

        return ausgabe;
    }
}
