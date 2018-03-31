/*
    GameGUI.java

    Launcher class for the game "Space Ranger II"
 */

package spaceranger;

import java.io.*;
import javax.swing.*;

public class GameGUI extends JFrame {
    private static final String TITLE = "Space Ranger II";

    GameGUI() {
        setSize(250, 200);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
