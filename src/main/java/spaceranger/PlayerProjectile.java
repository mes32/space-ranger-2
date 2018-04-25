/*
    PlayerProjectile.java

    This Sprite represents projectiles fired by PlayerShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerProjectile extends Sprite {

    protected int damage = 0;

    PlayerProjectile(GameBoard board, String imagePath) {
        super(board, imagePath);
        dy = -5;

        hitPoints = new HitPoints(1);
        collideDamage = 0;
    }

    public void update() {
        super.update();

        for (EnemyShip enemy : board.getEnemies()) {
            if (this.collision(enemy)) {
                damage(enemy);
                if (enemy.isActive()) {
                    EnemySpark spark = new EnemySpark(board, enemy);
                    int xOffset = (int)(x + width/2);
                    spark.setCenter(x, y);
                    board.insert(spark);
                } else {
                    board.incrementScore();
                }
                break;
            }
        }
    }

    public boolean isActive() {
        if (hitPoints.isDestroyed() || y + height < 0) {
            return false;
        } else {
            return true;
        }
    }
}
