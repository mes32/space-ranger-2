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
    private static final double P_FIRE = 0.01;

    private final int boardHeight;
    private Random random;

    EnemyShip(GameBoard board) {
        super(board, IMAGE_PATH);
        boardHeight = board.getHeight();
        random = new Random();
        dy = 1;
        isHit = false;
    }

    public void update() {
        super.update();

        if (random.nextDouble() <= P_FIRE) {
            fireShots();
        }
    }

    public void fireShots() {
        EnemyProjectile projectile = new EnemyProjectile(board);
        projectile.setInitialPosition(x + (int)(width / 2), y + height);
        // board.addSprite(projectile);
        board.insert(projectile);
    }

    public boolean isEnemy() {
        return true;
    }

    public boolean isActive() {
        if (y > boardHeight || isHit) {
            return false;
        } else {
            return true;
        }
    }
}
