package com.company;

public abstract class Figuren {

    private int PositionX;
    private int PositionY;
    private String Team;


    public int getPositionX() {
        return PositionX;
    }

    public void setPositionX(int positionX) {
        PositionX = positionX;
    }

    public int getPositionY() {
        return PositionY;
    }

    public void setPositionY(int positionY) {
        PositionY = positionY;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public Figuren(int positionX, int positionY, String team) {
        PositionX = positionX;
        PositionY = positionY;
        Team = team;
    }
    public abstract Boolean Bewegen (int ZielX, int ZielY, Figuren [][] Spielfeld);

    @Override
    public abstract String toString();
}
