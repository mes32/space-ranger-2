/*
    GameGUI.java

    The main program window
 */

package spaceranger.gui;

import java.io.*;
import javax.swing.*;

public class GameGUI {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String TITLE = "Space Ranger II";

    private JFrame frame;
    private GameBoard board;

    public GameGUI() {
        frame = new JFrame();
        board = new GameBoard(this);

        frame.add(board);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
