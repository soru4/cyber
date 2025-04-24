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
    public Scenario scenario;
    public final ComputerComponent COMPUTER = new ComputerComponent(1000, "Computer");
    public final ComputerComponent SERVER = new ComputerComponent(2000, "Server");
    public final ComputerComponent ROUTER = new ComputerComponent(1000, "Router");
    public final ComputerComponent C_SWITCH = new ComputerComponent(1000, "Switch");

    public Level1() {
        init();
    }

    private void init() {
        this.cart = new ArrayList<ComputerComponent>();
        this.scenario = new Scenario();
        frame = new JFrame("Level One");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        addElements();

        frame.setVisible(true);
    }

    private void addElements() {
        GridBagConstraints c = new GridBagConstraints();
        button = new JButton("Computer");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                scenario.setBudget(scenario.getBudget() - COMPUTER.getPrice());
                label.setText(String.valueOf(scenario.getBudget()));
                cart.add(COMPUTER);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        frame.add(button, c);

        button = new JButton("Server");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scenario.setBudget(scenario.getBudget() - SERVER.getPrice());
                label.setText(String.valueOf(scenario.getBudget()));
                cart.add(SERVER);
            }
        });
        c.gridx = 1;
        frame.add(button, c);

        button = new JButton("Router");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scenario.setBudget(scenario.getBudget() - ROUTER.getPrice());
                label.setText(String.valueOf(scenario.getBudget()));
                cart.add(ROUTER);
            }
        });
        c.gridx = 2;
        frame.add(button, c);

        button = new JButton("Switch");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scenario.setBudget(scenario.getBudget() - C_SWITCH.getPrice());
                label.setText(String.valueOf(scenario.getBudget()));
                cart.add(C_SWITCH);
            }
        });
        c.gridx = 3;
        frame.add(button, c);

        label = new JLabel(String.valueOf(scenario.getBudget()));
        c.gridx = 0;
        c.gridy = 1;
        frame.add(label, c);

        button = new JButton("Checkout");
        c.gridx = 3;
        frame.add(button, c);
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

    public Scenario getScenario() {
        return scenario;
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
