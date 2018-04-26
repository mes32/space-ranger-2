/*
    EnemyShipGenerator.java

    This Sprite represents enemy characters
 */

package spaceranger;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class EnemyShipGenerator {

    private static final int TIME_OUT = 160;
    private static final int[] RANDOM_LANE = {
        922, 728, 865, 741, 959, 279, 233, 210, 273, 945,
        81, 242, 235, 921, 916, 543, 597, 658, 403, 886,
        830, 686, 643, 287, 877, 905, 718, 852, 968, 18,
        789, 387, 191, 897, 333, 624, 608, 121, 265, 145,
        714, 132, 362, 861, 798, 551, 75, 184, 433, 610,
        156, 89, 464, 212, 774, 306, 834, 321, 931, 115,
        151, 963, 751, 716, 608, 134, 773, 150, 714, 332,
        805, 458, 950, 827, 911, 400, 811, 333, 932, 520,
        655, 178, 443, 120, 2, 667, 684, 335, 883, 549,
        528, 839, 886, 774, 91, 527, 797, 598, 832, 359
    };

    private GameBoard board;
    private int index;
    private int counter;

    private int mineCounter = 0;

    EnemyShipGenerator(GameBoard board) {
        this.board = board;
        init();
    }

    private void init() {
        Random random = new Random();
        index = random.nextInt(RANDOM_LANE.length);
        counter = 0;
    }

    public void update() {
        counter++;
        if (counter >= TIME_OUT) {
            counter = 0;

            mineCounter++;
            EnemyShip enemy;
            if (mineCounter % 3 == 0) {
                mineCounter = 0;
                enemy = new BigRoundMine(board);
            } else {
                enemy = new GreenCruiser(board);
            }
            enemy.setInitialPosition(getLane(), -60);
            board.insert(enemy);
        }
    }

    private int getLane() {
        index++;
        if (index >= RANDOM_LANE.length) {
            index = 0;
        }
        return RANDOM_LANE[index];
    }
}
