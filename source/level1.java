
import javax.swing.JPanel;

class level1 extends levelTemplate {
    public level1(JPanel panel, window w) {
        super(panel, w);
    }

    public void setLevelActive() {
        this.win.panel = this.mainPanel;
    }

}