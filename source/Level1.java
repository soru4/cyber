import java.util.ArrayList;

import javax.swing.JPanel;

public class Level1 extends LevelTemplate {

    public ArrayList<ComputerComponent> components;

    public Level1(JPanel x, Window w) {
        super(x, w);
        this.components = new ArrayList<ComputerComponent>();
    }

    @Override
    public void setLevelActive() {
        // Set the level active and perform any necessary initialization here
        System.out.println("Level 1 is now active.");
    }

    public ArrayList<ComputerComponent> getComponents() {
        return components;
    }

}
