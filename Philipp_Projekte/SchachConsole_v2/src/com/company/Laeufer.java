package com.company;

public class Laeufer extends  Figuren{
    public Laeufer(int positionX, int positionY, String team) {
        super(positionX, positionY, team);
    }
    @Override
    public Boolean Bewegen(int ZielX, int ZielY, Figuren[][] Spielfeld) {
        for (int i = 1; i < 8; i++) {
            int mitzähler =1;
            if (getPositionX()+i==ZielX && getPositionY()+i==ZielY) {
                for (int j = getPositionX(); j <ZielX; j++) {
                    if (j != ZielX-1 &&(Spielfeld[getPositionX()+mitzähler][getPositionY()+mitzähler] !=null||(Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam())))) {
                        return false;
                    }
                    mitzähler++;
                }
               return true;
            }
            if (getPositionX()+i==ZielX && getPositionY()-i==ZielY) {
                for (int j = getPositionX(); j <ZielX; j++) {
                    if (j != ZielX-1 &&(Spielfeld[getPositionX()+mitzähler][getPositionY()-mitzähler] !=null||(Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam())))) {
                        return false;
                    }
                    mitzähler++;
                }
                return true;
            }
            if (getPositionX()-i==ZielX && getPositionY()+i==ZielY) {
                for (int j = getPositionX(); j <ZielX; j++) {
                    if (j != ZielX-1 &&(Spielfeld[getPositionX()-mitzähler][getPositionY()+mitzähler] !=null||(Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam())))) {
                        return false;
                    }
                    mitzähler++;
                }
                return true;
            }
            if (getPositionX()-i==ZielX && getPositionY()-i==ZielY) {
                for (int j = getPositionX(); j >ZielX; j++) {
                    if (j != ZielX-1 &&(Spielfeld[getPositionX()-mitzähler][getPositionY()-mitzähler] !=null||(Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam())))) {
                        return false;
                    }
                    mitzähler++;
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "L";
    }
}
