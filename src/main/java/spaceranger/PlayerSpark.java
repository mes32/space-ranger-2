/*
    PlayerSpark.java

    This Sprite representing sparks from projectiles hitting PlayerShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerSpark extends Sprite {

    private static final long DURRATION = 80;
    private static final String[] IMAGE_PATHS = {
        "/images/PlayerSpark0.png",
        "/images/PlayerSpark1.png",
        "/images/PlayerSpark2.png"
    };
    private static final int NUM_IMAGE_PATHS = IMAGE_PATHS.length;

    private static int count = 0;

    protected PlayerShip parent;
    protected boolean burntOut;
    protected long startTime;

    PlayerSpark(GameBoard board, PlayerShip parent) {
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
        super.update(parent.getMoveX(), parent.getMoveY());

        if (time - startTime >= DURRATION) {
            burntOut = true;
        } 
    }

    public boolean isActive() {
        if (burntOut) {
            return false;
        } else {
            return true;
        }
    }
}
