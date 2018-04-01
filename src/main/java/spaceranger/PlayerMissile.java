/*
    PlayerMissile.java

    This Sprite representing projectiles fired by PlayerShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerMissile extends Sprite {

    private static final String IMAGE_PATH = "/images/PlayerMissile.png";

    PlayerMissile(GameBoard board) {
        super(board, IMAGE_PATH);
        dy = -5;
    }

    public boolean isActive() {
        if (y + height < 0) {
            return false;
        } else {
            return true;
        }
    }
}
