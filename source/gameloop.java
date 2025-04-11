
import javax.swing.JLabel;

import java.util.*; 


public class gameloop { // game loop is going to update the frame with all the new information at a given FPS. 

    public float FPSTarget;
    public  ArrayList<MonoBehaviour> allGameObjectsInScene; 


    public window w; 
    public gameloop(window gameWindow, float FPSTarget) {
        this.w = gameWindow; 
        this.FPSTarget = FPSTarget;
        fakeLoop();

    }

    public void fakeLoop() {

        try {
            Thread.sleep((int) (1000 / this.FPSTarget));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Loop through everything. 

        this.w.label.setText(this.w.x+ "");
      
        this.w.updateFrame(this.w.frame);
        this.w.x+=1; 
        fakeLoop();
    }

    public  MonoBehaviour Instantiate(MonoBehaviour object, float delay){
        allGameObjectsInScene.add(object);

        return object; 
    }

}
