/*
    PlayerShip.java

    This Sprite representing the player character
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerShip extends Sprite {

    private static final String IMAGE_PATH = "/images/PlayersShip.png";

    PlayerShip(GameBoard board) {
        super(board, IMAGE_PATH);
    }

    public void fireMissile() {
        PlayerMissile missileLeft = new PlayerMissile(board);
        PlayerMissile missileRight = new PlayerMissile(board);
        missileLeft.setInitialPosition(x + 10, y + 15);
        missileRight.setInitialPosition(x + width - 5 - 10, y + 15);
        board.addSprite(missileLeft);
        board.addSprite(missileRight);
    }
}
