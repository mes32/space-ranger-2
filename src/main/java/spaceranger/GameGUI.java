/*
    GameGUI.java

    The main program window
 */

package spaceranger;

import java.io.*;
import javax.swing.*;

public class GameGUI extends JFrame {
    private static final String TITLE = "Space Ranger II";

    private GameBoard board;

    GameGUI() {
        board = new GameBoard();
        add(board);
        setSize(250, 200);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
