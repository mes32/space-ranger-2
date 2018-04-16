/*
    PlayerShip.java

    The Sprite representing the player character/avatar
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerShip extends Sprite {

    private static final String IMAGE_PATH = "/images/PlayersShip.png";
    private static final long FIRING_PERIOD = 400;

    private long lastFiringTime;
    private boolean firing;
    private boolean isDestroyed;

    PlayerShip(GameBoard board) {
        super(board, IMAGE_PATH);
        lastFiringTime = 0L;
        firing = false;
        isDestroyed = false;
    }

    public void update(long time) {
        super.update();

        for (EnemyProjectile p : board.getEnemyProjectiles()) {
            if (this.collision(p)) {
                p.hit();
                isDestroyed = true;
                break;
            }
        }

        for (EnemyShip enemy : board.getEnemies()) {
            if (this.collision(enemy)) {
                enemy.hit();
                isDestroyed = true;
                break;
            }
        }

        if (firing && time - lastFiringTime > FIRING_PERIOD) {
            fireMissile();
            lastFiringTime = time;
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    private void fireMissile() {
        PlayerProjectile missileLeft = new PlayerProjectile(board);
        PlayerProjectile missileRight = new PlayerProjectile(board);
        missileLeft.setInitialPosition(x + 10, y + 15);
        missileRight.setInitialPosition(x + width - 5 - 10, y + 15);
        board.insert(missileLeft);
        board.insert(missileRight);
    }
}
