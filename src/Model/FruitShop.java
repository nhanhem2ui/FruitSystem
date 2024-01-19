package Model;

import Controller.FruitShopSystem;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 * FPT don't like hashMap, neither do i like a bunch of warning
 *
 * @author Mazl
 */
public class FruitShop {

    private ArrayList<Fruit> fruits = new ArrayList<>();
    private Hashtable<String, ArrayList<Fruit>> orders = new Hashtable<>();
    private ArrayList<Fruit> fruitListForShopping = new ArrayList<>();

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }

    public Hashtable<String, ArrayList<Fruit>> getOrders() {
        return orders;
    }

    public void setOrders(Hashtable<String, ArrayList<Fruit>> orders) {
        this.orders = orders;
    }

    public ArrayList<Fruit> getFruitListForShopping() {
        return fruitListForShopping;
    }

    public void setFruitListForShopping(ArrayList<Fruit> fruitListForShopping) {
        this.fruitListForShopping = fruitListForShopping;
    }

    public void createFruit(int fruitId, String fruitName, int price, int quantity, String origin) {
        Fruit fruit = new Fruit(fruitId, fruitName, price, quantity, origin);
        fruits.add(fruit);
    }

    public void createFruit(Fruit fruit) {
        fruits.add(fruit);
    }

    public void viewOrders(String customerName) {
        if (orders.containsKey(customerName)) {
            System.out.println("Customer: " + customerName);
            System.out.println("Product\t\tQuantity\tPrice\t\tAmount");
            for (Fruit fruit : orders.get(customerName)) {
                System.out.println(fruit);
            }
            System.out.println("Total: " + calculateTotal(orders.get(customerName)) + "$\n");
        } else {
            System.out.println("No orders found for customer: " + customerName);
        }
    }

    public double calculateTotal(ArrayList<Fruit> order) {
        double total = 0;
        for (Fruit fruit : order) {
            total += fruit.getAmount();
        }
        return total;
    }

    public void displayFruits() {
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        int item = 1;
        for (Fruit fruit : fruits) {
            System.out.printf("\t%-10d%-20s%-15s%-15s%n", item++, fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice() + "$");
        }
    }

    public void shopping() {
        displayFruits();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Choose item: ");
            int selectedItem = sc.nextInt();

            Fruit selectedFruit = getFruits().get(selectedItem - 1);

            System.out.println("Enter quantity: ");
            int quantity = sc.nextInt();

            if (quantity > selectedFruit.getQuantity()) {
                System.out.println("Number exceeds max quantity..");
                return;
            }

            System.out.println("Do you want to create order now (Y/N): ");
            char createOrder = sc.next().charAt(0);

            if (createOrder == 'Y' || createOrder == 'y') {
                boolean found = false;

                for (Fruit existingFruit : fruitListForShopping) {
                    if (existingFruit.getFruitId() == selectedFruit.getFruitId()) {

                        // Update the quantity if the same item is found
                        existingFruit.setQuantity(existingFruit.getQuantity() + quantity);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    Fruit newFruit = new Fruit(selectedFruit.getFruitId(), selectedFruit.getFruitName(),
                            selectedFruit.getPrice(), quantity, selectedFruit.getOrigin());
                    fruitListForShopping.add(newFruit);
                }

                selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);

                System.out.println("Product\t\tQuantity\tPrice\t\tAmount");
                for (Fruit fruit : fruitListForShopping) {
                    System.out.println(fruit);
                }

                System.out.println("Total: " + calculateTotal(fruitListForShopping) + "$");

                System.out.print("Input your name: ");
                String customerName = sc.next();

                orders.put(customerName, new ArrayList<>(fruitListForShopping));
                fruitListForShopping.clear();
                break;

            } else {
                Fruit newFruit = new Fruit(selectedFruit.getFruitId(), selectedFruit.getFruitName(),
                        selectedFruit.getPrice(), quantity, selectedFruit.getOrigin());
                fruitListForShopping.add(newFruit);
                selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);
                displayFruits();
            }
        }
    }

}
