import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Fish fish;
    private ArrayList<Pipe> pipes;
    private int score = 0;
    private boolean gameOver = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(400, 600));
        this.setBackground(Color.cyan);
        this.setFocusable(true);
        this.addKeyListener(this);

        fish = new Fish(100, 300);
        pipes = new ArrayList<>();

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fish.draw(g);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 30);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over!", 100, 250);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press R to Restart", 130, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            fish.update();

            // Pipe movement and collision
            Iterator<Pipe> iterator = pipes.iterator();
            while (iterator.hasNext()) {
                Pipe pipe = iterator.next();
                pipe.update();

                if (pipe.getX() + pipe.getWidth() < fish.getX() && !pipe.isPassed()) {
                    score++;
                    pipe.setPassed(true);
                }

                if (pipe.collidesWith(fish)) {
                    gameOver = true;
                }

                if (pipe.getX() + pipe.getWidth() < 0) {
                    iterator.remove();
                }
            }

            // Add new pipes
            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < 200) {
                pipes.add(new Pipe(400));
            }

            // Check ground
            if (fish.getY() > 600 || fish.getY() < 0) {
                gameOver = true;
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            fish.jump();
        }

        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            fish = new Fish(100, 300);
            pipes.clear();
            score = 0;
            gameOver = false;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
