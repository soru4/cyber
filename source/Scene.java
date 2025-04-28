
class Scene {

    public Scene() {
        // add the classes that extend MonoBehaviour here( just call the constructor)
        GameLoop.__inst__.Instantiate(new TextCounter(), 0);
      //  GameLoop.__inst__.Instantiate(
              //  new MovableComponent(new ImageIcon("assets/75519.png"), MouseInfo.getPointerInfo().getLocation()), 0);
         new Level4();
    }
    public void runLevel2(){
        //Level2 mainGameWindow = new Level2();
        // TODO: Modify Gameloop to work with any level, possibly using levelTemplate?
        //Gameloop gameRunner = new Gameloop(mainGameWindow);
    }
}