import java.awt.*;

public class Fish {
    private int x, y;
    private int velocity = 0;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -12;
    private final int SIZE = 30;

    public Fish(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        velocity += GRAVITY;
        y += velocity;
    }

    public void jump() {
        velocity = JUMP_STRENGTH;
    }

    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
