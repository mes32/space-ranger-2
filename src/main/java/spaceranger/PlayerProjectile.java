/*
    PlayerProjectile.java

    This Sprite represents projectiles fired by PlayerShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerProjectile extends Sprite {

    private static final String IMAGE_PATH = "/images/PlayerMissile.png";

    PlayerProjectile(GameBoard board) {
        super(board, IMAGE_PATH);
        dy = -5;
    }

    public void update() {
        super.update();

        for (Sprite sprite : board.getSprites()) {
            if (sprite.isEnemy() && this.collision(sprite)) {
                sprite.hit();
                this.hit();
                board.incrementScore();
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
