import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.*;
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1550691097823471818L;

    public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random random;
    private HUD hud;
    private Spawn spawner;

    // Game Constructor
    public Game() {

        handler = new Handler();
        this.addKeyListener((KeyListener) new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "building game", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        random = new Random();

        handler.addObj(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        handler.addObj(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));

    }
        //call the thread to check if the game is started
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //check for game if its running that is equal to false
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    // running the game that is equals to true
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfSticks = 60.0;
        double ns =1000000000 / amountOfSticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }stop();
    }

    //method tick
    private void tick() {
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    // use to render the image load
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    //use to check the variable minimum and the maximum health
    public static int clamp(int var, int min, int max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    // Main Method
    public static void main(String[] args) {
        new Game();
    }
}
