import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ComputerComponent {
    public int price;
    public String type;
    public int ports;
    public ArrayList<ComputerComponent> conn;

    public ComputerComponent(int price, String type, int ports) {
        this.price = price;
        this.type = type;
        this.ports = ports;
        this.conn = new ArrayList<ComputerComponent>(ports);
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type + " ($" + price + ", " + ports + "ports)";
    }

    public boolean  addConnection(ComputerComponent component, JFrame frame) {
        if (conn.size() < ports) {
            conn.add(component);
            JOptionPane.showMessageDialog(frame, "Successful Connection between " + this.type + " and " + component.type);
            return  true;
        } else {
            JOptionPane.showMessageDialog(frame, "The " + type +  " component you are trying to connect already has too many connections. ");
            return false; 
        }
       
    }

    public void removeConnection(ComputerComponent component) {
        conn.remove(component);
    }

    public void CheckConnection(){
        if(type.equals("World")){
            
        }
    }
}
