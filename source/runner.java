
public class Runner {

    public static void main(String[] args) {
        //In the end product, this should use Level1
        Window mainGameWindow = new Window();

        ///All lines of code need to be above this line!!!!
        GameLoop gameRunner = new GameLoop(mainGameWindow, 12);

    }
    public void runLevel2(){
        //Level2 mainGameWindow = new Level2();
        // TODO: Modify Gameloop to work with any level, possibly using levelTemplate?
        //Gameloop gameRunner = new Gameloop(mainGameWindow);
    }
}
