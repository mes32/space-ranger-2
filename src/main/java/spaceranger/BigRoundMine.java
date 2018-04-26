/*
    BigRoundMine.java

    This is an EnemyShip

    Exhibits the following pattern. Cruises down the screen from top to bottom.

 */

package spaceranger;

public class BigRoundMine extends EnemyShip {

    private static final String IMAGE_PATH = "/images/BigRoundMine.png";

    private final int boardHeight;

    BigRoundMine(GameBoard board, int x, int y) {
        super(board, IMAGE_PATH);
        boardHeight = board.getHeight();
        dy = 1;

        hitPoints = new HitPoints(200);
        collideDamage = 1000;

        this.x = x;
        this.y = y;

        SRect container = new SRect(x, y, x + width, y + height);
        Collider[] components = new Collider[2];
        components[0] = new SRect(x, y, x + (int)(width / 2), y + (int)(height / 2));
        components[1] = new SRect(x + (int)(width / 2), y + (int)(height / 2), x + width, y + height);

        hitbox = new HitBox(container, components);
    }
}
