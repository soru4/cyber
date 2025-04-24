import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.*;

public class Level1 implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JTextField textField;
    public JButton button;
    public ArrayList<ComputerComponent> cart;
    public Point startPoint;
    public final String[] COMPONENTS = {"Server", "Router", "Switch", "Computer"};

    public Level1(JPanel x, Window w) {
        this.cart = new ArrayList<ComputerComponent>();
        init();
    }

    private void init() {
        frame = new JFrame("Level Three");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < COMPONENTS.length; i++) {
            String text = COMPONENTS[i];
            frame.add(new JButton(text));
        }

        frame.setVisible(true);
    }


    public void setLevelActive() {
        // Set the level active and perform any necessary initialization here
        System.out.println("Level 1 is now active.");
    }

    public ArrayList<ComputerComponent> getCart() {
        return cart;
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
