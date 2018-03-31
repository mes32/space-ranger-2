/*
    GameBoard.java

    Launcher class for the game "Space Ranger II"
 */

package spaceranger;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GameBoard extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDonut(g);
    }

    private void drawDonut(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(hints);

        Dimension dim = getSize();
        double width = dim.getWidth();
        double height = dim.getHeight();

        Ellipse2D ellipse = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform transform = AffineTransform.getTranslateInstance(width / 2, height / 2);
            transform.rotate(Math.toRadians(deg));
            g2d.draw(transform.createTransformedShape(ellipse));
        }
    }
    
}
