package sk.stuba.fei.uim.upb;

import javax.swing.JFrame;

import sk.stuba.fei.uim.upb.gui.GUI;


public class PasswordSecurityApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

    	GUI okno = new GUI();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        okno.setResizable(false);
        okno.setLocationRelativeTo(null);
    }
}
