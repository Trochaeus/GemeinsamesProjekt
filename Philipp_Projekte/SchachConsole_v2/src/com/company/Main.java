package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException {
        List<Integer> l1 = new ArrayList<>();
	Schachbrett SCHACH = new Schachbrett();
    SCHACH.GuiErstellen();
    SCHACH.SpielfeldZeichnen();


    Scanner s = new Scanner(System.in);
    while (true)
    {

    }
    }
}
