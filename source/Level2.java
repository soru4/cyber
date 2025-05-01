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
    public boolean isPlaceMode = false; 
    public static int numOfWires = 0 ; 
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
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                for(int i = 0; i <=numOfWires; i++){
                    File myObj = new File("assets/transformedFile"+i+".png"); 
                    if (myObj.delete()) { 
                      System.out.println("Deleted the file: " + myObj.getName());
                    } else {
                      System.out.println("Failed to delete the file.");
                    } 
                }
                System.exit(0);
            }
        });

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
            int computer = 0;
            for(ComponentHolder s: holders ){
                if(s.component.type.equals("Computer"))
                    computer++;
            }
            if(world.CheckConnection() && computer>=Level1.build.getWorkforceSize()){
                JOptionPane.showMessageDialog(frame, "All connections in your world are valid!!");
                frame.dispose();
                new Level3();
            }else{
                JOptionPane.showMessageDialog(frame, "You have one or more invalid connections in your world or you dont have enough computers. Please check your connections and try again! You are bad!");
            }


        });

        Button button5 = new Button("Turn on Build Mode");
        button5.addActionListener((ActionEvent e) -> {
           isPlaceMode = !isPlaceMode; 
            button5.setLabel(isPlaceMode ? "Turn off Build Mode" : "Turn on Build Mode");

        });
        panel.add(button4);
        panel.add(button5);

        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
        button2.addActionListener((ActionEvent e) -> { // on button press it creates a wire image currently. 

            if(recentlyClicked.size()==2){
               
                ComponentHolder recentlyClicked1 = recentlyClicked.poll(); 
                ComponentHolder recentlyClicked2 = recentlyClicked.poll();
               
                ComputerComponent obj1 = recentlyClicked1.component;
                ComputerComponent obj2 = recentlyClicked2.component;
                //connects first component to second. 
                if(obj1.addConnection(obj2, frame)){
                    Connection x = new Connection(recentlyClicked1, recentlyClicked2);
                    x.realizeConnection(panel);
                    ImageIcon imageIcon = new ImageIcon("assets/transformedFile" + numOfWires + ".png");
                    JLabel instanceLabel = new JLabel(imageIcon);
                    numOfWires++;
                    panel2.add(instanceLabel);
                    
                    Insets insets = panel2.getInsets();
                    Dimension size = instanceLabel.getPreferredSize();
                    instanceLabel.setBounds((int)((recentlyClicked1.label.getX() -recentlyClicked1.label.getWidth() /2)) , (int)(( recentlyClicked1.label.getY() +recentlyClicked1.label.getHeight() /2 )) ,size.width,size.height); // idk how this works fix brandon
                    System.out.println("X: " +(int)((recentlyClicked1.label.getX() - recentlyClicked1.label.getWidth())));
                    System.out.println("Y: " +(int)((recentlyClicked1.label.getY() + recentlyClicked1.label.getHeight() )));
                    wires.add(x);
                    recentlyClicked.clear();
                    x.wire= instanceLabel;
                    frame.add(x.getWire());
                    panel.revalidate();
                    panel.repaint();
                }
                
                
            }else{
                JOptionPane.showMessageDialog(frame, "Can't create a connection with current selection. ");
            }
        });

        button3.addActionListener((ActionEvent e) -> { // removes the mostly recent wire connection
            if(wires.size() > 0){
                Connection instanceLabel = wires.pop();
                panel2.remove(instanceLabel.getWire());
                instanceLabel.object1.component.removeConnection(instanceLabel.object2.component);
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
        ImageIcon imageIcon1 = new ImageIcon("assets/75519.png");
        JLabel instanceLabel1 = new JLabel(imageIcon1);
        holders.add(new ComponentHolder(world, instanceLabel1));
        components.add(instanceLabel1);
        Insets insets1 = panel.getInsets();
        Dimension size1 = instanceLabel1.getPreferredSize();
        instanceLabel1.setBounds(25+insets1.left, 5+insets1.top,size1.width,size1.height);
        instanceLabel1.setVisible(true);
        instanceLabel1.addMouseListener(this);
        instanceLabel1.addMouseMotionListener(this);
        panel.add(instanceLabel1);
        for(ComputerComponent c: cart){
            if(c.getType().equals("Computer")){
                
                ImageIcon imageIcon = new ImageIcon("assets/PC.png");
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
                ImageIcon imageIcon = new ImageIcon("assets/Server.png");
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
                ImageIcon imageIcon = new ImageIcon("assets/Router.png");
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
                ImageIcon imageIcon = new ImageIcon("assets/Switch.png");
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
        if(recentlyClicked.size()< 3 && SwingUtilities.isLeftMouseButton(e) && isPlaceMode){
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
        }else if(!isPlaceMode){

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
    public  JLabel wire; 

    public Connection(ComponentHolder o1, ComponentHolder o2){
        object1 = o1;
        object2 = o2; 
    }

    public void realizeConnection(JPanel x){
        wire = new JLabel(new ImageIcon("assets/redWire.png"));
        Insets insets1 = x.getInsets();
        Dimension size1 = wire.getPreferredSize();
        wire.setBounds(25+insets1.left, 5+insets1.top,size1.width,size1.height);

        int x1 = object1.getLabel().getX();
        int x2 = object2.getLabel().getX();
        int y1 = object1.getLabel().getY();
        int y2 = object2.getLabel().getY();

        int newWidth = (int)Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1,2));
        int newHeight = wire.getHeight();
        // creates a physical wire from one component to another; 
        try {
        BufferedImage bi = ImageIO.read(new File("assets/redWire.png"));
        System.out.println(bi.toString());
        BufferedImage stretechedImage = new BufferedImage(newWidth, newHeight*3, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = stretechedImage.createGraphics();
        double angle = Math.atan2(y2-y1, x2-x1);
        System.out.println(angle);
        AffineTransform transform = new AffineTransform();
        transform.translate(newWidth / 2.0, newHeight );
        transform.rotate(angle);
        transform.scale((double) newWidth / bi.getWidth(), 0.078f);
        transform.translate(-bi.getWidth() / 2.0, -bi.getHeight() );

        g2d.transform(transform);
        g2d.drawImage(bi, 0, 0, null);
        System.out.println(g2d.toString());
        g2d.dispose();
        
        ImageIO.write(stretechedImage, "png", new File("assets/transformedFile"+Level2.numOfWires+".png"));


    } catch (Exception e) {
        JOptionPane.showMessageDialog(wire, "Failed to make a wire image " + e + " " );
    }
    }

    public JLabel getWire(){
        return wire; 
    }
}
