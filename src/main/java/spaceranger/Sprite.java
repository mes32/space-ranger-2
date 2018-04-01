/*
    Sprite.java

    A game element that can be painted onto GameGUI
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

abstract public class Sprite {

    protected Image image;

    private GameGUI gui;
    private int width;
    private int height;
    private int x;
    private int y;
    private int dx;
    private int dy;

    Sprite(GameGUI gui, String path) {
        this.gui = gui;
        image = loadImage(path);
        init();
    }

    public void setInitialPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Image getImage() {
        return image;
    }

    public void setMoveX(int delta) {
        dx = delta;
    }

    public void setMoveY(int delta) {
        dy = delta;
    }

    public void update() {
        x += dx;
        y += dy;
    }
    
    private void init() {
        width = image.getWidth(null);
        height =  image.getHeight(null);

        int guiWidth = gui.getWidth();
        int guiHeight = gui.getHeight();

        x = (int)((guiWidth - width) / 2);
        y = (int)(guiHeight - 1.5 * height);
        dx = 0;
        dy = 0;
    }
    
    protected Image loadImage(String path) {
        ImageIcon ii = new ImageIcon(getClass().getResource(path));
        Image image = ii.getImage();
        return image;
    }
}
