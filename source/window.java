
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class window implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JLabel label2;
    public JTextField textField;
    public JButton button;
    public JButton button2;
    Point startPoint;
    public window() {
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        panel = new JPanel(); // panel kinda like <View>. 
        label = new JLabel("" ); // text i guess..
        textField = new JTextField(20); // text input
        button = new JButton("Save"); // button.
        button2 = new JButton("Add New Label");

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(button2);
        button.addActionListener((ActionEvent e) -> {
            panel.add(new JLabel(textField.getText()));
            updateFrame(frame);
        });
        button2.addActionListener((ActionEvent e) -> {
            gameloop.__inst__.Instantiate(new movableComponent(new ImageIcon("assets/75519.png"), new Point(0,0)), 0);

        });
        frame.add(panel, BorderLayout.CENTER);

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
        startPoint = SwingUtilities.convertPoint(label2, e.getPoint(), label2.getParent());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        startPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    
}
