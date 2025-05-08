package source;


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
    public String ip;

    public ComputerComponent(int price, String type, int ports, String icon) {
        this.price = price;
        this.type = type;
        this.ports = ports;
        this.conn = new ArrayList<ComputerComponent>(ports);
        this.icon = new ImageIcon(icon);
        this.ip = "0.0.0.0";
    }

    public ComputerComponent(int price, String type, int ports) {
        this.price = price;
        this.type = type;
        this.ports = ports;
        this.conn = new ArrayList<ComputerComponent>(ports);
        this.icon = null;
        this.ip = "0.0.0.0";
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

            if (component.isSouthOf(this)) {
                System.out.println("The component is south of the reference component.");
                JOptionPane.showMessageDialog(frame,
                        "The component is south of the reference component.");
                component.setIP(getIP() + "/" + ((int) ((Math.random() * 15) + 1)));
            }
            conn.add(component);

            System.out.println("The component is south of the reference component.");
           
            component.setIP(getIP() + "/" + ((int) ((Math.random() * 18) + 1)));

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
        this.ip = "0.0.0.0";
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

    public boolean isSouthOf(ComputerComponent component) {
        // if this componet is connected to the reference component, then it is south of
        // the reference component.
        if (component.conn.isEmpty()) {
            return false;
        }
        for (ComputerComponent c : component.conn) {
            if (c.equals(this)) {
                return true;
            }
        }
        for (ComputerComponent c : component.conn) {
            return isSouthOf(c);
        }
        return false;
    }


    public boolean isTypeSouthOf(ComputerComponent startingComponent, String type) {
        // if this componet is connected to the reference component, then it is south of
        // the reference component.
        if (startingComponent.conn.isEmpty()) {
            return false;
        }
        for (ComputerComponent c : startingComponent.conn) {
            if (c.getType().equals(type)) {
                return true;
            }
        }
        for (ComputerComponent c : startingComponent.conn) {
            return isTypeSouthOf(c,type);
        }
        return false;
    }

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

    public void setIP(String i) {
        if (!i.contains("/")) {
            i += "/32";
        }
        String[] ip = i.substring(0, i.indexOf("/")).split("\\.");
        int range = Integer.parseInt(i.substring(i.indexOf("/") + 1));
        range = 12;
        int[] prefix = new int[4];
        for (int j = 0; j < ip.length; j++) {
            int q = Integer.parseInt(ip[j]);
            if (q < 0 || q > 255) {
                JOptionPane.showMessageDialog(null, "Invalid IP address. Please enter a valid IP address.");
                return;
            }
            prefix[j] = q;
        }

        if (range < 0 || range > 32) {
            JOptionPane.showMessageDialog(null, "Invalid CIDR range. Please enter a valid CIDR range.");
            return;
        }
        this.ip = generateRandomIP(prefix, range);
    }

    public String generateRandomIP(int[] startingBits, int range) {
        int[] ip = new int[4];
        // Copy starting bits
        for (int i = 0; i < startingBits.length; i++) {
            ip[i] = startingBits[i];
        }
        // Fill remaining bits randomly, but keep within the subnet
        int bitsToRandomize = 32 - range;
        int randomPart = (int) (Math.random() * (1 << bitsToRandomize));
        int ipInt = 0;
        // Build the base IP as int
        for (int i = 0; i < 4; i++) {
            ipInt |= (i < startingBits.length ? startingBits[i] : 0) << (24 - 8 * i);
        }
        // Add the random part
        ipInt |= randomPart;
        // Convert back to octets
        for (int i = 0; i < 4; i++) {
            ip[i] = (ipInt >> (24 - 8 * i)) & 0xFF;
        }
        return ip[0] + "." + ip[1] + "." + ip[2] + "." + ip[3];
    }

    public String getIP() {
        return ip;
    }
}
