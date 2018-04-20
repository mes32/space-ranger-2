/*
    EnemyShip.java

    This Sprite represents enemy characters
 */

package spaceranger;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class EnemyShip extends Sprite {

    private final int boardHeight;
    private int firingCycle;
    private long lastFiringTime;

    EnemyShip(GameBoard board, String imagePath) {
        super(board, imagePath);
        boardHeight = board.getHeight();
        firingCycle = 0;
        lastFiringTime = 0L;
        dy = 1;
        isHit = false;
    }

    public void update(long time) {
        super.update();
    }

    public boolean isActive() {
        if (y > boardHeight || isHit) {
            return false;
        } else {
            return true;
        }
    }
}
