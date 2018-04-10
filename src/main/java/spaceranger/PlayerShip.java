/*
    PlayerShip.java

    The Sprite representing the player character/avatar
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerShip extends Sprite {

    private static final String IMAGE_PATH = "/images/PlayersShip.png";

    private boolean isDestroyed;

    PlayerShip(GameBoard board) {
        super(board, IMAGE_PATH);
        isDestroyed = false;
    }

    public void update() {
        super.update();

        for (Sprite sprite : board.getSprites()) {
            if (sprite.isEnemy() && this.collision(sprite) || sprite.isEnemyProjectile() && this.collision(sprite)) {
                sprite.hit();
                isDestroyed = true;
                break;
            }
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void fireMissile() {
        PlayerProjectile missileLeft = new PlayerProjectile(board);
        PlayerProjectile missileRight = new PlayerProjectile(board);
        missileLeft.setInitialPosition(x + 10, y + 15);
        missileRight.setInitialPosition(x + width - 5 - 10, y + 15);
        board.addSprite(missileLeft);
        board.addSprite(missileRight);
    }
}
