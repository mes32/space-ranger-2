/*
    GameBoard.java

    The main program panel associated with GameGUI
 */

package spaceranger;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameBoard extends JPanel implements ActionListener {

    private static final int DELAY = 10;
    private static final int MOVE_PIXELS = GameplayKeyAdapter.getMovePixels();

    private GameGUI gui;
    private javax.swing.Timer timer;
    private PlayerShip playerShip;

    private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();

    GameBoard(GameGUI gui) {
        this.gui = gui;
        init();
    }
    
    private void init() {
        playerShip = new PlayerShip(this);
        addKeyListener(new GameplayKeyAdapter(playerShip));
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        timer = new javax.swing.Timer(DELAY, this);
        timer.start();
    }

    public int getWidth() {
        return gui.getWidth();
    }

    public int getHeight() {
        return gui.getHeight();
    }

    public void addSprite(Sprite sprite) {
        spriteList.add(sprite);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSprites(g);
        Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        toolkit.sync();
    }

    private void drawSprites(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerShip.getImage(), playerShip.getX(), playerShip.getY(), this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        updateAll();
    }
    
    private void updateAll() {
        playerShip.update();
        for (Sprite sprite : spriteList) {
            sprite.update();
        }

        repaint(playerShip.getX() - MOVE_PIXELS, playerShip.getY() - MOVE_PIXELS, playerShip.getWidth() + 2*MOVE_PIXELS, playerShip.getHeight() + 2*MOVE_PIXELS);
        for (Sprite sprite : spriteList) {
            repaint(sprite.getX() - MOVE_PIXELS, sprite.getY() - MOVE_PIXELS, sprite.getWidth() + 2*MOVE_PIXELS, sprite.getHeight() + 2*MOVE_PIXELS);
        }
    }  
}
