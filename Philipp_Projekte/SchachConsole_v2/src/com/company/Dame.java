package com.company;

public class Dame extends  Figuren{
    public Dame(int positionX, int positionY, String team) {
        super(positionX, positionY, team);
    }

    @Override
    public Boolean Bewegen(int ZielX, int ZielY,Figuren [][] Spielfeld) {
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
        for (int i = 1; i < 8; i++) {
             mitzähler =1;
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
        return "D";
    }
}
