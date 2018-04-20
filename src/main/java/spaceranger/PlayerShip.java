/*
    PlayerShip.java

    The Sprite representing the player character/avatar
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerShip extends Sprite {

    private static final String IMAGE_PATH = "/images/PlayersShip.png";
    private static final long BULLET_FIRING_PERIOD = 100;
    private static final long MISSILE_FIRING_PERIOD = 400;

    private boolean firing;
    private boolean isDestroyed;
    private long bulletFiredAt;
    private long missileFiredAt;
    private boolean missileOffset;

    PlayerShip(GameBoard board) {
        super(board, IMAGE_PATH);
        firing = false;
        isDestroyed = false;
        bulletFiredAt = 0L;
        missileFiredAt = 0L;
        missileOffset = false;
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

        if (firing) {
            if (time - bulletFiredAt > BULLET_FIRING_PERIOD) {
                fireBullets();
                bulletFiredAt = time;
            }
            if (time - missileFiredAt > MISSILE_FIRING_PERIOD) {
                fireMissiles();
                missileFiredAt = time;
            }
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    private void fireBullets() {
        PlayerBullet left = new PlayerBullet(board);
        PlayerBullet right = new PlayerBullet(board);
        left.setInitialPosition(x + 12, y + 5);
        right.setInitialPosition(x + width - 5 - 12, y + 5);
        board.insert(left);
        board.insert(right);
    }

    private void fireMissiles() {
        PlayerMissile left = new PlayerMissile(board);
        PlayerMissile right = new PlayerMissile(board);
        if (missileOffset) {
            left.setInitialPosition(x + 5, y + 15);
            right.setInitialPosition(x + width - 5 - 5, y + 15);
        } else {
            left.setInitialPosition(x + 10, y + 15);
            right.setInitialPosition(x + width - 5 - 10, y + 15);
        }
        missileOffset = !missileOffset;
        board.insert(left);
        board.insert(right);
    }
}
