package Controller;

import Model.Fruit;
import View.Menu;
import java.util.Scanner;
import Model.FruitShop;
import java.util.ArrayList;

/**
 *
 * @author Mazl
 */
public class FruitShopSystem {

    Scanner sc;
    FruitShop shop;
    ArrayList<Fruit> fruitListForShopping;

    public FruitShopSystem() {
        sc = new Scanner(System.in);
        shop = new FruitShop();
        fruitListForShopping = new ArrayList<>();
    }

    public void displayMainMenu() {
        Menu<String> mainMenu = new Menu<>("FRUIT SHOP SYSTEM", new String[]{
            "Create Fruit",
            "View orders",
            "Shopping (for buyer)",
            "Exit"
        }) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 ->
                        createFruit();
                    case 2 ->
                        viewOrder();
                    case 3 ->
                        shop.shopping();
                    case 4 ->
                        System.exit(0);
                    default ->
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        };
        shop.createFruit(1, "notEdible", 10, 10, "VietNam");
        shop.createFruit(2, "Maokai", 20, 50, "Runttera");
        mainMenu.run();
    }

    void createFruit() {
        while (true) {
            System.out.println("Enter fruit Id: ");
            int id = sc.nextInt();
            System.out.println("Enter fruit name: ");
            String name = sc.next();
            System.out.println("Enter fruit price: ");
            int price = sc.nextInt();
            System.out.println("Enter fruit quantity: ");
            int quantity = sc.nextInt();
            System.out.println("Enter fruit origin: ");
            String origin = sc.next();
            Fruit newFruit = new Fruit(id, name, price, quantity, origin);
            shop.createFruit(newFruit);
            System.out.println("Do you want to continue (Y/N): ");
            char next = sc.next().charAt(0);
            if (next == 'N' || next == 'n') {
                break;
            }
        }
    }

    public void viewOrder() {
        System.out.print("Enter customer name: ");
        String customerToView = sc.next();
        shop.viewOrders(customerToView);
    }

    public static void main(String[] args) {
        FruitShopSystem main = new FruitShopSystem();
        main.displayMainMenu();
    }
}
