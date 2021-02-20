import java.awt.*;
import java.util.Random;
//class for the player
public class Player extends GameObject {

    Random r = new Random();
    Handler handler;
    //constructor for player class
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
       // velY = 5;
       // velX = 5;
    }
    // check the player if it collides with the enemy
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

       // if (x == Game.HEIGHT || x == Game.WIDTH) velX *= -1;
        //if (y == Game.HEIGHT || y == Game.WIDTH) velX *= -1;
        x = Game.clamp(x, 0 , Game.WIDTH - 45);
        y = Game.clamp(y, 0 , Game.HEIGHT - 65);

        collision();
    }

    // check the collision of the player and the BasicEnemy
    public void collision(){
        for (int i = 0; i < handler.object.size(); i++){

            GameObject tempGameObject = handler.object.get(i);// initialize as the basicEnemy
            if (tempGameObject.getId() == ID.BasicEnemy){
                if(getBounds().intersects(tempGameObject.getBounds())){
                    /*
                    collision code to check the bounds of the player and enemy are colliding
                    when the player collide with the basicEnemy the health will reduce by 2
                     */
                    HUD.HEALTH -= 2;
                }

            }

        }
    }

    @Override
    // graphical image for the player
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
