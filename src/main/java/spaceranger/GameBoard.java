/*
    GameBoard.java

    The main program panel associated with GameGUI
 */

package spaceranger;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GameBoard extends JPanel implements Runnable {

    private static final int DELAY = 10;
    private static final int MOVE_PIXELS = GameplayKeyAdapter.getMovePixels();

    private Thread animator;
    private GameGUI gui;

    private EnemyShipGenerator enemyGenerator;
    private int score;
    private boolean ingame;

    private PlayerShip playerShip;
    private SpriteLayer<PlayerProjectile> playerProjectiles = new SpriteLayer<PlayerProjectile>();
    private SpriteLayer<EnemyShip> enemies = new SpriteLayer<EnemyShip>(); 
    private SpriteLayer<EnemyProjectile> enemyProjectiles = new SpriteLayer<EnemyProjectile>(); 

    GameBoard(GameGUI gui) {
        this.gui = gui;

        playerShip = new PlayerShip(this);
        enemyGenerator = new EnemyShipGenerator(this);
        score = 0;
        ingame = true;

        addKeyListener(new GameplayKeyAdapter(playerShip));
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    public int getWidth() {
        return gui.getWidth();
    }

    public int getHeight() {
        return gui.getHeight();
    }

    public void incrementScore() {
        if (ingame) {
            score++;
        }
    }

    public void insert(PlayerProjectile projectile) {
        playerProjectiles.insert(projectile);
    }

    public void insert(EnemyShip enemy) {
        enemies.insert(enemy);
    }

    public void insert(EnemyProjectile projectile) {
        enemyProjectiles.insert(projectile);
    }

    public SpriteLayer<PlayerProjectile> getPlayerProjectiles() {
        return playerProjectiles;
    }

    public SpriteLayer<EnemyShip> getEnemies() {
        return enemies;
    }

    public SpriteLayer<EnemyProjectile> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        long diffTime;
        long sleepTime;

        while (true) {
            updateAll();
            repaint();

            diffTime = System.currentTimeMillis() - beforeTime;
            sleepTime = DELAY - diffTime;
            if (sleepTime < 0) {
                sleepTime = 2;
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                String title = "InterruptedException";
                String message = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (ingame) {
            drawSprites(g);
        } else {
            drawGameover(g);
        }
        Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        toolkit.sync();
    }

    private void drawSprites(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerShip.getImage(), playerShip.getX(), playerShip.getY(), this);
        for (int i = 0; i < playerProjectiles.getList().size(); i++) {
            PlayerProjectile p = playerProjectiles.getList().get(i);
            g2d.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }
        for (int i = 0; i < enemies.getList().size(); i++) {
            EnemyShip e = enemies.getList().get(i);
            g2d.drawImage(e.getImage(), e.getX(), e.getY(), this);
        }
        for (int i = 0; i < enemyProjectiles.getList().size(); i++) {
            // TODO: Why does iterator not work here with the multi-threading
            EnemyProjectile p = enemyProjectiles.getList().get(i);
            g2d.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }
    }

    private void drawGameover(Graphics g) {
        String message = "Game Over";
        int fontSize = 22;
        Font small = new Font("Helvetica", Font.BOLD, fontSize);
        FontMetrics fontMetrics = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (gui.getWidth() - fontMetrics.stringWidth(message)) / 2, gui.getHeight() / 2);

        String message2 = "Score: " + score;
        g.drawString(message2, (gui.getWidth() - fontMetrics.stringWidth(message2)) / 2, (gui.getHeight() / 2) + 2 * fontSize);
    }

    private void updateAll() {
        enemyGenerator.update();

        playerShip.update();
        if (playerShip.isDestroyed()) {
            ingame = false;
        }
        repaint(playerShip.getX() - MOVE_PIXELS, playerShip.getY() - MOVE_PIXELS, playerShip.getWidth() + 2*MOVE_PIXELS, playerShip.getHeight() + 2*MOVE_PIXELS);

        for (int i = 0; i < playerProjectiles.getList().size(); i++) {
            PlayerProjectile sprite = playerProjectiles.getList().get(i);
            if (sprite.isActive()) {
                sprite.update();
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            } else {
                playerProjectiles.remove(sprite);
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            }
        }

        for (int i = 0; i < enemies.getList().size(); i++) {
            EnemyShip sprite = enemies.getList().get(i);
            if (sprite.isActive()) {
                sprite.update();
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            } else {
                enemies.remove(sprite);
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            }
        }

        for (int i = 0; i < enemyProjectiles.getList().size(); i++) {
            EnemyProjectile sprite = enemyProjectiles.getList().get(i);
            if (sprite.isActive()) {
                sprite.update();
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            } else {
                enemyProjectiles.remove(sprite);
                repaint(sprite.getX(), sprite.getY() - 5, sprite.getWidth(), sprite.getHeight() + 10);
            }
        }
    }

    // @Override
    // public void repaint() {
    //     super.repaint();
    // }

    // @Override
    // public void repaint(int x, int y, int width, int height) {
    //     super.repaint(x, y, width, height);
    // }
}
