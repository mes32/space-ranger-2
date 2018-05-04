/*
    PlayerMissile.java

    This Sprite represents missiles fired by PlayerShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerMissile extends PlayerProjectile {

    private static final String IMAGE_PATH = "/images/PlayerMissile.png";

    private int acceleratorCounter;

    PlayerMissile(GameBoard board) {
        super(board, IMAGE_PATH);
        dy = -2;
        collideDamage = 10;
        acceleratorCounter = 0;
    }

    @Override
    public void update(long time) {
        super.update(time);

        // TODO: Acceleration should be a function of time not iterations
        acceleratorCounter++;
        if (dy > -7 && acceleratorCounter % 10 == 0) {
            dy--;
            acceleratorCounter = 0;
        }
    }
}
