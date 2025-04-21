import java.awt.MouseInfo;

import javax.swing.ImageIcon;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

class Scene {

    public Scene() {
        // add the classes that extend MonoBehaviour here( just call the constructor)
        GameLoop.__inst__.Instantiate(new TextCounter(), 0);
        GameLoop.__inst__.Instantiate(
                new MovableComponent(new ImageIcon("assets/75519.png"), MouseInfo.getPointerInfo().getLocation()), 0);

    }

}