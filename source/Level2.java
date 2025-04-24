import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Level2 implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    Point startPoint;
    private java.util.Stack<JLabel> wires = new Stack<JLabel>();

    public Level2() {
        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("default button click");
        updateFrame(frame);
    }

    private void init() {
        frame = new JFrame("Level One");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(); // panel kinda like <View>.
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        panel.setBackground(Color.GRAY);
        
        Button button2 = new Button("Add New Wire");
        panel.add(button2);

        Button button3 = new Button("Remove Last Wire");
        panel.add(button3);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        button2.addActionListener((ActionEvent e) -> {
            ImageIcon imageIcon = new ImageIcon("cyber/assets/75519.png");
            JLabel instanceLabel = new JLabel(imageIcon);
            wires.add(instanceLabel);
            instanceLabel.setVisible(true);
            instanceLabel.addMouseListener(this);
            instanceLabel.addMouseMotionListener(this);
            panel.add(instanceLabel);
            instanceLabel.setHorizontalAlignment(JLabel.CENTER);
            instanceLabel.setVerticalAlignment(JLabel.CENTER);
            panel.revalidate();
            panel.repaint();

        });

        button3.addActionListener((ActionEvent e) -> {
            JLabel instanceLabel = wires.pop();
            panel.remove(instanceLabel);
            panel.revalidate();
            panel.repaint();
        });

        updateFrame(frame);
    }

    public void updateFrame(JFrame j) {
        j.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel touched = (JLabel) (e.getComponent());
        startPoint = SwingUtilities.convertPoint(touched, e.getPoint(), touched.getParent());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        startPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseExited'");
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
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

}
