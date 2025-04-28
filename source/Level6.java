import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Level6 implements ActionListener, MouseListener, MouseMotionListener {

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
    public int score =0;
    public ArrayList<Cipher> cipher = new ArrayList<Cipher>();


    
    public Level6() {

        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("default button click");
        updateFrame(frame);
    }

    private void init() {
        frame = new JFrame("Level Six");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(1, 1));
        

        JPanel topPanel = new JPanel(true);
        JLabel topLabel = new JLabel("Level Six");
        topLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
        topLabel.setForeground(Color.WHITE); // set the font color
        topPanel.add(topLabel);
        topPanel.setSize(1920, 100); // set the size of the top panel
        topPanel.setBackground(Color.GRAY);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        JTextField textField = new JTextField(20);
        questions(centerPanel, textField);
       

       

        frame.add(centerPanel, BorderLayout.CENTER);


        updateFrame(frame);
    }

    private void questions(JPanel centerPanel, JTextField textField1) {
        if (score == 0) {
            Cipher c = new Cipher("Trains are the best mode of transport", "", 3);
            c.encrypt(c.getPlainText(), (int)(Math.random() * 26)+1);
            JLabel questionLabel = new JLabel("Decrypt the cipher:  " + c.getCipherText() + " Which has a shift of: " + c.getShift());
            questionLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
            JButton button = new JButton("Submit");
            centerPanel.add(questionLabel);
            centerPanel.add(textField1);
            centerPanel.add(button);
            button.addActionListener((ActionEvent e) -> {
                String answer = textField1.getText();
                if (answer.equals(c.getPlainText())) {
                    centerPanel.remove(questionLabel);
                    centerPanel.remove(textField1);
                    centerPanel.remove(button);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    score++;
                    questions(centerPanel, textField1);
                    JOptionPane.showMessageDialog(frame, "Correct!");
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect! The correct answer is: " + c.getPlainText());
                }
            });
        }
        if (score == 1) {
            Cipher c = new Cipher("The quick brown fox jumps over the lazy dog", "", 3);
            c.encrypt(c.getPlainText(), (int)(Math.random() * 26)+1);
            JLabel questionLabel = new JLabel("Decrypt the cipher:  " + c.getCipherText() + " Which has a shift of: " + c.getShift());
            questionLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
            JButton button = new JButton("Submit");
            centerPanel.add(questionLabel);
            centerPanel.add(textField1);
            centerPanel.add(button);
            button.addActionListener((ActionEvent e) -> {
                String answer = textField1.getText();
                if (answer.equals(c.getPlainText())) {
                    centerPanel.remove(questionLabel);
                    centerPanel.remove(textField1);
                    centerPanel.remove(button);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    score++;
                    questions(centerPanel, textField1);
                    JOptionPane.showMessageDialog(frame, "Correct!");
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect! The correct answer is: " + c.getPlainText());
                }
            });
        }
        if (score == 2) {
            Cipher c = new Cipher("The only way to do great work is to love what you do", "", 3);
            c.encrypt(c.getPlainText(), (int)(Math.random() * 26)+1);
            JLabel questionLabel = new JLabel("Decrypt the cipher:  " + c.getCipherText() + " Which has a shift of: " + c.getShift());
            questionLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
            JButton button = new JButton("Submit");
            button.addActionListener((ActionEvent e) -> {
                centerPanel.add(questionLabel);
                centerPanel.add(textField1);
                centerPanel.add(button);
                String answer = textField1.getText();
                if (answer.equals(c.getPlainText())) {
                    centerPanel.remove(questionLabel);
                    centerPanel.remove(textField1);
                    centerPanel.remove(button);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    score++;
                    questions(centerPanel, textField1);
                    JOptionPane.showMessageDialog(frame, "Correct!");
                   
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect! The correct answer is: " + c.getPlainText());
                }
            });
        }
        if(score == 3){
            //end game
        }
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


class Cipher{
    public String plainText; 
    public String cipherText; 
    public int shift; 

    public String getPlainText() {
        return plainText;
    }
    public String getCipherText() {
        return cipherText;
    }
    public int getShift() {
        return shift;
    }
    public Cipher(String plainText, String cipherText, int shift) {
        this.plainText = plainText;
        this.cipherText = cipherText;
        this.shift = shift;
    }
    public String encrypt(String plainText, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        this.shift = shift;
        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);
            }
            encryptedText.append(c);
        }
        cipherText = encryptedText.toString();
        return encryptedText.toString();
    }
}