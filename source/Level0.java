

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Level0 extends Level implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JButton button;
    public Point startPoint;
    public Scenario scenario = new Scenario();

    public Level0() {
        init();
    }

    private void init() {
        frame = new JFrame("Intro");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());
        addElements();

        frame.setVisible(true);
    }

    private void addElements() {
        label = new JLabel(scenario.getScenario());
        frame.add(label);
        button = new JButton("Continue");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scene.allLevelInstances.add(new Level1(scenario));
                frame.dispose();
            }
        });
        frame.add(button);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        JLabel touched = (JLabel) (e.getComponent());

        Point location = SwingUtilities.convertPoint(touched, e.getPoint(), touched.getParent());
        if (touched.getParent().getBounds().contains(location)) {
            Point newLocation = touched.getLocation();
            newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);
            newLocation.x = Math.max(newLocation.x, 0);
            newLocation.y = Math.max(newLocation.y, 0);
            newLocation.x = Math.min(newLocation.x, touched.getParent().getWidth() - touched.getWidth());
            newLocation.y = Math.min(newLocation.y, touched.getParent().getHeight() - touched.getHeight());
            touched.setLocation(newLocation);
            startPoint = location;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
