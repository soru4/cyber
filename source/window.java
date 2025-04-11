
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class window implements ActionListener {

    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JTextField textField;
    public JButton button;
    public int x = 0;

    public window() {
        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("default button click");
        updateFrame(frame);
    }

    private void init() {
        frame = new JFrame("Hello World");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        panel = new JPanel(); // panel kinda like <View>.
        label = new JLabel("" + x); // text i guess..
        textField = new JTextField(20); // text input
        button = new JButton("Save"); // button.

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        button.addActionListener((ActionEvent e) -> {
            panel.add(new JLabel(textField.getText()));
            updateFrame(frame);
        });
        frame.add(panel, BorderLayout.CENTER);

        updateFrame(frame);
    }

    public void updateFrame(JFrame j) {
        j.setVisible(true);
    }
}
