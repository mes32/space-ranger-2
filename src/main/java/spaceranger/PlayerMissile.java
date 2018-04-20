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
        acceleratorCounter = 0;
    }

    public void update() {
        super.update();

        acceleratorCounter++;
        if (dy > -7 && acceleratorCounter % 10 == 0) {
            dy--;
            acceleratorCounter = 0;
        }
    }
}
