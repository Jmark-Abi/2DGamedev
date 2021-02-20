import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//Class for getting the user key input
public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Get the key inputted by the user
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);

            if (temp.getId() == ID.Player) {
                //key events for player 1

                if(key == KeyEvent.VK_W) temp.setvelY(-5);
                if(key == KeyEvent.VK_S) temp.setvelY(5);
                if(key == KeyEvent.VK_D) temp.setvelX(5);
                if(key == KeyEvent.VK_A) temp.setvelX(-5);

            }
            //use escape key to exit
            if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        }

    }

    @Override
    //if the key is unpressed the key will release
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);

            if (temp.getId() == ID.Player) {
                //key events for player 1

                if(key == KeyEvent.VK_W) temp.setvelY(0);
                if(key == KeyEvent.VK_S) temp.setvelY(0);
                if(key == KeyEvent.VK_D) temp.setvelX(0);
                if(key == KeyEvent.VK_A) temp.setvelX(0);

            }

        }

    }
}
