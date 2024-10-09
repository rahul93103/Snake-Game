//package snakegame;
//import javax.swing.*;
//import java.awt.event.KeyEvent;
//
//public class SnakeGame extends JFrame{
//    SnakeGame(){
//        super("Snake Game");
//        add(new Board());
//        pack();
//
//
//        setLocationRelativeTo(null);
//        setResizable(false);
//
//    }
//    public static void main(String[] args) {
//
//        new SnakeGame().setVisible(true);;
//    }
//}

package snakegame;

import javax.swing.*;

public class SnakeGame extends JFrame {

    SnakeGame() {
        super("Snake Adventure"); // Changed window title
        add(new GamePanel()); // Changed Board to GamePanel
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        JFrame gameFrame = new SnakeGame();
        gameFrame.setVisible(true); // Show frame in the main method
    }
}
