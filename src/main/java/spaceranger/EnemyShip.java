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
    private HitPoints hitPoints;

    EnemyShip(GameBoard board, String imagePath) {
        super(board, imagePath);
        boardHeight = board.getHeight();
        firingCycle = 0;
        lastFiringTime = 0L;
        dy = 1;
        hitPoints = new HitPoints(40);
    }

    public void damage(int damage) {
        hitPoints.damage(damage);
    }

    public void update(long time) {
        super.update();
    }

    public boolean isActive() {
        if (y > boardHeight || hitPoints.isDestroyed()) {
            return false;
        } else {
            return true;
        }
    }
}
