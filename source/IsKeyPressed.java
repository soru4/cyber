import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import javax.crypto.spec.OAEPParameterSpec;

public class IsKeyPressed {

    public enum KeyCode{
        A,
        B,
        C, 
        D, 
        E,
        F,
        G,
        H,
        I,
        J,
        K,
        L,
        M,
        N,
        O,
        P,
        Q,
        R,
        S,
        T,
        U,
        V,
        W,
        X,
        Y,
        Z, 
        One, 
        Two,
        Three, 
        Four,
        Five, 
        Six,
        Seven,
        Eight,
        Nine,
        Zero
    }
    private static volatile boolean wPressed = false;
    public static boolean isWPressed() {
        synchronized (IsKeyPressed.class) {
            return wPressed;
        }
    }
    private static ArrayList keysPressed = new ArrayList<KeyCode>();
    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = true;
                        }
                        switch (ke.getKeyCode()) {
                            case KeyEvent.VK_A:
                                KeyCode x = KeyCode.A; 
                                keysPressed.add(x);
                                break;
                            case KeyEvent.VK_B:
                                KeyCode x1 = KeyCode.B; 
                                keysPressed.add(x1);
                                break;
                            default:
                                throw new AssertionError();
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = false;
                        }
                        break;
                    }
                    return false;
                }
            }
        });
    }
}