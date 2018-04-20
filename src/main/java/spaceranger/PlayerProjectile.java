/*
    PlayerProjectile.java

    This Sprite represents projectiles fired by PlayerShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerProjectile extends Sprite {

    // private static final String IMAGE_PATH = "/images/PlayerMissile.png";

    protected int damage = 0;

    PlayerProjectile(GameBoard board, String imagePath) {
        super(board, imagePath);
        dy = -5;
    }

    public void update() {
        super.update();

        for (EnemyShip enemy : board.getEnemies()) {
            if (this.collision(enemy)) {
                enemy.damage(damage);
                this.hit();
                break;
            }
        }
    }

    public boolean isActive() {
        if (y + height < 0 || isHit) {
            return false;
        } else {
            return true;
        }
    }
}
