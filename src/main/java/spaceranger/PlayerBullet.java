/*
    PlayerBullet.java

    This Sprite represents bullets fired by PlayerShip
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerBullet extends PlayerProjectile {

    private static final String IMAGE_PATH = "/images/PlayerBullet.png";

    PlayerBullet(GameBoard board) {
        super(board, IMAGE_PATH);
        dy = -7;
        damage = 4;
    }
}
