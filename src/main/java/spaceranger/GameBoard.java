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
    private EnemyShipGenerator enemyGenerator;

    private java.util.List<Sprite> spriteList = Collections.synchronizedList(new ArrayList());

    GameBoard(GameGUI gui) {
        this.gui = gui;
        init();
    }

    private void init() {
        playerShip = new PlayerShip(this);
        enemyGenerator = new EnemyShipGenerator(this);

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

    public java.util.List<Sprite> getSprites() {
        return spriteList;
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
        for (Sprite sprite : spriteList) {
            g2d.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), this);
        }
        g2d.drawImage(playerShip.getImage(), playerShip.getX(), playerShip.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateAll();
    }

    private void updateAll() {
        enemyGenerator.update();

        playerShip.update();
        repaint(playerShip.getX() - MOVE_PIXELS, playerShip.getY() - MOVE_PIXELS, playerShip.getWidth() + 2*MOVE_PIXELS, playerShip.getHeight() + 2*MOVE_PIXELS);

        for (int i = 0; i < spriteList.size(); i++) {
            Sprite sprite = spriteList.get(i);
            if (sprite.isActive()) {
                sprite.update();
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            } else {
                spriteList.remove(i);
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            }
        }
    }  
}
