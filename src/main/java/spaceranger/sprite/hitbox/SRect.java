/*
    SRect.java

    A geometric rectangle on the GameBoard
 */

package spaceranger.sprite.hitbox;

import spaceranger.exceptions.*;

public class SRect implements Collider {

    private SPoint topLeft;
    private SPoint bottomRight;

    private final int width;
    private final int height;

    public SRect(double x0, double y0, double x1, double y1) {
        topLeft = new SPoint(x0, y0);
        width = (int)(x1 - x0);
        height = (int)(y1 - y0);
    }

    public int getX() {
        return topLeft.getX();
    }

    public int getY() {
        return topLeft.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void translate(double dx, double dy) {
        topLeft.translate(dx, dy);
    }

    public boolean collision(SRect rect) {
        if (
            topLeft.getX() < rect.getX() + rect.getWidth() &&
            topLeft.getX() + width > rect.getX() &&
            topLeft.getY() < rect.getY() + rect.getHeight() &&
            height + topLeft.getY() > rect.getY()
        ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean collision(SCircle circle) {
        double x0 = topLeft.getX();
        double y0 = topLeft.getY();
        double x1 = x0 + width;
        double y1 = y0 + height;

        try {
            SPoint topRight = new SPoint(x1, y0);
            SPoint bottomLeft = new SPoint(x0, y1);
            SPoint bottomRight = new SPoint(x1, y1);

            SLineSegment top = new SLineSegment(topLeft, topRight);
            SLineSegment left = new SLineSegment(topLeft, bottomLeft);
            SLineSegment right = new SLineSegment(topRight, bottomRight);
            SLineSegment bottom = new SLineSegment(bottomLeft, bottomRight);

            if (top.within(circle) || left.within(circle) || right.within(circle) || bottom.within(circle) || circle.within(this)) {
                return true;
            } else {
                return false;
            }
        } catch (InvalidLineSegmentException e) {
            e.printStackTrace();
            return false;
        }
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
}
