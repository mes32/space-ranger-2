/*
    HitPoints.java

    This represents the hit points for a game entity.
 */

package spaceranger;

public class HitPoints {

    private int currentPoints;
    private int maximumPoints;

    HitPoints(int maximum) {
        currentPoints = maximum;
        maximumPoints = maximum;
    }

    public void damage(int damage) {
        currentPoints -= damage;
        if (currentPoints < 0) {
            currentPoints = 0;
        }
    }

    public boolean isDestroyed() {
        if (currentPoints <= 0) {
            return true;
        }
        return false;
    }
}
