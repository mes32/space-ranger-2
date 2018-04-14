/*
    SLineSegment.java

    A geometric line segment on the GameBoard. Note these line segments are restricted to being
    perpendicular to the GameBoard axes.
 */

package spaceranger;

public class SLineSegment {

    private SPoint p0;
    private SPoint p1;

    private double A;
    private double B;
    private double C;

    SLineSegment(SPoint p0, SPoint p1) {
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
            // TODO: This should throw an exception
            A = 0.0;
            B = 0.0;
            C = 0.0;
        }
    }

    public boolean within(SCircle circle) {
        SPoint c = circle.getCenter();
        double r = circle.getRadius();
        if (distance(c) <= r) {
            // TODO: Check the perpendicular point in the next if()
            // The case where the points span the cicle
            if (p0.distance(c) <= r || p1.distance(c) <= r  ) {
                return true;
            }
        }
        return false;
    }

    private double distance(SPoint p) {
        int x = p.getX();
        int y = p.getY();
        return Math.abs(A*x + B*y + C) / Math.sqrt(A*A + B*B);
    }
}
