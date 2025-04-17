
public class runner {

    public static void main(String[] args) {
        window mainGameWindow = new window();

        ///All lines of code need to be above this line!!!!
        gameloop gameRunner = new gameloop(mainGameWindow, 12);

    }
}
