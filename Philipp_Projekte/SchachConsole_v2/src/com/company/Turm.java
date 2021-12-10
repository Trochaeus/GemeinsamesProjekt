package com.company;

public class Turm extends  Figuren{
    public Turm(int positionX, int positionY, String team) {
        super(positionX, positionY, team);
    }

    @Override
    public Boolean Bewegen(int ZielX, int ZielY, Figuren[][] Spielfeld) {
        int mitzähler =1;
        if ((ZielX-getPositionX() == 0) && (ZielY-getPositionY() != 0) ) {
            if (ZielY-getPositionY()>0) {
                for (int i = getPositionY(); i < ZielY; i++) {
                    if (i!= ZielY-1 && Spielfeld[getPositionX()][getPositionY()+mitzähler] != null || (Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam()))) {
                        return false;
                    }
                    mitzähler++;
                }
            }
            else if (ZielY-getPositionY()<0)
            {
                for (int i = getPositionY(); i < ZielY; i++) {
                    if (i!= ZielY-1 && Spielfeld[getPositionX()][getPositionY()-mitzähler] != null || (Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam()))) {
                        return false;
                    }
                    mitzähler++;
                }
            }
            return true;
        }
        else if ((ZielX-getPositionX() != 0) && (ZielY-getPositionY() == 0))
        {
            if (ZielX-getPositionX()>0) {
                for (int i = getPositionX(); i < ZielX; i++) {
                    if (i!= ZielX-1 && Spielfeld[getPositionX()+mitzähler][getPositionY()] != null || (Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam()))) {
                        return false;
                    }
                    mitzähler++;
                }
            }
            else if (ZielX-getPositionX()<0)
            {
                for (int i = getPositionX(); i < ZielY; i++) {
                    if (i!= ZielY-1 && Spielfeld[getPositionX()-mitzähler][getPositionY()] != null || (Spielfeld[ZielX][ZielY] != null && Spielfeld[ZielX][ZielY].getTeam().equals(getTeam()))) {
                        return false;
                    }
                    mitzähler++;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "T";
    }
}
