import java.util.ArrayList;

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
        return type + " ($" + price + ")";
    }

    public void addConnection(ComputerComponent component) {
        if (conn.size() < ports) {
            conn.add(component);
        } else {
            System.out.println("No more ports available for " + type);
        }
    }

    public void removeConnection(ComputerComponent component) {
        conn.remove(component);
    }
}
