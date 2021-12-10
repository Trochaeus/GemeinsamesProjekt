package com.company;
public class Springer extends  Figuren{
    public Springer(int positionX, int positionY, String team) {
        super(positionX, positionY, team);
    }
    @Override
    public Boolean Bewegen(int ZielX, int ZielY, Figuren[][] Spielfeld) {
        if ((ZielX-getPositionX()==2 && ZielY-getPositionY() ==1) || (ZielX-getPositionX()==2 && ZielY-getPositionY() == -1) || (ZielX-getPositionX()== -2 && ZielY-getPositionY() == 1) || (ZielX-getPositionX()==-2 && ZielY-getPositionY() ==-1) || (ZielX-getPositionX()==1 && ZielY-getPositionY()==2) || (ZielX-getPositionX()==1 && ZielY-getPositionY()==-2) || (ZielX-getPositionX() == -1 && ZielY-getPositionY() ==2) || (ZielX-getPositionX()==-1 && ZielY-getPositionY()==-2)) {
            if (Spielfeld[ZielX][ZielY] == null ||Spielfeld[ZielX][ZielY].getTeam().equals(Spielfeld[getPositionX()][getPositionY()].getTeam())) {
                return true;
            }
        }
        return false;
    }
        @Override
    public String toString() {
        return "S";
    }
}
