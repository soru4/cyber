public class ComputerComponent {
    public int price;
    public String type;

    public ComputerComponent(int price, String type) {
        this.price = price;
        this.type = type;
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
}
