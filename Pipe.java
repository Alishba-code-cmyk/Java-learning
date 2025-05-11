import java.awt.*;
import java.util.Random;

public class Pipe {
    private int x, width = 60, gap = 150;
    private int topHeight, bottomY;
    private int speed = 4;
    private boolean passed = false;

    public Pipe(int startX) {
        this.x = startX;
        Random rand = new Random();
        this.topHeight = rand.nextInt(200) + 100;
        this.bottomY = topHeight + gap;
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, 0, width, topHeight);
        g.fillRect(x, bottomY, width, 600 - bottomY);
    }

    public boolean collidesWith(Fish fish) {
        Rectangle fishBounds = fish.getBounds();
        Rectangle top = new Rectangle(x, 0, width, topHeight);
        Rectangle bottom = new Rectangle(x, bottomY, width, 600 - bottomY);
        return fishBounds.intersects(top) || fishBounds.intersects(bottom);
    }

    public int getX() { return x; }
    public int getWidth() { return width; }
    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }
}
