/*
    GameBoard.java

    The main program panel inside the GameGUI window
 */

package spaceranger.gui;

import spaceranger.sprite.*;
import spaceranger.sprite.hitbox.*;

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

    private SpriteLayer<PlayerShip> playerLayer = new SpriteLayer<PlayerShip>();
    private SpriteLayer<PlayerProjectile> playerProjectiles = new SpriteLayer<PlayerProjectile>();
    private SpriteLayer<PlayerSpark> playerSparks = new SpriteLayer<PlayerSpark>();
    private SpriteLayer<EnemyShip> enemies = new SpriteLayer<EnemyShip>();
    private SpriteLayer<EnemyProjectile> enemyProjectiles = new SpriteLayer<EnemyProjectile>();
    private SpriteLayer<EnemySpark> enemySparks = new SpriteLayer<EnemySpark>();
    private SpriteLayer<EnemyExplosion> enemyExplosions = new SpriteLayer<EnemyExplosion>();

    private SpriteLayer[] layers = {
        playerLayer,
        playerProjectiles,
        playerSparks,
        enemies,
        enemyProjectiles,
        enemySparks,
        enemyExplosions
    };

    GameBoard(GameGUI gui) {
        this.gui = gui;

        playerShip = new PlayerShip(this);
        playerLayer.insert(playerShip);
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

    public void insert(PlayerSpark spark) {
        playerSparks.insert(spark);
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

    public void insert(EnemyExplosion explosion) {
        enemyExplosions.insert(explosion);
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
        for (SpriteLayer layer : layers) {
            for (int i = 0; i < layer.getList().size(); i++) {
                Sprite sprite = layer.get(i);
                drawImage(sprite, g2d);
            }
        }
    }

    private void drawImage(Sprite sprite, Graphics2D graphics) {
        graphics.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), this);
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
        if (!playerShip.isActive()) {
            ingame = false;
        }
        enemyGenerator.update();

        for (SpriteLayer layer : layers) {
            for (int i = 0; i < layer.getList().size(); i++) {
                Sprite sprite = layer.get(i);
                if (sprite.isActive()) {
                    sprite.update(updateTime);
                } else {
                    layer.remove(sprite);
                }
                repaint(sprite.repaintRect());
            }
        }
    }

    public void repaint(SRect rect) {
        repaint(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
}
