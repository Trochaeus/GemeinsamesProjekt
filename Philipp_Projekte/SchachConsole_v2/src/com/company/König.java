package com.company;

public class König extends  Figuren
{
    public König(int positionX, int positionY, String team) {
        super(positionX, positionY, team);
    }

    @Override
    public Boolean Bewegen(int ZielX, int ZielY, Figuren[][] Spielfeld) {
        if ((ZielX-getPositionX())<2 && (ZielX-getPositionX())>-2 && (ZielY-getPositionY()<2) && (ZielY-getPositionY())>-2) {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "K";
    }
}
