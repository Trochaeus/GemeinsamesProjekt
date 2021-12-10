package com.company;
import java.sql.SQLOutput;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Schachbrett {
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_YELLOW = "\u001B[33m";
    public Boolean weißSchwarz = true;
    public Boolean WeißSchach =false;
    public int MouseXFigure;
    public int MouseYFigure;
    public int MouseXFigureZiel;
    public int MouseYFigureZiel;
    public Boolean ErsterMouseKlick =true;
    public Boolean SchwarzSchach = false;
    Figuren [][] Spielfeld = new Figuren[8][8];

    public JFrame frame = new JFrame();
    public JPanel pn;
    public BufferedImage all;
    public Image imgs[];
    public String imagePath ="C:\\Users\\pgerhardy\\GIT\\GemeinsamesProjekt\\Philipp_Projekte\\SchachConsole_v2\\schach.png";
    public Schachbrett() throws IOException {
        Spielfeld [0][0] = new Turm(0,0,"Schwarz");
        Spielfeld [7][0] = new Turm(7,0,"Schwarz");
        Spielfeld [0][7] = new Turm(0,7,"Weiß");
        Spielfeld [7][7] = new Turm(7,7,"Weiß");
        Spielfeld [1][0] = new Springer(1,0,"Schwarz");
        Spielfeld [6][0] = new Springer(6,0,"Schwarz");
        Spielfeld [1][7] = new Springer(1,7,"Weiß");
        Spielfeld [6][7] = new Springer(6,7,"Weiß");
        Spielfeld [2][0] = new Laeufer(2,0,"Schwarz");
        Spielfeld [5][0] = new Laeufer(5,0,"Schwarz");
        Spielfeld [2][7] = new Laeufer(2,7,"Weiß");
        Spielfeld [5][7] = new Laeufer(5,7,"Weiß");
        Spielfeld [3][0] = new Dame(3, 0,"Schwarz");
        Spielfeld [3][7] = new Dame(3, 7,"Weiß");
        Spielfeld [4][0] = new König(4, 0,"Schwarz");
        Spielfeld [4][7] = new König(4, 7,"Weiß");
        for (int i = 0; i < 8; i++) {
            Spielfeld [i] [1] = new Bauern(i,1,"Schwarz");
            Spielfeld [i] [6] = new Bauern(i,6,"Weiß");
        }

        this.initFrame();
        this.readAndSetImages();

    }

    private void initFrame() throws IOException {
        frame.setBounds(10,10,512,512);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(3);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (ErsterMouseKlick) {
                    System.out.println("Erster klick");
                    MouseXFigure = e.getX()/64;
                    MouseYFigure =e.getY()/64;
                    ErsterMouseKlick = false;
                }
                else
                {
                    System.out.println("Zweiter klick");
                    MouseXFigureZiel=e.getX()/64;
                    MouseYFigureZiel=e.getY()/64;
                    Spielzug(MouseXFigureZiel,MouseYFigureZiel,MouseXFigure,MouseYFigure);
                    ErsterMouseKlick = true;
                }
                System.out.println(e.getX()+" "+e.getY());
                System.out.println((int)Math.floor(e.getX()/64)+" "+Math.floor(e.getY()/64));
                try {
                    GuiErstellen();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void readAndSetImages() throws IOException {
        all = ImageIO.read(new File(this.imagePath));
        imgs = new Image[12];
        int ind=0;
        for (int i = 0; i < 400; i+=200) {
            for (int j = 0; j < 1200; j+=200) {
                imgs[ind] = all.getSubimage(j,i,200,200).getScaledInstance(64,64,BufferedImage.SCALE_SMOOTH);
                ind++;
            }

        }

    }

    public void SpielfeldZeichnen ()
    {
        System.out.print("    ");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "   ");
            for (int j = 0; j < 8; j++) {
                if (Spielfeld[j][i] == null) {
                    System.out.print("  ");
                }
                else {
                    if (Spielfeld[j][i].getTeam().equals("Weiß")) {
                        System.out.print(ANSI_RED +Spielfeld[j][i].toString()+ " " +ANSI_RESET);
                    }
                    else if (Spielfeld[j][i].getTeam().equals("Schwarz"))
                    {
                        System.out.print(ANSI_YELLOW+Spielfeld[j][i].toString()+ " "+ANSI_RESET);
                    }
                    else
                    {
                        System.out.print(Spielfeld[j][i].toString()+ " ");
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public void neueFigureErstellen (int ZielX, int ZielY, int FigureX, int FigureY)
    {
        if (Spielfeld[FigureX][FigureY] instanceof Bauern) {
            Spielfeld[ZielX][ZielY] = new Bauern(ZielX,ZielY,Spielfeld[FigureX][FigureY].getTeam(),((Bauern) Spielfeld[FigureX][FigureY]).bauerSchonBewegt);
            Spielfeld[FigureX][FigureY] = null;
        }
        else if (Spielfeld[FigureX][FigureY] instanceof Turm)
        {
            Spielfeld[ZielX][ZielY] = new Turm(ZielX,ZielY,Spielfeld[FigureX][FigureY].getTeam());
            Spielfeld[FigureX][FigureY] = null;
        }
        else if (Spielfeld[FigureX][FigureY] instanceof Laeufer)
        {
            Spielfeld[ZielX][ZielY] = new Laeufer(ZielX,ZielY,Spielfeld[FigureX][FigureY].getTeam());
            Spielfeld[FigureX][FigureY] = null;
        }
        else if (Spielfeld[FigureX][FigureY] instanceof Springer)
        {
            Spielfeld[ZielX][ZielY] = new Springer(ZielX,ZielY,Spielfeld[FigureX][FigureY].getTeam());
            Spielfeld[FigureX][FigureY] = null;
        }
        else if (Spielfeld[FigureX][FigureY] instanceof Dame)
        {
            Spielfeld[ZielX][ZielY] = new Dame(ZielX,ZielY,Spielfeld[FigureX][FigureY].getTeam());
            Spielfeld[FigureX][FigureY] = null;
        }
        else if (Spielfeld[FigureX][FigureY] instanceof König)
        {
            Spielfeld[ZielX][ZielY] = new König(ZielX,ZielY,Spielfeld[FigureX][FigureY].getTeam());
            Spielfeld[FigureX][FigureY] = null;
        }
    }

    public void Spielzug (int ZielX, int ZielY, int FigureX, int FigureY)
    {
        if (AusgewähltEineFigure(FigureX,FigureY)) {
            if (Spielfeld[FigureX][FigureY].Bewegen(ZielX,ZielY,Spielfeld)  && richtigeFarbe(FigureX,FigureY))
            {
                neueFigureErstellen(ZielX,ZielY,FigureX,FigureY);
            }
        }
        for(int i = 0; i < 20; i++) {
            System.out.println(" ");
        }
        SpielfeldZeichnen();
    }
    public Boolean AusgewähltEineFigure (int X, int Y)
    {
        if (Spielfeld[X][Y] != null) {
            return true;
        }
        return false;
    }
    public List<Integer> königsPositionen ()
    {
        List<Integer> königspositionen = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if (Spielfeld[j][i] != null && Spielfeld[j][i] instanceof König)
                {
                    königspositionen.add(Spielfeld[j][i].getPositionX());
                    königspositionen.add(Spielfeld[j][i].getPositionY());
                }

            }
        }
        return königspositionen;
    }
    public Boolean richtigeFarbe (int FigureX, int FigureY)
    {
        if (weißSchwarz)
        {
            if (Spielfeld[FigureX][FigureY] != null && Spielfeld[FigureX][FigureY].getTeam().equals("Weiß")) {
                weißSchwarz =false;
                return true;
            }
        }
        else if (weißSchwarz==false) {
            if (Spielfeld[FigureX][FigureY] != null && Spielfeld[FigureX][FigureY].getTeam().equals("Schwarz")) {
                weißSchwarz= true;
                return true;
            }
        }

        return false;
    }
    public Boolean SpiefeldRückgängigmachen (int ZielXRückgängig, int ZielYRückgängig, int FigureXRückgängig, int FigureYRückgängig)
    {
        Figuren ZielKopie = Spielfeld[ZielXRückgängig][ZielYRückgängig];
        String eigenesTeam;
        if (weißSchwarz) {
            eigenesTeam= "Weiß";
        }
        else
        {
            eigenesTeam= "Schwarz";
        }
        neueFigureErstellen(ZielXRückgängig,ZielYRückgängig,FigureXRückgängig,FigureYRückgängig);

        if (eigenerKönigSchach(eigenesTeam) == false) {
            neueFigureErstellen(FigureXRückgängig,FigureYRückgängig,ZielXRückgängig,ZielYRückgängig);
            return false;
        }
        return false;

    }
    public Boolean eigenerKönigSchach (String Team)
    {
        List <Integer> königsposition = königsPositionen();
        if (Team.equals( "Weiß")) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Spielfeld[i][j] != null && Spielfeld[i][j].Bewegen(königsposition.get(2),königsposition.get(3),(Spielfeld))) {
                        return false;
                    }

                }
            }
        }
        else if (Team.equals("Schwarz"))
        {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Spielfeld[i][j] != null && Spielfeld[i][j].Bewegen(königsposition.get(0),königsposition.get(1),(Spielfeld))) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    public void GuiErstellen () throws IOException {
        pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Boolean WeißSchwarz = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (WeißSchwarz) {
                            g.setColor(Color.WHITE);
                        } else {
                            g.setColor(Color.red);
                        }
                        g.fillRect(j*64,i*64,64,64);
                        WeißSchwarz =!WeißSchwarz;
                    }
                    WeißSchwarz =!WeißSchwarz;
                }
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        int ind=0;
                        if (Spielfeld[j][i] != null && Spielfeld[j][i].getTeam().equals("Schwarz")) {
                            ind = 6;
                        }

                        if (Spielfeld[j][i] != null && Spielfeld[j][i] instanceof Bauern)
                        {
                            ind = ind +5;
                        }
                        else if (Spielfeld[j][i] != null && Spielfeld[j][i] instanceof König) {
                            ind = ind + 0;
                        }
                        else if (Spielfeld[j][i] != null && Spielfeld[j][i] instanceof Dame)
                        {
                            ind = ind +1;
                        }
                        else if (Spielfeld[j][i] != null && Spielfeld[j][i] instanceof Springer)
                        {
                            ind = ind + 3;
                        }
                        else if (Spielfeld[j][i] != null && Spielfeld[j][i] instanceof Laeufer)
                        {
                            ind = ind + 2 ;
                        }
                        else if (Spielfeld[j][i] != null && Spielfeld[j][i] instanceof Turm)
                        {
                            ind = ind +4;
                        }

                        if (Spielfeld[j][i] != null) {
                            g.drawImage(imgs[ind],j*64,i*64,this);
                        }

                    }
                }
            }
        };
        frame.add(pn);
        frame.setVisible(true);
    }
    public  Figuren getFigure (int x, int y)
    {
        int xp = x/64;
        int yp = x/64;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Spielfeld[j][i].getPositionX() == xp && Spielfeld[j][i].getPositionY() == yp) {
                   return  Spielfeld[j][i];
                }
            }
        }
        return null;
    }
}
