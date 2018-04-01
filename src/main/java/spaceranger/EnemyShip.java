/*
    EnemyShip.java

    This Sprite represents enemy characters
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class EnemyShip extends Sprite {

    private static final String IMAGE_PATH = "/images/EnemyShip.png";

    private final int boardHeight;

    EnemyShip(GameBoard board) {
        super(board, IMAGE_PATH);
        boardHeight = board.getHeight();
        dy = 1;
        isHit = false;
    }

    public boolean isEnemy() {
        return true;
    }

    public boolean isActive() {
        if (y > boardHeight || isHit) {
            return false;
        } else {
            return true;
        }
    }
}
