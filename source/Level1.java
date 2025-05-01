import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    public JButton addComputer, addServer, addRouter, addSwitch, resetCart, checkout;
    public ArrayList<ComputerComponent> cart;
    public Point startPoint;
    public Scenario scenario;
    public int budget;
    public int iBudget;
    public final ComputerComponent COMPUTER = new ComputerComponent(1000, "Computer", 1, "assets/PC.png");
    public final ComputerComponent SERVER = new ComputerComponent(2000, "Server", 2, "assets/Server.png");
    public final ComputerComponent ROUTER = new ComputerComponent(100, "Router", 5, "assets/Router.png");
    public final ComputerComponent C_SWITCH = new ComputerComponent(300, "Switch", 20, "assets/Switch.png");
    public static Scenario build; 
    public Level1(Scenario s) {
        scenario = s;
        budget = scenario.getBudget();
        iBudget = scenario.getBudget();
        build = s; 
        init();
    }

    private void init() {
        this.cart = new ArrayList<ComputerComponent>();
        frame = new JFrame("Level One");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        addElements();

        frame.setVisible(true);
    }

    private void addElements() {
        GridBagConstraints c = new GridBagConstraints();
        addComputer = new JButton(
                String.format("Computer ($%d, 0 in cart)", COMPUTER.getPrice()), COMPUTER.getIcon());
        addComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                budget -= COMPUTER.getPrice();
                label.setText(String.valueOf(budget));
                cart.add(new ComputerComponent(1000, "Computer", 1, "assets/PC.png"));
                addComputer.setText(
                        String.format("Computer ($%d, %d in cart)", COMPUTER.getPrice(),
                                countComponents(COMPUTER.getType())));
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        frame.add(addComputer, c);

        addServer = new JButton(
                String.format("Server ($%d, 0 in cart)", SERVER.getPrice()), SERVER.getIcon());
        addServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                budget -= SERVER.getPrice();
                label.setText(String.valueOf(budget));
                cart.add(new ComputerComponent(2000, "Server", 2, "assets/Server.png"));
                addServer.setText(
                        String.format("Server ($%d, %d in cart)", SERVER.getPrice(),
                                countComponents(SERVER.getType())));
            }
        });
        c.gridx = 1;
        frame.add(addServer, c);

        addRouter = new JButton(
                String.format("Router ($%d, 0 in cart)", ROUTER.getPrice()), ROUTER.getIcon());
        addRouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                budget -= ROUTER.getPrice();
                label.setText(String.valueOf(budget));
                cart.add(new ComputerComponent(100, "Router", 5, "assets/Router.png"));
                addRouter.setText(
                        String.format("Router ($%d, %d in cart)", ROUTER.getPrice(),
                                countComponents(ROUTER.getType())));
            }
        });
        c.gridx = 2;
        frame.add(addRouter, c);

        addSwitch = new JButton(
                String.format("Switch ($%d, 0 in cart)", C_SWITCH.getPrice()), C_SWITCH.getIcon());
        addSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                budget -= C_SWITCH.getPrice();
                label.setText(String.valueOf(budget));
                cart.add(new ComputerComponent(300, "Switch", 20, "assets/Switch.png"));
                addSwitch.setText(
                        String.format("Switch ($%d, %d in cart)", C_SWITCH.getPrice(),
                                countComponents(C_SWITCH.getType())));
            }
        });
        c.gridx = 3;
        frame.add(addSwitch, c);

        label = new JLabel(String.valueOf(budget));
        c.gridx = 0;
        c.gridy = 1;
        frame.add(label, c);

        resetCart = new JButton("Reset");
        resetCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.clear();
                budget = iBudget;
                label.setText(String.valueOf(budget));
                addComputer.setText(
                        String.format("Computer ($%d, 0 in cart)", COMPUTER.getPrice()));
                addServer.setText(
                        String.format("Server ($%d, 0 in cart)", SERVER.getPrice()));
                addRouter.setText(
                        String.format("Router ($%d, 0 in cart)", ROUTER.getPrice()));
                addSwitch.setText(
                        String.format("Switch ($%d, 0 in cart)", C_SWITCH.getPrice()));
            }
        });
        c.gridx = 2;
        frame.add(resetCart, c);

        checkout = new JButton("Checkout");

        c.gridx = 3;
        frame.add(checkout, c);
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cart.size() >= 1 && budget >= 0) {
                    new Level2(cart);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Empty cart or over budget");
                }
            }
        });
    }

    public ArrayList<ComputerComponent> getCart() {
        return cart;
    }

    private int countComponents(String type) {
        int count = 0;
        for (ComputerComponent component : cart) {
            if (component.getType().equals(type)) {
                count++;
            }
        }
        return count;
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
