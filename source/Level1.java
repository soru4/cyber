import java.util.ArrayList;

import javax.swing.JPanel;

public class Level1  {

    public ArrayList<ComputerComponent> components;

    public Level1(JPanel x, Window w) {
        this.components = new ArrayList<ComputerComponent>();
    }


    public void setLevelActive() {
        // Set the level active and perform any necessary initialization here
        System.out.println("Level 1 is now active.");
    }

    public ArrayList<ComputerComponent> getComponents() {
        return components;
    }

}
