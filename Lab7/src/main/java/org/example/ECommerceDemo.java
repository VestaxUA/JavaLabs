package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ECommerceDemo {
    private static final ECommercePlatform platform = new ECommercePlatform();

    public static void displayProductsSortedByName() {
        List<Product> sortedProducts = new ArrayList<>(platform.getAvailableProducts().values());
        sortedProducts.sort(new Product.NameComparator());

        System.out.println("List of products sorted by name:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    public static void displayProductsSortedByPrice() {
        List<Product> sortedProducts = new ArrayList<>(platform.getAvailableProducts().values());
        Collections.sort(sortedProducts);

        System.out.println("List of products sorted by price:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    public static void displayProductsSortedByStock() {
        List<Product> sortedProducts = new ArrayList<>(platform.getAvailableProducts().values());
        sortedProducts.sort(new Product.StockComparator());

        System.out.println("List of products sorted by stock:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    public static void filterProductsByStock(int minStock) {
        List<Product> filteredProducts = platform.getAvailableProducts().values().stream()
                .filter(product -> product.getStock() >= minStock)
                .toList();

        System.out.println("List of products available in stock more " + minStock + ":");
        for (Product product : filteredProducts) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {

        addUserAndProducts();
        simulateUserInteractions();
        displayFinalState();
    }

    private static void addUserAndProducts() {
        User user1 = new User(1, "rock_star");
        User user2 = new User(2, "john_Snow");
        platform.addUser(user1);
        platform.addUser(user2);

        Product product1 = new Product(1, "Laptop", 999.99, 10);
        Product product2 = new Product(2, "Smartphone", 499.99, 20);
        platform.addProduct(product1);
        platform.addProduct(product2);
    }

    private static void simulateUserInteractions() {
        User user1 = platform.getUsers().get(1);
        user1.addToCart(platform.getAvailableProducts().get(1), 2);
        user1.addToCart(platform.getAvailableProducts().get(2), 1);

        User user2 = platform.getUsers().get(2);
        user2.addToCart(platform.getAvailableProducts().get(1), 1);
        user2.addToCart(platform.getAvailableProducts().get(2), 3);

        platform.createOrder(user1.getId(), user1.getCart());
        platform.createOrder(user2.getId(), user2.getCart());
    }

    private static void displayFinalState() {
        System.out.println("\nEnd state of users:");
        for (User user : platform.getUsers().values()) {
            System.out.println(user);
        }

        System.out.println("\nEnd state of products:");
        for (Product product : platform.getAvailableProducts().values()) {
            System.out.println(product);
        }

        System.out.println("\nEnd state of orders:");
        for (Order order : platform.getOrders().values()) {
            System.out.println(order);
        }
    }
}
