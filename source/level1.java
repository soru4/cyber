import java.util.ArrayList;

import javax.swing.JPanel;

public class Level1 extends levelTemplate {

    public Level1(JPanel x, window w) {
        super(x, w);
    }

    @Override
    public void setLevelActive() {
        // Set the level active and perform any necessary initialization here
        System.out.println("Level 1 is now active.");
    }

}
