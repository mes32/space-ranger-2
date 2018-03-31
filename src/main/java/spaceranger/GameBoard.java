/*
    GameBoard.java

    The main program panel associated with GameGUI
 */

package spaceranger;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoard extends JPanel implements ActionListener {

    private static final int DELAY = 10;
    private static final int MOVE_PIXELS = GameplayKeyAdapter.getMovePixels();

    private GameGUI gui;
    private Timer timer;
    private PlayerShip playerShip;

    GameBoard(GameGUI gui) {
        this.gui = gui;
        init();
    }
    
    private void init() {
        playerShip = new PlayerShip(gui);
        addKeyListener(new GameplayKeyAdapter(playerShip));
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        timer = new Timer(DELAY, this);
        timer.start();
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
        step();
    }
    
    private void step() {
        playerShip.move();
        repaint(playerShip.getX() - MOVE_PIXELS, playerShip.getY() - MOVE_PIXELS, playerShip.getWidth() + 2*MOVE_PIXELS, playerShip.getHeight() + 2*MOVE_PIXELS);     
    }  
}
