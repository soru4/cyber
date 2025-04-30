import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Level2 implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    Point startPoint;
    private java.util.Stack<Connection> wires = new Stack<Connection>();
    //Replace components with arraylist from level1
    private ArrayList<JLabel> components = new ArrayList<JLabel>();
    ArrayList<ComputerComponent> pulledCart;
    public ArrayList<ComponentHolder> holders = new ArrayList<ComponentHolder>();
    public Queue<ComponentHolder> recentlyClicked = new LinkedList<ComponentHolder>();
    public ComputerComponent world;
    public Level2(ArrayList<ComputerComponent> cart) {
        pulledCart = cart;
        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("default button click");
        updateFrame(frame);
    }

    private void init() {
        

        frame = new JFrame("Level Two");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(); // panel kinda like <View>.
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);

        panel2.setBackground(Color.GRAY);
        frame.add(panel2, BorderLayout.CENTER);

        populateComponentList(pulledCart, panel2);

        panel.setBackground(Color.YELLOW);
        
        Button button2 = new Button("Add New Wire");
        panel.add(button2);

        Button button3 = new Button("Remove Last Wire");
        panel.add(button3);

        Button button4 = new Button("Check Connections");
        button4.addActionListener((ActionEvent e) -> {
            if(world.CheckConnection()){
                JOptionPane.showMessageDialog(frame, "All connections in your world are valid!!");
            }else{
                JOptionPane.showMessageDialog(frame, "You have one or more invalid connections in your world. Please check your connections and try again! You are bad!");
            }


        });
        panel.add(button4);

        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
        button2.addActionListener((ActionEvent e) -> { // on button press it creates a wire image currently. 
            /* 
            ImageIcon imageIcon = new ImageIcon("assets/copperWire.png");
            JLabel instanceLabel = new JLabel(imageIcon);
            wires.add(instanceLabel);
            instanceLabel.setVisible(true);
            instanceLabel.addMouseListener(this);
            instanceLabel.addMouseMotionListener(this);
            panel2.add(instanceLabel);
            instanceLabel.setHorizontalAlignment(JLabel.CENTER);
            instanceLabel.setVerticalAlignment(JLabel.CENTER);
            panel2.revalidate();
            panel2.repaint();
            */
            if(recentlyClicked.size()==2){
               
                ComponentHolder recentlyClicked1 = recentlyClicked.poll(); 
                ComponentHolder recentlyClicked2 = recentlyClicked.poll();
               
                ComputerComponent obj1 = recentlyClicked1.component;
                ComputerComponent obj2 = recentlyClicked2.component;
                //connects first component to second. 
                if(obj1.addConnection(obj2, frame)){
                    Connection x = new Connection(recentlyClicked1, recentlyClicked2);
                    x.realizeConnection();
                    ImageIcon imageIcon = new ImageIcon("assets/transformedFile.png");
                    JLabel instanceLabel = new JLabel(imageIcon);
                    panel2.add(instanceLabel);
                    wires.add(x);
                    recentlyClicked.clear();
                    frame.add(x.getWire());
                    panel2.revalidate();
                    panel2.repaint();
                }
                
                
            }else{
                JOptionPane.showMessageDialog(frame, "Can't create a connection with current selection. ");
            }
        });

        button3.addActionListener((ActionEvent e) -> { // removes the mostly recent wire connection
            if(wires.size() > 0){
                Connection instanceLabel = wires.pop();
                panel2.remove(instanceLabel.getWire());
                panel2.revalidate();
                panel2.repaint();
            }
        });


        placeLevel1Components(panel2, components);
        updateFrame(frame);
    }

    public void updateFrame(JFrame j) {
        j.setVisible(true);
    }

    public void placeLevel1Components(JPanel p, ArrayList<JLabel> arr){
        for(JLabel l: arr){
            l.setVisible(true);
            l.addMouseListener(this);
            l.addMouseMotionListener(this);
            p.add(l);
        }
    }

    public void populateComponentList(ArrayList<ComputerComponent> cart, JPanel panel){

        world = new ComputerComponent(0, "World", 1);
        ImageIcon imageIcon1 = new ImageIcon("cyber/assets/75519.png");
        JLabel instanceLabel1 = new JLabel(imageIcon1);
        holders.add(new ComponentHolder(world, instanceLabel1));
        components.add(instanceLabel1);
        instanceLabel1.setVisible(true);
        instanceLabel1.addMouseListener(this);
        instanceLabel1.addMouseMotionListener(this);
        panel.add(instanceLabel1);
        for(ComputerComponent c: cart){
            if(c.getType().equals("Computer")){
                
                ImageIcon imageIcon = new ImageIcon("cyber/assets/PC.png");
                JLabel instanceLabel = new JLabel(imageIcon);
                holders.add(new ComponentHolder(c, instanceLabel));
                components.add(instanceLabel);
                instanceLabel.setVisible(true);
                instanceLabel.addMouseListener(this);
                instanceLabel.addMouseMotionListener(this);
                panel.add(instanceLabel);
                
                Insets insets = panel.getInsets();
                Dimension size = instanceLabel.getPreferredSize();
                instanceLabel.setBounds(25+insets.left, 5+insets.top,size.width,size.height);
            }
            else if(c.getType().equals("Server")){
                ImageIcon imageIcon = new ImageIcon("cyber/assets/Server.png");
                JLabel instanceLabel = new JLabel(imageIcon);
                holders.add(new ComponentHolder(c, instanceLabel));
                components.add(instanceLabel);
                instanceLabel.setVisible(true);
                instanceLabel.addMouseListener(this);
                instanceLabel.addMouseMotionListener(this);
                panel.add(instanceLabel);
                
                Insets insets = panel.getInsets();
                Dimension size = instanceLabel.getPreferredSize();
                instanceLabel.setBounds(25+insets.left, 5+insets.top,size.width,size.height);
            }
            else if(c.getType().equals("Router")){
                ImageIcon imageIcon = new ImageIcon("cyber/assets/Router.png");
                JLabel instanceLabel = new JLabel(imageIcon);
                holders.add(new ComponentHolder(c, instanceLabel));
                components.add(instanceLabel);
                instanceLabel.setVisible(true);
                instanceLabel.addMouseListener(this);
                instanceLabel.addMouseMotionListener(this);
                panel.add(instanceLabel);
                
                Insets insets = panel.getInsets();
                Dimension size = instanceLabel.getPreferredSize();
                instanceLabel.setBounds(25+insets.left, 5+insets.top,size.width,size.height);
            }
            else if(c.getType().equals("Switch")){
                ImageIcon imageIcon = new ImageIcon("cyber/assets/Switch.png");
                JLabel instanceLabel = new JLabel(imageIcon);
                holders.add(new ComponentHolder(c, instanceLabel));
                components.add(instanceLabel);
                instanceLabel.setVisible(true);
                instanceLabel.addMouseListener(this);
                instanceLabel.addMouseMotionListener(this);
                panel.add(instanceLabel);
                
                Insets insets = panel.getInsets();
                Dimension size = instanceLabel.getPreferredSize();
                instanceLabel.setBounds(25+insets.left, 5+insets.top,size.width,size.height);
            }
        }
        panel.revalidate();
        panel.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel touched = (JLabel) (e.getComponent());
        startPoint = SwingUtilities.convertPoint(touched, e.getPoint(), touched.getParent());
        if(recentlyClicked.size()< 3 && SwingUtilities.isLeftMouseButton(e)){
            for(ComponentHolder x: holders){
                if(x.label == (JLabel) (e.getComponent())){
                    boolean doesContain = false;
                    for(ComponentHolder y : recentlyClicked)
                        if(y == x)
                            doesContain = true; 
                    if(!doesContain)
                        recentlyClicked.add(x);
                }
            }
            
        }
        else if(SwingUtilities.isRightMouseButton(e) || e.isPopupTrigger()){
            JPopupMenu popupMenu = null;
            for(ComponentHolder x: holders){

                if(x.label == (JLabel) (e.getComponent())){
                      popupMenu = createPopupMenu(x.component);
                }
            }
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
        else{
            JOptionPane.showMessageDialog(frame, "Clicking on too many components at once. Resetting clicked");
            recentlyClicked.clear();
        }

      

    }

    private JPopupMenu createPopupMenu(ComputerComponent x) {
        
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem item2 = new JMenuItem(x.type);
        JMenuItem item1 = new JMenuItem("Current Connections Number: " + x.conn.size());
        popupMenu.add(item2);
        popupMenu.add(item1);
        for(ComputerComponent y : x.conn){
            JMenuItem xJMenuItem = new JMenuItem("Connected to " + y.type);
            popupMenu.add(xJMenuItem);
        }
 
        
        return popupMenu;
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

class ComponentHolder{
    public ComputerComponent component; 
    public JLabel label; 

    public ComponentHolder ( ComputerComponent c, JLabel l){
        this.component = c;
        this.label = l; 
    }

    public ComputerComponent getComponent() {
        return component;
    }

    public JLabel getLabel() {
        return label;
    }
    

}

class Connection{
    public ComponentHolder object1; 
    public ComponentHolder object2; 
    private JLabel wire; 

    public Connection(ComponentHolder o1, ComponentHolder o2){
        object1 = o1;
        object2 = o2; 
    }

    public void realizeConnection(){
        int x1 = object1.getLabel().getX();
        int x2 = object1.getLabel().getX();
        int y1 = object1.getLabel().getX();
        int y2 = object1.getLabel().getX();

        int newWidth = (int)Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1,2));
        int newHeight = wire.getHeight();
        // creates a physical wire from one component to another; 
        try {
        BufferedImage bi = ImageIO.read(new File("assets/copperWire.png"));
        System.out.println(bi.toString());
        BufferedImage stretechedImage = new BufferedImage(newWidth, newHeight, bi.getType());
        Graphics2D g2d = stretechedImage.createGraphics();
        double angle = Math.atan2(y2-y1, x2-x1);
        System.out.println(angle);
        AffineTransform transform = new AffineTransform();
        transform.translate(newWidth / 2.0, newHeight / 2.0);
        transform.rotate(angle);
        transform.scale((double) newWidth / bi.getWidth(), 1);
        transform.translate(-bi.getWidth() / 2.0, -bi.getHeight() / 2.0);

        g2d.transform(transform);
        g2d.drawImage(bi, 0, 0, null);
        System.out.println(g2d.toString());
        g2d.dispose();
        
        ImageIO.write(stretechedImage, "png", new File("assets/transformedFile.png"));


    } catch (Exception e) {
        JOptionPane.showMessageDialog(wire, "Failed to make a wire image" + e);
    }
    }

    public JLabel getWire(){
        return wire; 
    }
}
