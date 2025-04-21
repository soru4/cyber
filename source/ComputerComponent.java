public class ComputerComponent {
    public int id;
    public double price;
    public String type;

    public ComputerComponent(double price, int id, String type) {
        this.price = price;
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
