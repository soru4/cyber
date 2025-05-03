import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Level3 implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JLabel label2;
    public JTextField textField;
    public JButton button;
    public JButton button2;
    Point startPoint;
    private static final int ROWS = 5;
    private static final int COLS = 2;
    public String[] options = { "IP Address", "Connections", "DMZ Setup", "Open VPN Setup", "DHCP Setup" };

    public JButton [] optionsClicked = new JButton[options.length];

    public Level3() {
        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("default button click");
        updateFrame(frame);
    }

    private void init() {
        frame = new JFrame("Level Three");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(1, 1));
        JPanel optionsPanel = new JPanel(new GridLayout(ROWS, 1));

        JPanel topPanel = new JPanel(true);
        JLabel topLabel = new JLabel("IP Router Settings: ");
        JButton topButton = new JButton("Check");
        topButton.addActionListener((ActionEvent e) -> {
            if(Level1.build.securityString.equals("isolated")){
                JOptionPane.showMessageDialog(frame, "You have not set up the DMZ yet. Please do so before checking the router settings.");
            } else if(Level1.build.securityString.equals("privacy-focused")){
                JOptionPane.showMessageDialog(frame, "You have not set up the VPN yet. Please do so before checking the router settings.");
            } else if(Level1.build.securityString.equals("informal")){
                JOptionPane.showMessageDialog(frame, "You have not set up the DHCP yet. Please do so before checking the router settings.");
            } else {
                new Level4();
            }
        });
        topPanel.add(topButton);
        topLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
        topLabel.setForeground(Color.WHITE); // set the font color
        topPanel.add(topLabel);
        topPanel.setSize(1920, 100); 
        topPanel.setBackground(Color.GRAY);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                
            }
            optionsClicked[i] = new JButton(options[i]);
            optionsClicked[i].setBackground(Color.WHITE);
            optionsPanel.add(optionsClicked[i]);

        }
        for(JButton x : optionsClicked) {
            if(x.getText().equals("IP Address")){
                x.addActionListener((ActionEvent e) -> {

                    centerPanel.removeAll();
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    JLabel ipLabel = new JLabel("IP Address:" + "10.2.32.23");
                    ipLabel.setFont(new Font("Arial", Font.BOLD, 24));

                    centerPanel.add(ipLabel);
                    System.out.println("Button clicked: " + e.getActionCommand());
                });
            } else if(x.getText().equals("Connections")){   
                   
                    x.addActionListener((ActionEvent e) -> {
                        centerPanel.removeAll();
                        centerPanel.revalidate();
                        centerPanel.repaint();
                        String connsString = "";
                        ArrayList<ComputerComponent> x2 = allChildComponents(Level2.world);
                        for (ComputerComponent comp : x2) {
                            connsString += comp.getType() + " \n";
                        }

                        JLabel ipLabel = new JLabel("Connections: " +  connsString);
                        

                        ipLabel.setFont(new Font("Arial", Font.BOLD, 24));
    
                        centerPanel.add(ipLabel);
                        System.out.println("Button clicked: " + e.getActionCommand());
                    });
                } else if(x.getText().equals("DMZ Setup")){
                    x.addActionListener((ActionEvent e) -> {
                        centerPanel.removeAll();
                        centerPanel.revalidate();
                        centerPanel.repaint();
                        JLabel ipLabel = new JLabel("DMZ Set Up:" +  "Not yet implemented");
                        ipLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
    
                        centerPanel.add(ipLabel);
                        System.out.println("Button clicked: " + e.getActionCommand());
                    });
                } else if(x.getText().equals("Open VPN Setup")){
                    x.addActionListener((ActionEvent e) -> {

                        centerPanel.removeAll();
                        centerPanel.revalidate();
                        centerPanel.repaint();
                        JLabel ipLabel = new JLabel("OpenVPN:" +  " Set Up VPN Server and Client");

                        ipLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
                        JButton ipButton = new JButton("Set Up VPN Server and Client");
                        centerPanel.add(ipLabel);
                        centerPanel.add(ipButton);
                        ipButton.addActionListener((ActionEvent e2) -> {
                           
                            System.out.println("VPN Server and Client Set Up");
                        });
                        System.out.println("Button clicked: " + e.getActionCommand());
                    });
        }
        else if(x.getText().equals("DHCP Setup")){
            x.addActionListener((ActionEvent e) -> {

                centerPanel.removeAll();
                centerPanel.revalidate();
                centerPanel.repaint();
                JLabel ipLabel = new JLabel("DHCP Set Up:" +  "");
                ipLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
                centerPanel.add(ipLabel);
                JTextField clientIpField = new JTextField(10);
                JTextField rangeField = new JTextField(10);
                JPanel dhcpPanel = new JPanel();
                dhcpPanel.add(new JLabel("Client IP Address:"));
                dhcpPanel.add(clientIpField);
                dhcpPanel.add(new JLabel("Range of IP Addresses:"));
                dhcpPanel.add(rangeField);
                JButton dhcpButton = new JButton("Set Up DHCP Server and Client");
                dhcpButton.addActionListener((ActionEvent e2) -> {
                    String clientIp = clientIpField.getText();
                    String range = rangeField.getText();
                
                    System.out.println("DHCP Server and Client Set Up with Client IP: " + clientIp + " and Range: " + range);
                });
                centerPanel.add(dhcpPanel);
            });

    
    }}
        frame.add(optionsPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        updateFrame(frame);
    }

    public ArrayList<ComputerComponent> allChildComponents(ComputerComponent comp) {
        ArrayList<ComputerComponent> allComponents = new ArrayList<ComputerComponent>();
        if (comp.conn.isEmpty()) {
            allComponents.add(comp);
            return allComponents;
        }
            for (ComputerComponent comp1 : comp.conn) {
                allComponents.add(comp1);
                allComponents.addAll(allChildComponents(comp1));
            
        }
        return allComponents;
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
