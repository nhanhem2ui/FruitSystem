package Model;

/**
 *
 * @author Mazl
 */
public class Fruit {

    private int fruitId;
    private String fruitName;
    private int price;
    private int quantity;
    private String origin;

    public Fruit(int fruitId, String fruitName, int price, int quantity, String origin) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public int getFruitId() {
        return fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public int getAmount(){
        return price*quantity;
    }
    @Override
    public String toString() {
        return String.format("%-15s%-15d%-15d%-15d", fruitName, quantity, price, getAmount());
    }
}
