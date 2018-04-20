/*
    GreenCruiser.java

    This is an EnemyShip

    Exhibits the following pattern. Cruises down the screen from top to bottom.
    Fires two shots straight ahead. Fires in bursts of three and then takes a break.

 */

package spaceranger;

public class GreenCruiser extends EnemyShip {

    private static final String IMAGE_PATH = "/images/GreenCruiser.png";
    private static final long FIRING_PERIOD = 400;
    private static final int FIRING_BURST = 4;

    private final int boardHeight;
    private int firingCycle;
    private long lastFiringTime;

    GreenCruiser(GameBoard board) {
        super(board, IMAGE_PATH);
        boardHeight = board.getHeight();
        firingCycle = 0;
        lastFiringTime = 0L;
        dy = 1;
        isHit = false;
    }

    public void update(long time) {
        super.update();

        if (time - lastFiringTime > FIRING_PERIOD) {
            firingCycle++;
            lastFiringTime = time;
            if (firingCycle % FIRING_BURST != 0) {
                fireShots();
            }   
        }
    }

    public void fireShots() {
        EnemyProjectile left = new EnemyProjectile(board);
        EnemyProjectile right = new EnemyProjectile(board);
        left.setInitialPosition(x + (int)(width / 2) - 26, y + height - 18);
        right.setInitialPosition(x + (int)(width / 2) + 21, y + height - 18);
        board.insert(left);
        board.insert(right);
    }
}
