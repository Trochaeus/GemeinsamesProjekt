package com.company;

public class Bauern extends Figuren{

    public Boolean bauerSchonBewegt= false;

    public Bauern(int positionX, int positionY, String team, Boolean bauerSchonBewegt) {
        super(positionX, positionY, team);
        this.bauerSchonBewegt = bauerSchonBewegt;
    }

    public Bauern(int positionX, int positionY, String team) {
        super(positionX, positionY, team);
    }

    @Override
    public Boolean Bewegen(int ZielX, int ZielY, Figuren [][] Spielfeld) {

            if (getTeam().equals("Weiß")) {
                // Bauer 1 nach vorne bewegen
                if (ZielX == getPositionX() && ZielY == getPositionY() - 1 && Spielfeld[getPositionX()][getPositionY() - 1] == null) {
                    return true;
                }
                else if (ZielX == getPositionX() && ZielY == getPositionY() - 2 && Spielfeld[getPositionX()][getPositionY() - 2] == null&& bauerSchonBewegt == false)
                {
                    bauerSchonBewegt = true;
                    return true;
                }
                // Bauer Angreifen
                else if ((ZielX == getPositionX() - 1 && ZielY == getPositionY() - 1) || ((ZielX == getPositionX() + 1 && ZielY == getPositionY() - 1))) {
                    if (Spielfeld[ZielX][ZielY].getTeam().equals("Schwarz")) {
                        return true;
                    }
                }
            } else if (getTeam().equals("Schwarz")) {
                // Bauer 1 nach vorne Bewegen
                if (ZielX == getPositionX() && ZielY == getPositionY() + 1 && Spielfeld[getPositionX()][getPositionY() + 1] == null) {
                    return true;
                }
                else if (ZielX == getPositionX() && ZielY == getPositionY() + 2 && Spielfeld[getPositionX()][getPositionY() + 2] == null&& bauerSchonBewegt == false)
                {
                    bauerSchonBewegt= true;
                    return true;
                }
                // Bauer Angreifen
                else if (((ZielX == getPositionX() - 1 && ZielY == getPositionY() + 1) || (ZielX == getPositionX() + 1 && ZielY == getPositionY() + 1))) {
                    if (Spielfeld[ZielX][ZielY].getTeam().equals("Weiß")) {
                        return true;
                    }
                }
            }

    return false;
    }
    @Override
    public String toString() {
        return "B";
    }
}
