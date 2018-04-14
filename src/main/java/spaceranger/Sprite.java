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
    }
    
    protected Image loadImage(String path) {
        ImageIcon ii = new ImageIcon(getClass().getResource(path));
        Image image = ii.getImage();
        return image;
    }
}
