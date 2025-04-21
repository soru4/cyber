
public class TextCounter extends MonoBehaviour {
    int x = 0;

    public TextCounter() {
        super();
    }

    public void Start() {

    }

    public void Update() {
        // System.out.println("yay");
        win.label.setText(x + "");

        x += 1;
    }

}
