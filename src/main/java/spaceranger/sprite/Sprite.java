/*
    Sprite.java

    A game element that can be painted onto GameGUI
 */

package spaceranger.sprite;

import spaceranger.gui.*;
import spaceranger.sprite.hitbox.*;

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

    protected int collideDamage = 0;
    protected HitPoints hitPoints;

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

    public void setCenter(int x, int y) {
        int xOffset = x - (int)(width / 2);
        int yOffset = y - (int)(height / 2);
        setInitialPosition(xOffset, yOffset);
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

    public int getMoveX() {
        return dx;
    }

    public void setMoveY(int delta) {
        dy = delta;
    }

    public int getMoveY() {
        return dy;
    }

    public void update(int dx, int dy) {
        // TODO: This function should be renamed to translate(int dx, int dy)
        this.dx = dx;
        this.dy = dy;

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

    public void update(long time) {
        update(this.dx, this.dy);
    }

    public boolean isActive() {
        return !hitPoints.isDestroyed();
    }

    public boolean collision(Sprite other) {
        return hitbox.collision(other.hitbox);
    }

    public void damage(Sprite other) {
        this.hitPoints.damage(other.collideDamage);
        other.hitPoints.damage(this.collideDamage);
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

        hitbox = new HitBox(new SRect(x, y, x + width, y + height));
        repaint = new SRect(0, 0, 0, 0);
    }
    
    protected Image loadImage(String path) {
        ImageIcon ii = new ImageIcon(getClass().getResource(path));
        Image image = ii.getImage();
        return image;
    }
}
