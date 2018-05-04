/*
    BigRoundMine.java

    This is an EnemyShip

    Exhibits the following pattern. Cruises down the screen from top to bottom.

 */

package spaceranger.sprite;

import spaceranger.gui.*;
import spaceranger.sprite.hitbox.*;

public class BigRoundMine extends EnemyShip {

    private static final String IMAGE_PATH = "/images/BigRoundMine.png";

    private final int boardHeight;

    public BigRoundMine(GameBoard board, int x, int y) {
        super(board, IMAGE_PATH);
        boardHeight = board.getHeight();
        dy = 1;

        hitPoints = new HitPoints(200);
        collideDamage = 1000;

        this.x = x;
        this.y = y;

        SRect container = new SRect(x, y, x + width, y + height);
        Collider[] components = new Collider[1];
        components[0] = new SCircle(x + width / 2, y + height / 2, 60);

        hitbox = new HitBox(container, components);
    }
}
