/*
    PlayerShip.java

    This Sprite representing the player character
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerShip extends Sprite {

    private static final String IMAGE_PATH = "/images/PlayersShip.png";

    PlayerShip(GameGUI gui) {
        super(gui, IMAGE_PATH);
    }
}
