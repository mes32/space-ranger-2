/*
    Main.java

    Launcher class for the game "Space Ranger II"
 */

package spaceranger;

import spaceranger.gui.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameGUI gui = new GameGUI();
        });
    }
}
