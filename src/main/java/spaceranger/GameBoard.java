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
    private SpriteLayer<EnemySpark> enemySparks = new SpriteLayer<EnemySpark>();

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

    public void insert(EnemySpark spark) {
        enemySparks.insert(spark);
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
        long updateTime;
        long diffTime;
        long sleepTime;

        while (true) {
            updateTime = System.currentTimeMillis();
            updateAll(updateTime);
            cullAll();

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
            // TODO: Could overload the drawImage interface to simplify the following function call
            g2d.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }
        for (int i = 0; i < enemySparks.getList().size(); i++) {
            EnemySpark s = enemySparks.getList().get(i);
            g2d.drawImage(s.getImage(), s.getX(), s.getY(), this);
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

    private void updateAll(long updateTime) {
        enemyGenerator.update();

        playerShip.update(updateTime);
        if (playerShip.isActive()) {
            ingame = false;
        }
        repaint(playerShip.repaintRect());

        for (int i = 0; i < playerProjectiles.getList().size(); i++) {
            PlayerProjectile sprite = playerProjectiles.getList().get(i);
            if (sprite.isActive()) {
                sprite.update();
                repaint(sprite.repaintRect());
            }
        }

        for (int i = 0; i < enemies.getList().size(); i++) {
            EnemyShip sprite = enemies.getList().get(i);
            if (sprite.isActive()) {
                sprite.update(updateTime);
                repaint(sprite.repaintRect());
            }
        }

        for (int i = 0; i < enemyProjectiles.getList().size(); i++) {
            EnemyProjectile sprite = enemyProjectiles.getList().get(i);
            if (sprite.isActive()) {
                sprite.update();
                repaint(sprite.repaintRect());
            }
        }

        for (int i = 0; i < enemySparks.getList().size(); i++) {
            EnemySpark sprite = enemySparks.getList().get(i);
            if (sprite.isActive()) {
                sprite.update(updateTime);
                repaint(sprite.repaintRect());
            }
        }
    }

    private void cullAll() {
        for (int i = 0; i < playerProjectiles.getList().size(); i++) {
            PlayerProjectile sprite = playerProjectiles.getList().get(i);
            if (!sprite.isActive()) {
                playerProjectiles.remove(sprite);
                repaint(sprite.repaintRect());
            }
        }

        for (int i = 0; i < enemies.getList().size(); i++) {
            EnemyShip sprite = enemies.getList().get(i);
            if (!sprite.isActive()) {
                enemies.remove(sprite);
                repaint(sprite.repaintRect());
            }
        }

        for (int i = 0; i < enemyProjectiles.getList().size(); i++) {
            EnemyProjectile sprite = enemyProjectiles.getList().get(i);
            if (!sprite.isActive()) {
                enemyProjectiles.remove(sprite);
                repaint(sprite.repaintRect());
            }
        }

        for (int i = 0; i < enemySparks.getList().size(); i++) {
            EnemySpark sprite = enemySparks.getList().get(i);
            if (!sprite.isActive()) {
                enemySparks.remove(sprite);
                repaint(sprite.repaintRect());
            }
        }
    }

    // @Override
    // public void repaint() {
    //     super.repaint();
    // }

    public void repaint(SRect rect) {
        repaint(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    // @Override
    // public void repaint(int x, int y, int width, int height) {
    //     super.repaint(x, y, width, height);
    // }
}
