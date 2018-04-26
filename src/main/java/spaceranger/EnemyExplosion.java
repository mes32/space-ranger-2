/*
    EnemyExplosion.java

    This Sprite represents explosions produced by EnemyShip being destroyed
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class EnemyExplosion extends Sprite {

    private static final long STAGE_DURATION = 10;
    private static final String[] IMAGE_PATHS = {
        "/images/ExplosionStage0.png",
        "/images/ExplosionStage1.png",
        "/images/ExplosionStage2.png",
        "/images/ExplosionStage3.png",
        "/images/ExplosionStage4.png",
        "/images/ExplosionStage5.png",
        "/images/ExplosionStage6.png",
        "/images/ExplosionStage7.png",
        "/images/ExplosionStage8.png",
        "/images/ExplosionStage9.png",
        "/images/ExplosionStage10.png",
        "/images/ExplosionStage11.png",
        "/images/ExplosionStage12.png",
        "/images/ExplosionStage13.png",
        "/images/ExplosionStage14.png",
        "/images/ExplosionStage15.png"
    };
    private static final int NUM_IMAGE_PATHS = IMAGE_PATHS.length;

    private int count;

    protected EnemyShip parent;
    protected boolean burntOut;
    protected long lastTime;

    EnemyExplosion(GameBoard board, EnemyShip parent) {
        super(board, IMAGE_PATHS[0]);

        hitPoints = new HitPoints(1);
        collideDamage = 0;

        count = 0;
        this.parent = parent;
        dx = parent.getMoveX();
        dy = parent.getMoveY();

        burntOut = false;
        lastTime = System.currentTimeMillis();
    }

    public void update(long time) {
        super.update();

        if (time - lastTime >= STAGE_DURATION) {
            lastTime = time;
            count++;
            if (count < NUM_IMAGE_PATHS - 1) {
                image = loadImage(IMAGE_PATHS[count]);
            } else {
                burntOut = true;
            }
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
