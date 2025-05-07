
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Level3 extends Level implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JLabel label2;
    public JTextField textField;
    public JButton button;
    public JButton button2;

    public boolean DMZ = false;
    public boolean VPN = false;
    public boolean DHCP = false;
    public boolean TCP = false;
    public boolean UDP = false; 

    public String port;
    public String VPNsubnet;
    public String ipString;
    public String rangeString;
    Point startPoint;
    private static final int ROWS = 5;
    private static final int COLS = 2;
    public String[] options = { "IP Address", "Connections", "DMZ Setup", "Open VPN Setup", "DHCP Setup" };

    public JButton[] optionsClicked = new JButton[options.length];

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
        JButton topButton1 = new JButton("Check Senario");
        topButton1.addActionListener((ActionEvent e) ->{
            JOptionPane.showMessageDialog(frame, Level1.build.getScenario());
        });
        topButton.addActionListener((ActionEvent e) -> {
            if (Level1.build.securityString.equals("isolated")) {
              if(DMZ && DHCP && VPN ){
                frame.dispose();
                Scene.allLevelInstances.add(new Level4());
              }
            } else if (Level1.build.securityString.equals("privacy-focused")) {
                if(VPN && DHCP){
                    frame.dispose();
                    Scene.allLevelInstances.add(new Level4());
                }
            } else if (Level1.build.securityString.equals("informal")) {
                if( DHCP){
                    frame.dispose();
                    Scene.allLevelInstances.add(new Level4());
                }
            } else {
                frame.dispose();
                Scene.allLevelInstances.add(new Level4());
            }
        });
        topPanel.add(topButton1);
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
        for (JButton x : optionsClicked) {
            if (x.getText().equals("IP Address")) {
                x.addActionListener((ActionEvent e) -> {

                    centerPanel.removeAll();
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    JLabel ipLabel = new JLabel("IP Address:" + Level2.world.getIP());
                    ipLabel.setFont(new Font("Arial", Font.BOLD, 24));

                    centerPanel.add(ipLabel);
                    System.out.println("Button clicked: " + e.getActionCommand());
                });
            } else if (x.getText().equals("Connections")) {

                x.addActionListener((ActionEvent e) -> {
                    centerPanel.removeAll();
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    String connsString = "";
                    ArrayList<ComputerComponent> x2 = allChildComponents(Level2.world);
                    JLabel ipLabel = new JLabel("Connections:");
                    ipLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
                    centerPanel.add(ipLabel);
                    for (ComputerComponent comp : x2) {
                        JLabel label = new JLabel(comp.getType() + " IP Address: " + comp.getIP());
                        label.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
                        centerPanel.add(label);
                        connsString += comp.getType() + " IP Address: " + comp.getIP() + "\n";
                    }
                
                    System.out.println("Button clicked: " + e.getActionCommand());
                });
            } else if (x.getText().equals("DMZ Setup")) {
                x.addActionListener((ActionEvent e) -> {
                    centerPanel.removeAll();
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    JLabel ipLabel = new JLabel("DMZ Set Up:");
                    ImageIcon i = new ImageIcon("assets/questionmark.png");
                    JButton b = new JButton(i);
                    b.addActionListener((ActionEvent e1) ->{
                        JOptionPane.showMessageDialog(frame, "Source Google: A DMZ, or demilitarized zone, is a network or subnet that acts as a buffer zone between an organization's internal network and the untrusted external network (like the internet). It protects the internal network by isolating services that need to be accessed from the outside. ");
                    });
                    ipLabel.setFont(new Font("Arial", Font.BOLD, 24));
                    // create button to set up DMZ
                    JButton ipButton = new JButton("Set Up DMZ Server and Client");

                    ipButton.setSelected(DMZ);

                    ipButton.addActionListener((ActionEvent e2) -> {
                        DMZ = true;
                        JOptionPane.showMessageDialog(frame, "DMZ set up");
                    });
                    ipButton.setFont(new Font("Arial", Font.BOLD, 24));
                    ipLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size

                    centerPanel.add(ipLabel);
                    centerPanel.add(b);
                    centerPanel.add(ipButton);
                    System.out.println("Button clicked: " + e.getActionCommand());
                });
            } else if (x.getText().equals("Open VPN Setup")) {
                x.addActionListener((ActionEvent e) -> {
                    
                    centerPanel.removeAll();
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    JLabel ipLabel = new JLabel("OpenVPN:" + " Set Up VPN Server and Client");

                    ImageIcon i = new ImageIcon("assets/questionmark.png");
                    JButton b = new JButton(i);
                    b.addActionListener((ActionEvent e1) ->{
                        JOptionPane.showMessageDialog(frame, "Source Google: OpenVPN refers to an open-source VPN protocol and software that enables secure, encrypted connections over the internet. It's a way to create a virtual private network (VPN) for secure data transmission, often used to protect sensitive information and bypass censorship or geographic restrictions. ");
                    });


                    ipLabel.setFont(new Font("Arial", Font.BOLD, 24));
                    JButton ipButton = new JButton("Set Up VPN Server and Client");
                    centerPanel.add(b);
                    ipButton.setSelected(VPN);
                    centerPanel.add(ipLabel);
                    centerPanel.add(ipButton);

                    JButton tcpButton = new JButton("TCP");
                    JButton udpButton = new JButton("UDP");
                    tcpButton.setSelected(TCP);
                    udpButton.setSelected(UDP);

                    JTextField portField = new JTextField(10);
                    portField.setText(port);
                    JPanel portPanel = new JPanel();
                    portPanel.add(new JLabel("Port Number:"));
                    portPanel.add(portField);
                    centerPanel.add(portPanel);
                    centerPanel.add(tcpButton);
                    centerPanel.add(udpButton);

                    JTextField subnetField = new JTextField(10);
                    JPanel subnetPanel = new JPanel();
                    subnetPanel.add(new JLabel("VPN Subnet Mask:"));
                    subnetField.setText(VPNsubnet);
                    subnetPanel.add(subnetField);
                    centerPanel.add(subnetPanel);

                    ipButton.addActionListener((ActionEvent e2) -> {

                        if ((tcpButton.isSelected() ) && !portField.getText().isEmpty() && !subnetField.getText().isEmpty()) {
                            this.port = portField.getText();
                            this.VPNsubnet = subnetField.getText();
                           
                            VPN = true;
                           
                        } else if ((udpButton.isSelected() ) && !portField.getText().isEmpty() && !subnetField.getText().isEmpty()) {
                            this.port = portField.getText();
                            this.VPNsubnet = subnetField.getText();
                           
                            VPN = true;
                           
                        } else {
                            System.out.println("Please select a protocol (TCP or UDP)");
                        }

                        System.out.println("VPN Server and Client Enable");
                    });

                    System.out.println("Button clicked: " + e.getActionCommand());
                });
            } else if (x.getText().equals("DHCP Setup")) {
                x.addActionListener((ActionEvent e) -> {

                    centerPanel.removeAll();
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    JLabel ipLabel = new JLabel("DHCP Set Up:" + "");
                    ImageIcon i = new ImageIcon("assets/questionmark.png");
                    JButton b = new JButton(i);
                    b.addActionListener((ActionEvent e1) ->{
                        JOptionPane.showMessageDialog(frame, "Source Google: DHCP, or Dynamic Host Configuration Protocol, is a network protocol that automatically assigns IP addresses and other network configuration information to devices on a network. It simplifies IP management by automating the process of assigning IP addresses to devices, reducing manual configuration efforts. ");
                    });

                    ipLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set the font size
                    centerPanel.add(ipLabel);
                    centerPanel.add(b);
                    JTextField clientIpField = new JTextField(10);
                    clientIpField.setText(ipString);
                    JTextField rangeField = new JTextField(10);
                    rangeField.setText(rangeString);
                    JPanel dhcpPanel = new JPanel();
                    dhcpPanel.add(new JLabel("Client IP Address:"));
                    dhcpPanel.add(clientIpField);
                    dhcpPanel.add(new JLabel("Range of IP Addresses:"));
                    dhcpPanel.add(rangeField);
                    JButton dhcpButton = new JButton("Set Up DHCP Server and Client");
                    dhcpButton.addActionListener((ActionEvent e2) -> {
                        if (!clientIpField.getText().isEmpty() && !rangeField.getText().isEmpty()){
                            ipString = clientIpField.getText();
                            rangeString = rangeField.getText();
                            DHCP = true;
                        }
                        System.out.println("DHCP Server and Client Set Up with Client IP: " + clientIpField.getText()
                                + " and Range: " + rangeField.getText());
                        JOptionPane.showMessageDialog(frame, "DHCP Server and Client Set Up with Client IP: "
                                + clientIpField.getText() + " and Range: " + rangeField.getText());

                    });
                    centerPanel.add(dhcpPanel);
                });

            }
        }
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
