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

    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingDown;
    private boolean movingUp;

    private PlayerShip player;

    GameplayKeyAdapter(PlayerShip player) {
        super();
        movingLeft = false;
        movingRight = false;
        movingDown = false;
        movingUp = false;
        this.player = player;
    }

    public static int getMovePixels() {
        return MOVE_PIXELS;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && movingLeft || key == KeyEvent.VK_RIGHT && movingRight) {
            movingRight = false;
            movingLeft = false;
            player.setMoveX(0);
        } else if (key == KeyEvent.VK_UP && movingUp || key == KeyEvent.VK_DOWN && movingDown) {
            movingUp = false;
            movingDown = false;
            player.setMoveY(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            movingRight = false;
            movingLeft = true;
            player.setMoveX(-1 * MOVE_PIXELS);
        } else if (key == KeyEvent.VK_RIGHT) {
            movingRight = true;
            movingLeft = false;
            player.setMoveX(MOVE_PIXELS);
        } else if (key == KeyEvent.VK_UP) {
            movingUp = true;
            movingDown = false;
            player.setMoveY(-1 * MOVE_PIXELS);
        } else if (key == KeyEvent.VK_DOWN) {
            movingUp = false;
            movingDown = true;
            player.setMoveY(MOVE_PIXELS);
        } else if (key == KeyEvent.VK_SPACE) {
            player.fireMissile();
        }
    }
}
