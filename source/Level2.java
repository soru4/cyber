import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Level2 implements ActionListener, MouseListener, MouseMotionListener {

    public JFrame frame;
    Point startPoint;
    private java.util.Stack<JLabel> wires = new Stack<JLabel>();
    //Replace components with arraylist from level1
    private ArrayList<JLabel> components = new ArrayList<JLabel>();
    ArrayList<ComputerComponent> pulledCart;
    public ArrayList<ComponentHolder> holders = new ArrayList<ComponentHolder>();
    public Queue<ComponentHolder> recentlyClicked = new LinkedList<ComponentHolder>();

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
        panel2.setLayout(new FlowLayout());

        panel2.setBackground(Color.GRAY);
        frame.add(panel2, BorderLayout.CENTER);

        populateComponentList(pulledCart, panel2);

        panel.setBackground(Color.YELLOW);
        
        Button button2 = new Button("Add New Wire");
        panel.add(button2);

        Button button3 = new Button("Remove Last Wire");
        panel.add(button3);

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
               

                ComputerComponent obj1 = recentlyClicked.poll().component;
                ComputerComponent obj2 = recentlyClicked.poll().component;
                //connects first component to second. 
                obj1.addConnection(obj2, frame);
                recentlyClicked.clear();
                
            }else{
                JOptionPane.showMessageDialog(frame, "Can't create a connection with current selection. ");
            }
        });

        button3.addActionListener((ActionEvent e) -> { // removes the mostly recent wire connection
            if(wires.size() > 0){
                JLabel instanceLabel = wires.pop();
                panel2.remove(instanceLabel);
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

}
