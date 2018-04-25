/*
    EnemyProjectile.java

    This Sprite representing projectiles fired by EnemyShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class EnemySpark extends Sprite {

    private static final long DURRATION = 50;
    private static final String[] IMAGE_PATHS = {
        "/images/EnemySpark0.png",
        "/images/EnemySpark1.png",
        "/images/EnemySpark2.png"
    };
    private static final int NUM_IMAGE_PATHS = IMAGE_PATHS.length;

    private static int count = 0;

    protected EnemyShip parent;
    protected boolean burntOut;
    protected long startTime;

    EnemySpark(GameBoard board, EnemyShip parent) {
        super(board, IMAGE_PATHS[count]);
        count++;
        if (count >= NUM_IMAGE_PATHS) {
            count = 0;
        }

        hitPoints = new HitPoints(1);
        collideDamage = 0;

        this.parent = parent;
        dx = parent.getMoveX();
        dy = parent.getMoveY();

        burntOut = false;
        startTime = System.currentTimeMillis();
    }

    public void update(long time) {
        super.update();

        dx = parent.getMoveX();
        dy = parent.getMoveY();

        if (time - startTime >= DURRATION) {
            burntOut = true;
        } 
    }

    public boolean isActive() {
        if (burntOut || y > board.getHeight()) {
            return false;
        } else {
            return true;
        }
    }
}
