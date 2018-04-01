/*
    EnemyProjectile.java

    This Sprite representing projectiles fired by EnemyShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class EnemyProjectile extends Sprite {

    private static final String IMAGE_PATH = "/images/EnemyShot.png";

    EnemyProjectile(GameBoard board) {
        super(board, IMAGE_PATH);
        dy = 4;
    }

    public boolean isEnemyProjectile() {
        return true;
    }

    public boolean isActive() {
        if (isHit || y > board.getHeight()) {
            return false;
        } else {
            return true;
        }
    }
}
