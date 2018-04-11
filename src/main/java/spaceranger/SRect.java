/*
    SRect.java

    A geometric rectangle on the GameBoard
 */

package spaceranger;

public class SRect {

    private SPoint topLeft;
    private SPoint bottomRight;

    private final int width;
    private final int height;

    SRect(double x0, double y0, double x1, double y1) {
        topLeft = new SPoint(x0, y0);
        bottomRight = new SPoint(x1, y1);
        width = (int)(x1 - x0);
        height = (int)(y1 - y0);
    }

    // SRect(SPoint topLeft, SPoint bottomRight) {
    //     this.topLeft = new SPoint(topLeft);
    //     this.bottomRight = new SPoint(bottomRight);
    // }

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
        bottomRight.translate(dx, dy);
    } 
}
