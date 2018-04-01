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
        System.out.println("PlayerShip fireMissile()");
    }
}
