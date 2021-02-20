import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep;
    private Random random;

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    //tick method
    public void tick(){
        scoreKeep++;
        if(scoreKeep >= 100){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
        }
    }
}
