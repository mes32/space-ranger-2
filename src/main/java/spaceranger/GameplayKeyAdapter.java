/*
    GameplayKeyAdapter.java

    Simple extender
 */

package spaceranger;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GameplayKeyAdapter extends KeyAdapter {

    private PlayerShip player;

    GameplayKeyAdapter(PlayerShip player) {
        super();
        this.player = player;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("GameplayKeyAdapter keyReleased()");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("GameplayKeyAdapter keyPressed()");
    }
}
