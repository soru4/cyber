import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

class movableComponent extends MonoBehaviour{
    public JLabel label; 
    public Point currentPos; 
    public movableComponent(ImageIcon image, Point x){
        //this.win.panel.setLayout(null);
        label = new JLabel(image);
        this.win.panel.add(label);
        label.setLocation(new Point(0, 0));
        currentPos = x; 

    }

    public  void Start(){
       this.win.frame.setLayout(null);
     
    }

    public  void Update(){
        System.out.println(MouseInfo.getPointerInfo().getLocation());
        label.setLocation(MouseInfo.getPointerInfo().getLocation());

  
    }
}