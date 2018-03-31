/*
    PlayerShip.java

    The main program panel associated with GameGUI
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerShip {

    private static final String SPRITE_PATH = "/images/PlayersShip.png";

    private GameGUI gui;
    private Image playerSprite;
    private int width;
    private int height;
    private int x;
    private int y;
    private int dx;
    private int dy;

    PlayerShip(GameGUI gui) {
        this.gui = gui;
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
        return playerSprite;
    }

    public void setMoveX(int delta) {
        dx = delta;
    }

    public void setMoveY(int delta) {
        dy = delta;
    }

    public void move() {
        x += dx;
        y += dy;
    }
    
    private void init() {
        loadImage();
        width = playerSprite.getWidth(null);
        height =  playerSprite.getHeight(null);

        int guiWidth = gui.getWidth();
        int guiHeight = gui.getHeight();

        x = (int)((guiWidth - width) / 2);
        y = (int)(guiHeight - 1.5 * height);
        dx = 0;
        dy = 0;
    }
    
    private void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource(SPRITE_PATH));
        playerSprite = ii.getImage();
    }
}
