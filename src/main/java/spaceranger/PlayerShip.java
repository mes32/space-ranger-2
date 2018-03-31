/*
    PlayerShip.java

    The main program panel associated with GameGUI
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class PlayerShip {

    private static final String SPRITE_PATH = "/images/PlayersShip.png";

    private Image playerSprite;
    private int width;
    private int height;
    private int x;
    private int y;

    PlayerShip() {
        init();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return playerSprite;
    }
    
    private void init() {
        loadImage();
        width = playerSprite.getWidth(null);
        height =  playerSprite.getHeight(null);
        // setPreferredSize(new Dimension(width, height));        
    }
    
    private void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource(SPRITE_PATH));
        playerSprite = ii.getImage();
    }
}
