

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Level5 extends Level implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JLabel label2;
    public JTextField textField;
    public JButton button;
    public JButton button2;
    Point startPoint;
    private static final int ROWS = 4;
    private static final int COLS = 2;
    public int score = 0;
    public ArrayList<QuestionList> questions = new ArrayList<QuestionList>();

    public Level5() {
        questions.add(new QuestionList("What is the primary function of the Session Layer?",
                new String[]{"Ensuring reliable data transmission","Managing connections and dialogs between applications","Routing data packets across a network","Formatting data for transport"}, "Ensuring reliable data transmission"));
        questions.add(new QuestionList(
                "Which of the following is a key service provided by the Session Layer?",
                new String[]{" TCP and UDP protocol selection",  "Address resolution and subnet masking", "Session establishment, maintenance, and termination", "Encryption and decryption of data "},
                "Session establishment, maintenance, and termination"));
        questions.add(new QuestionList("If a session is broken, what action might the session layer take?",
                new String[]{"It will attempt to re-establish the session", "It will discard the data that was being transmitted",  "It will report the error to the network layer", "It will notify the application layer that the connection has been lost"}, "It will attempt to re-establish the session"));

        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("default button click");
        updateFrame(frame);
    }

    private void init() {
        frame = new JFrame("Level Five");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(1, 1));

        JPanel topPanel = new JPanel(true);
        JLabel topLabel = new JLabel("Level Five");
        topLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
        topLabel.setForeground(Color.WHITE); // set the font color
        topPanel.add(topLabel);
        topPanel.setSize(1920, 100); // set the size of the top panel
        topPanel.setBackground(Color.GRAY);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        for (QuestionList q : questions) {
            JPanel questionPanel = new JPanel(new GridLayout(1, 1));
            JLabel questionLabel = new JLabel(q.getQuestion());
            questionLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
            questionLabel.setForeground(Color.WHITE); // set the font color
            questionPanel.add(questionLabel);
            questionPanel.setSize(1920, 100); // set the size of the top panel
            questionPanel.setBackground(Color.GRAY);
            centerPanel.add(questionPanel);

            JPanel optionsPanel = new JPanel(new GridLayout(1, 1));
            for (String option : q.getOptions()) {
                JButton optionButton = new JButton(option);
                optionButton.setBackground(Color.WHITE);
                optionButton.addActionListener((ActionEvent e) -> {
                    if (option.equals(q.getCorrectString())) {
                        score++;
                        JOptionPane.showMessageDialog(frame, "Correct! Your score is: " + score);
                        if (score >= questions.size()) {
                            // new Level2(); // Assuming Level1 is the next level
                            frame.dispose();
                            Scene.allLevelInstances.add(new Level6());
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect! Your score is: " + score);
                    }
                });
                centerPanel.add(optionButton);
            }

        }
        frame.add(centerPanel, BorderLayout.CENTER);

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

