/*
    SCircle.java

    A geometric circle on the GameBoard
 */

package spaceranger;

public class SCircle {

    private SPoint center;
    private double radius;

    SCircle(double x, double y, double r) {
        center = new SPoint(x, y);
        radius = r;
    }

    public void translate(double dx, double dy) {
        center.translate(dx, dy);
    } 
}
