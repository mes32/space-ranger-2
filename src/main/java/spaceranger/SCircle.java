/*
    SCircle.java

    A geometric circle on the GameBoard
 */

package spaceranger;

public class SCircle implements Collider {

    private SPoint center;
    private double radius;

    SCircle(double x, double y, double r) {
        center = new SPoint(x, y);
        radius = r;
    }

    public SPoint getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public boolean collision(SRect rect) {
        return rect.collision(this);
    }

    public boolean collision(SCircle circle) {
        if (center.distance(circle.center) <= radius + circle.radius) {
            return true;
        }
        return false;
    }

    public boolean collision(HitBox hitbox) {
        SRect container = hitbox.getContainer();
        if (collision(container)) {
            Collider[] components = hitbox.getComponents();
            if (components.length == 0) {
                return true;
            }
            for (Collider c : components) {
                if (c.collision(this)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void translate(double dx, double dy) {
        center.translate(dx, dy);
    }

    public boolean within(SRect rect) {
        double cx = center.getX();
        double cy = center.getY();
        double rx_start = rect.getX();
        double ry_start = rect.getY();
        double rx_end = rx_start + rect.getWidth();
        double ry_end = ry_start + rect.getHeight(); 
        if (cx >= rx_start && cy >= ry_start && cx <= rx_end && cy <= ry_end) {
            return true;
        }
        return false;
    }
}
