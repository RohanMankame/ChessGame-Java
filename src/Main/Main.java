package Main;

import javax.swing.*;
/// /////////////////////////////////////////////////////////////////////////////////////////////// MUSIC
import javax.sound.sampled.*;
/// /////////////////////////////////////////////////////////////////////////////////////////////// MUSIC

public class Main {
    public static void main(String[] args) {

        System.out.println("Program Started");

/// /////////////////////////////////////////////////////////////////////////////////////////////// MUSIC

        JFrame window = new JFrame("ChessGame"); // create our JFrame window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close program on exiting window
        window.setResizable(false); //not resizable

        // Start music when the app starts
        startMusic("res/lofi-music.wav");



/// /////////////////////////////////////////////////////////////////////////////////////////////// MUSIC
        GamePanel gp = new GamePanel(); // new game panel
        window.add(gp); // add game panel to our window
        window.pack(); // adjust window size to game panel height & width


        window.setLocationRelativeTo(null); // center window
        window.setVisible(true); // make window visible

        gp.launchGame(); // call to start thread

    }
/// //////////////////////////////////////////////////////////////////////////////////////////// MUSIC
    private static void startMusic(String path) {
        try {
            javax.sound.sampled.AudioInputStream audioIn =
                    javax.sound.sampled.AudioSystem.getAudioInputStream(new java.io.File(path));

            // Create audio clip
            Clip bgClip = AudioSystem.getClip();
            bgClip.open(audioIn);
            bgClip.loop(javax.sound.sampled.Clip.LOOP_CONTINUOUSLY);
            bgClip.start();
        } catch (javax.sound.sampled.UnsupportedAudioFileException
                 | javax.sound.sampled.LineUnavailableException
                 | java.io.IOException ex) {
            ex.printStackTrace();
        }
    }

/// //////////////////////////////////////////////////////////////////////////////////////////// MUSIC
}