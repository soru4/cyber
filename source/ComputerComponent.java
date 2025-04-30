import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ComputerComponent {
    public int price;
    public String type;
    public int ports;
    public ArrayList<ComputerComponent> conn;
    public Icon icon;

    public ComputerComponent(int price, String type, int ports, String icon) {
        this.price = price;
        this.type = type;
        this.ports = ports;
        this.conn = new ArrayList<ComputerComponent>(ports);
        this.icon = new ImageIcon(icon);
    }

    public ComputerComponent(int price, String type, int ports) {
        this.price = price;
        this.type = type;
        this.ports = ports;
        this.conn = new ArrayList<ComputerComponent>(ports);
        this.icon = null;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public Icon getIcon() {
        return icon;
    }

    public String toString() {
        return type + " ($" + price + ", " + ports + "ports)";
    }

    public boolean addConnection(ComputerComponent component, JFrame frame) {
        if (conn.size() < ports) {
            conn.add(component);
            JOptionPane.showMessageDialog(frame,
                    "Successful Connection between " + this.type + " and " + component.type);
            return true;
        } else {
            JOptionPane.showMessageDialog(frame,
                    "The " + type + " component you are trying to connect already has too many connections. ");
            return false;
        }

    }

    public void removeConnection(ComputerComponent component) {
        conn.remove(component);
    }

    public boolean CheckConnection() {
        if (type.equals("World")) {
            return isValidConnection(this);
        }
        return false;
    }

    /*
     * acceptable connection types
     * 1. World to Router
     * 2. Router to Switch
     * 3. Switch to Computer
     * 4. Switch to Server
     * 5. Server to Switch
     * 6. Server to Router
     * 7. Router to Server
     * 
     */
    // recursive method to see if the component have the acceptable connection
    // types.
    public boolean isValidConnection(ComputerComponent component) {

        /*
        * 
        */
        if (component.conn.isEmpty()) {
            return true;
        }
        for (ComputerComponent c : component.conn) {
            switch (component.type) {
                case "World":
                    if (c.type.equals("Router") || c.type.equals("Switch")) {
                        return true && isValidConnection(c);
                    }

                    return false;
                case "Router":
                    if (c.type.equals("Switch") || c.type.equals("Server")) {
                        return true && isValidConnection(c);
                    }

                    return false;
                case "Switch":
                    if (c.type.equals("Computer") || c.type.equals("Server")) {
                        return true && isValidConnection(c);
                    }

                    return false;
                case "Server":
                    if (c.type.equals("Switch") || c.type.equals("Router") || c.type.equals("Computer")) {
                        return true && isValidConnection(c);
                    }

                    return false;
                default:
                    return false;
            }
        }
        return false;
    }

}
