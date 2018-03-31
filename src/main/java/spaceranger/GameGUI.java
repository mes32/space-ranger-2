/*
    GameGUI.java

    The main program window
 */

package spaceranger;

import java.io.*;
import javax.swing.*;

public class GameGUI {

    private static final String TITLE = "Space Ranger II";

    private JFrame frame;
    private GameBoard board;

    GameGUI() {
        frame = new JFrame();
        board = new GameBoard();

        frame.add(board);
        frame.setSize(1000, 700);
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
