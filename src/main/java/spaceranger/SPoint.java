/*
    SPoint.java

    A geometric point on the GameBoard
 */

package spaceranger;

public class SPoint {

    private double x;
    private double y;

    SPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    SPoint(SPoint p) {
        this.x = p.x;
        this.y = p.y;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    } 
}
