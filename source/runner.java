
public class runner {

    public static void main(String[] args) {
        window mainGameWindow = new window();
        gameloop gameRunner = new gameloop(mainGameWindow, 10);
    }
}
