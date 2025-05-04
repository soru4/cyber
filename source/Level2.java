import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.*;
import javax.swing.*;

public class Level2 implements ActionListener, MouseListener, MouseMotionListener {

    public static JFrame frame;
    Point startPoint;
    private java.util.Stack<Connection> wires = new Stack<Connection>();
    //Replace components with arraylist from level1
    private ArrayList<JLabel> components = new ArrayList<JLabel>();
    ArrayList<ComputerComponent> pulledCart;
    public ArrayList<ComponentHolder> holders = new ArrayList<ComponentHolder>();
    public Queue<ComponentHolder> recentlyClicked = new LinkedList<ComponentHolder>();
    public static ComputerComponent  world;
    public boolean isPlaceMode = false; 
    public boolean isClicking = false;
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

        JPanel panel2 = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.clearRect(0, 0, getWidth(),getHeight());
                
          
                for (Connection wire : wires) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.RED);
                    g2d.setStroke(new BasicStroke(5));
                    //make it so that when object one and object two are moved, the line moves with it.
                    g2d.setColor(Color.RED); // change this line below to change positioning.
                    // if(!isClicking){
                    //     Line2D line = new Line2D.Double(wire.object1.getLabel().getX() + wire.object1.getLabel().getWidth()/2, wire.object1.getLabel().getY() + wire.object1.getLabel().getHeight()/2, wire.object2.getLabel().getX() + wire.object2.getLabel().getWidth()/2, wire.object2.getLabel().getY() + wire.object2.getLabel().getHeight()/2);
                    //     wire.wire = line;
                    // }
                    g2d.draw(wire.wire);
                    //g2d.draw(wire.wire);
                }
            }
        };
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
                if(s.component.type.equals("Computer")){
                    
                   
                    if(s.component.isSouthOf(world)){
                        computer++;
                    }
                }
            }
        
            if(world.CheckConnection() && computer>=Level1.build.getWorkforceSize()){
                JOptionPane.showMessageDialog(frame, "All connections in your world are valid!!");
                for(int i = 0; i <=numOfWires; i++){
                    File myObj = new File("assets/transformedFile"+i+".png"); 
                    if (myObj.delete()) { 
                      System.out.println("Deleted the file: " + myObj.getName());
                    } else {
                      System.out.println("Failed to delete the file.");
                    } 
                }
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
            panel2.setBackground(isPlaceMode ? Color.GREEN : Color.GRAY);
        });
        panel.add(button4);
        panel.add(button5);

        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
        button2.addActionListener((ActionEvent e) -> { 

            if(recentlyClicked.size()==2){
                for(ComponentHolder y: recentlyClicked){
                
                    y.label.setBackground(null);
                    y.label.setOpaque(false);
                }
                ComponentHolder recentlyClicked1 = recentlyClicked.poll(); 
                ComponentHolder recentlyClicked2 = recentlyClicked.poll();
               
                ComputerComponent obj1 = recentlyClicked1.component;
                ComputerComponent obj2 = recentlyClicked2.component;
                //connects first component to second. 
                if(obj1.addConnection(obj2, frame)){
                    Connection x = new Connection(recentlyClicked1, recentlyClicked2);
        
                    x.realizeConnection(panel2);
                    wires.add(x);
                   
                    recentlyClicked.clear();
                    //frame.add(x.getWire());
                    panel.revalidate();
                    panel.repaint();
                }
                
                
            }else{
                JOptionPane.showMessageDialog(frame, "Can't create a connection with current selection. ");
            }
        });

        button3.addActionListener((ActionEvent e) -> { // removes the mostly recent wire connection
            if(wires.size() > 0){
                Connection wire = wires.pop();

                wire.object1.component.removeConnection(wire.object2.component);
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
        world.setIP("192.168.10.1/32");
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
        isClicking = true;
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
            } // make component in recently clicked have a background
          
                for(ComponentHolder y: recentlyClicked){
                 
                        y.label.setBackground(Color.YELLOW);
                        y.label.setOpaque(true);
                    
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
            for(ComponentHolder y: recentlyClicked){
                
                    y.label.setBackground(null);
                    y.label.setOpaque(false);
                }
                recentlyClicked.clear();
            }
         
        }

    

      

    

    private JPopupMenu createPopupMenu(ComputerComponent x) {
        
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem item2 = new JMenuItem(x.type);
        JMenuItem item3 = new JMenuItem("IP: " + x.ip);
        JMenuItem item1 = new JMenuItem("Current Connections Number: " + x.conn.size());
        popupMenu.add(item2);
        popupMenu.add(item3);
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
        isClicking = false;
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
    public  Line2D wire; 

    public Connection(ComponentHolder o1, ComponentHolder o2){
        object1 = o1;
        object2 = o2; 
    }

    public void realizeConnection(JPanel x){
        Graphics2D g2d = (Graphics2D) x.getGraphics();
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.RED); // change this line below to change positioning. 
        Line2D line = new Line2D.Double(object1.getLabel().getX() + object1.getLabel().getWidth()/2, object1.getLabel().getY() + object1.getLabel().getHeight()/2, object2.getLabel().getX() + object2.getLabel().getWidth()/2, object2.getLabel().getY() + object2.getLabel().getHeight()/2);
        wire = line;
        //System.out.println("line" + line.toString());
        g2d.draw(line);
        x.repaint();
    }

    public Line2D getWire(){
        return wire; 
    }
}
