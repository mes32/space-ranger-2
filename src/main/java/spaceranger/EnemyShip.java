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

        hitPoints = new HitPoints(40);
        collideDamage = 40;
    }

    @Override
    public boolean isActive() {
        if (hitPoints.isDestroyed() || y > boardHeight) {
            return false;
        } else {
            return true;
        }
    }
}
