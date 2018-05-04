/*
    SLineSegment.java

    A geometric line segment on the GameBoard. Note these line segments are restricted to being
    perpendicular to the GameBoard axes.
 */

package spaceranger.sprite.hitbox;

import spaceranger.exceptions.*;

public class SLineSegment {

    private SPoint p0;
    private SPoint p1;

    private double A;
    private double B;
    private double C;

    SLineSegment(SPoint p0, SPoint p1) throws InvalidLineSegmentException {
        this.p0 = p0;
        this.p1 = p1;

        int x0 = p0.getX();
        int y0 = p0.getY();
        int x1 = p1.getX();
        int y1 = p1.getY();

        if (x0 == x1 && y0 != y1) {
            A = 1.0;
            B = 0.0;
            C = -1.0 * x0;
        } else if (y0 == y1 && x0 != x1) {
            A = 0.0;
            B = 1.0;
            C = -1.0 * y0;
        } else {
            throw new InvalidLineSegmentException();
        }
    }

    public boolean within(SCircle circle) {
        SPoint center = circle.getCenter();
        double radius = circle.getRadius();
        if (this.distance(center) <= radius) {
            if (p0.distance(center) <= radius || p1.distance(center) <= radius) {
                return true;
            }

            // TODO: Should be able to use this formula instead
            // SPoint proximal = this.closestPoint(center);
            // if (pointOnSegment(proximal)) {
            //     return true;
            // }
        }
        return false;
    }

    private double distance(SPoint p) {
        int x = p.getX();
        int y = p.getY();
        return Math.abs(A*x + B*y + C) / Math.sqrt(A*A + B*B);
    }

    private SPoint closestPoint(SPoint p) {
        int x_p = p.getX();
        int y_p = p.getY();
        double denominator = Math.pow(A, 2) + Math.pow(B, 2);

        double x = ((B * (B*x_p - A*y_p)) - (A*C)) / denominator;
        double y = ((A * (A*y_p - B*x_p)) - (B*C)) / denominator;

        return new SPoint(x, y);
    }

    private boolean pointOnSegment(SPoint point) {
        double epsilon = 2.0;
        double total = p0.distance(p1);
        double d0 = point.distance(p0);
        double d1 = point.distance(p1);

        if (Math.abs(total - (d0 + d1)) < epsilon) {
            return true;
        }
        return false;
    }
}
