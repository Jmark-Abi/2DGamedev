import java.awt.*;

public class BasicEnemy extends GameObject{

    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 5;
        velY = 5;
    }

    // check the enemy if it collides with the player
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

      if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
      if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

      handler.addObj(new Trail( x, y, ID.Trail, Color.RED, 16, 16, 0.03F, handler));
    }

    // use to create or render the image
    public void render(Graphics g) {

    g.setColor(Color.RED);
    g.fillRect(x, y, 16, 16);
    }
}
