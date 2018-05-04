/*
    HitBox.java

    The hitbox of a Sprite on the GameBoard
 */

package spaceranger;

public class HitBox {

    private SRect container;
    private Collider[] components;

    public HitBox(SRect container) {
        this.container = container;
        components = new Collider[0];
    }

    public HitBox(SRect container, Collider[] components) {
        // TODO: Possibly this should just take the components and determine the container from that
        this.container = container;
        this.components = components;
    }

    public void translate(double dx, double dy) {
        container.translate(dx, dy);
        for (Collider c : components) {
            c.translate(dx, dy);
        }
    }

    public SRect getContainer() {
        return container;
    }

    public Collider[] getComponents() {
        return components;
    }

    public boolean collision(HitBox other) {
        if (this.components.length == 0) {
            return container.collision(other);
        }

        if (this.container.collision(other.container)) {

            for (Collider c : this.components) {
                if (c.collision(other)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
