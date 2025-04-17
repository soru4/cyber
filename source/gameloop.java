
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.util.*;

public class gameloop { // game loop is going to update the frame with all the new information at a given FPS. 

    public static gameloop __inst__;
    public float FPSTarget;
    public ArrayList<MonoBehaviour> allGameObjectsInScene;

    public window win;

    public gameloop(window gameWindow, float FPSTarget) {
        __inst__ = this;
        this.win = gameWindow;
        this.FPSTarget = FPSTarget;

        allGameObjectsInScene = new ArrayList<MonoBehaviour>();
        scene s = new scene();
        for (MonoBehaviour x : allGameObjectsInScene) {
            x.Start();
        }
        fakeLoop();
        win.frame.repaint((int) (1000 / this.FPSTarget));
        win.frame.revalidate();
    }

    public void fakeLoop() {

        try {
            Thread.sleep((int) (1000 / this.FPSTarget));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Loop through everything. 
        for (MonoBehaviour x : allGameObjectsInScene) {
            x.Update();
        }
       // System.out.println(MouseInfo.getPointerInfo().getLocation() );
        
        
      
        //fakeLoop();
    }

    public MonoBehaviour Instantiate(MonoBehaviour object, float delay) {
        allGameObjectsInScene.add(object);
        object.Start();
        return object;
    }

}
