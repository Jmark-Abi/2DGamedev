import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for(int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.tick();
        }
    }
    public void render(Graphics g) {
        for(int i=0; i < object.size(); i++) {
            GameObject temp = object.get(i);

            temp.render(g);
        }
    }

    public void addObj(GameObject gameObject) {
        this.object.add(gameObject);
    }
    public void removeObj(GameObject gameObject) {
        this.object.remove(gameObject);
    }
}
