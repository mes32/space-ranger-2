/*
    EnemyProjectile.java

    This Sprite representing projectiles fired by EnemyShip
 */

package spaceranger.sprite;

import spaceranger.*;

import java.awt.*;
import javax.swing.*;

public class EnemyProjectile extends Sprite {

    private static final String IMAGE_PATH = "/images/EnemyShot.png";

    EnemyProjectile(GameBoard board) {
        super(board, IMAGE_PATH);
        dy = 4;

        hitPoints = new HitPoints(1);
        collideDamage = 20;
    }

    @Override
    public boolean isActive() {
        if (hitPoints.isDestroyed() || y > board.getHeight()) {
            return false;
        } else {
            return true;
        }
    }
}
