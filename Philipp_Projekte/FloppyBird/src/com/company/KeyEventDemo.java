
package com.company;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventDemo extends JFrame
        implements KeyListener,
        ActionListener
{
    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        KeyEventDemo frame = new KeyEventDemo("KeyEventDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        frame.addComponentsToPane();


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {

        JButton button = new JButton("Clear");
        button.addActionListener(this);

        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);

        //typingArea.setFocusTraversalKeysEnabled(false);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(button, BorderLayout.PAGE_END);
    }

    public KeyEventDemo(String name) {
        super(name);
    }


    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        //System.out.println("Typed: '" + e.getKeyChar() + "'");
        typingArea.setText("");
    }

    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed: '" + e.getKeyChar() + "'");
        typingArea.setText("");
        if(e.getKeyChar() == ' ') {
            System.out.println("Richtige Taste!");
        }
    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //System.out.println("Released: '" + e.getKeyChar() + "'");
        typingArea.setText("");
    }

    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
        displayArea.setText("");
        typingArea.setText("");

        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }
}