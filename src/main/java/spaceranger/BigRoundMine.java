/*
    BigRoundMine.java

    This is an EnemyShip

    Exhibits the following pattern. Cruises down the screen from top to bottom.

 */

package spaceranger;

public class BigRoundMine extends EnemyShip {

    private static final String IMAGE_PATH = "/images/BigRoundMine.png";

    private final int boardHeight;

    BigRoundMine(GameBoard board) {
        super(board, IMAGE_PATH);
        boardHeight = board.getHeight();
        dy = 1;

        hitPoints = new HitPoints(200);
        collideDamage = 1000;
    }
}
