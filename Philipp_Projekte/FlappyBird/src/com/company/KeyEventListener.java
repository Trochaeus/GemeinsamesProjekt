package com.company;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventListener extends JFrame implements KeyListener, ActionListener {
    JTextArea displayArea;
    JTextField typingArea;

    public KeyEventListener() {
        super("EventListener");
    }
    public static void KeyEventMain() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {
        KeyEventListener frame = new KeyEventListener();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane();
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {
        JButton button = new JButton("Clear");
        button.addActionListener(this);
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(button, BorderLayout.PAGE_END);
    }

    public void keyTyped(KeyEvent e) {
        //
    }


    @Override
    public void keyPressed(KeyEvent e) {
        typingArea.setText("");
        if(e.getKeyChar() == ' ') {
            Game.setSpaceIsPressed();
        }
    }


    public void keyReleased(KeyEvent e) {
        //
    }

    //Handle the button click.
    public void actionPerformed(ActionEvent e) {
        displayArea.setText("");
        typingArea.setText("");
        typingArea.requestFocusInWindow();
    }
}