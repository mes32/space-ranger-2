/*
    GameBoard.java

    The main program panel associated with GameGUI
 */

package spaceranger;

import java.awt.*;
import javax.swing.*;

public class GameBoard extends JPanel {

    private static final String SPRITE_PATH = "src/main/resources/images/PlayersShip.png";
    private Image playerSprite;

    public GameBoard() {
        init();
    }
    
    private void init() {
        loadImage();
        int width = playerSprite.getWidth(this);
        int height =  playerSprite.getHeight(this);
        setPreferredSize(new Dimension(width, height));        
    }
    
    private void loadImage() {
        ImageIcon icon = new ImageIcon(SPRITE_PATH);
        playerSprite = icon.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(playerSprite, 0, 0, null);
    }
    
}
