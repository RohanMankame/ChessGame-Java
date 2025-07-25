package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Program Started");

        JFrame window = new JFrame("ChessGame"); // create our JFrame window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close program on exiting window
        window.setResizable(false); //not resizable

        GamePanel gp = new GamePanel(); // new game panel
        window.add(gp); // add game panel to our window
        window.pack(); // adjust window size to game panel height & width


        window.setLocationRelativeTo(null); // center window
        window.setVisible(true); // make window visible

        gp.launchGame(); // call to start thread

    }
}