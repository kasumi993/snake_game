import java.awt.Color;

import javax.swing.JFrame;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        JFrame fenetre= new JFrame();
        Gameplay gameplay = new Gameplay();

        fenetre.setVisible(true);
        fenetre.setSize(905, 700);
        fenetre.setBackground(Color.darkGray);
        fenetre.setLocationRelativeTo(null);
        fenetre.add(gameplay);
    }
}