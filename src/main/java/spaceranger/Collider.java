/*
    Collider.java

    Interface for game objects that require hit detection
 */

package spaceranger;

public interface Collider {

    public void translate(double dx, double dy);

    public boolean collision(SRect rect);

    public boolean collision(SCircle circle);

    public boolean collision(HitBox hitbox);
}