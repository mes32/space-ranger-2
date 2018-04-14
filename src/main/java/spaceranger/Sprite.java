/*
    Sprite.java

    A game element that can be painted onto GameGUI
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

abstract public class Sprite {

    protected Image image;
    protected HitBox hitbox;
    protected SRect repaint;

    protected GameBoard board;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected boolean isHit;

    Sprite(GameBoard board, String path) {
        this.board = board;
        image = loadImage(path);
        init();
    }

    public void setInitialPosition(int x, int y) {
        // TODO: This function should not be needed
        this.x = x;
        this.y = y;

        hitbox = new HitBox(new SRect(x, y, x + width, y + height));
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

        hitbox.translate(dx, dy);
        int x0, y0, x1, y1;
        if (dx < 0) {
            x0 = x;
            x1 = x - dx + width;
        } else {
            x0 = x - dx;
            x1 = x + width;
        }
        if (dy < 0) {
            y0 = y;
            y1 = y - dy + height;
        } else {
            y0 = y - dy;
            y1 = y + height;
        }
        repaint = new SRect(x0, y0, x1, y1);
    }

    public boolean isActive() {
        return true;
    }

    public void hit() {
        isHit = true;
    }

    public boolean collision(Sprite other) {
        return hitbox.collision(other.hitbox);
    }
    
    public SRect repaintRect() {
        return repaint;
    }

    private void init() {
        width = image.getWidth(null);
        height =  image.getHeight(null);

        int guiWidth = board.getWidth();
        int guiHeight = board.getHeight();

        x = (int)((guiWidth - width) / 2);
        y = (int)(guiHeight - 1.5 * height);
        dx = 0;
        dy = 0;
        isHit = false;

        hitbox = new HitBox(new SRect(x, y, x + width, y + height));
        repaint = new SRect(0, 0, 0, 0);
    }
    
    protected Image loadImage(String path) {
        ImageIcon ii = new ImageIcon(getClass().getResource(path));
        Image image = ii.getImage();
        return image;
    }
}
