/*
    HitBox.java

    The hitbox of a Sprite on the GameBoard
 */

package spaceranger;

public class HitBox {

    private SRect container;
    // private Collider[] components;

    HitBox(SRect container) {
        this.container = container;
    }

    public void translate(double dx, double dy) {
        container.translate(dx, dy);
    }
}
