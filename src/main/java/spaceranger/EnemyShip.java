/*
    EnemyShip.java

    This Sprite represents enemy characters
 */

package spaceranger;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class EnemyShip extends Sprite {

    private static final String IMAGE_PATH = "/images/EnemyShip.png";
    private static final long FIRING_PERIOD = 400;
    private static final int FIRING_BURST = 4;

    private final int boardHeight;
    private int firingCycle;
    private long lastFiringTime;

    EnemyShip(GameBoard board) {
        super(board, IMAGE_PATH);
        boardHeight = board.getHeight();
        firingCycle = 0;
        lastFiringTime = 0L;
        dy = 1;
        isHit = false;
    }

    public void update(long time) {
        super.update();

        if (time - lastFiringTime > FIRING_PERIOD) {
            firingCycle++;
            lastFiringTime = time;
            if (firingCycle % FIRING_BURST != 0) {
                fireShots();
            }   
        }
    }

    public void fireShots() {
        EnemyProjectile projectile = new EnemyProjectile(board);
        projectile.setInitialPosition(x + (int)(width / 2), y + height);
        board.insert(projectile);
    }

    public boolean isActive() {
        if (y > boardHeight || isHit) {
            return false;
        } else {
            return true;
        }
    }
}
