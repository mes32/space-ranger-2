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

    private static final int MOVE_PIXELS = 3;

    private PlayerShip player;

    GameplayKeyAdapter(PlayerShip player) {
        super();
        this.player = player;
    }

    public static int getMovePixels() {
        return MOVE_PIXELS;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            player.setMoveX(0);
        } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            player.setMoveY(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.setMoveX(-1 * MOVE_PIXELS);
        } else if (key == KeyEvent.VK_RIGHT) {
            player.setMoveX(MOVE_PIXELS);
        } else if (key == KeyEvent.VK_UP) {
            player.setMoveY(-1 * MOVE_PIXELS);
        } else if (key == KeyEvent.VK_DOWN) {
            player.setMoveY(MOVE_PIXELS);
        } else if (key == KeyEvent.VK_SPACE) {
            player.fireMissile();
        }
    }
}
