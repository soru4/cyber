
package source;


import java.util.ArrayList;


class Scene {
    public static ArrayList<Level> allLevelInstances;
    
    /**
     * The scene constuctor creates level 0 and it creates a static array that will store all instances of levels created. 
     */
    public Scene() {
        allLevelInstances = new ArrayList<Level>(); 
        // Scene should hold all instances of each level.
        // add the classes that extend MonoBehaviour here( just call the constructor)
        // GameLoop.__inst__.Instantiate(new TextCounter(), 0);
        // GameLoop.__inst__.Instantiate(
        // new MovableComponent(new ImageIcon("assets/75519.png"),
        // MouseInfo.getPointerInfo().getLocation()), 0);
        // new Level4();
        // new Level();
        allLevelInstances.add(new Level0());
    }
}
