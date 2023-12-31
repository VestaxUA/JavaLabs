package org.example;

import java.util.HashMap;
import java.util.Map;
public class ECommercePlatform {
    private final Map<Integer, User> users;
    private final Map<Integer, Product> products;
    private final Map<Integer, Order> orders;

    public ECommercePlatform() {
        this.users = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void createOrder(Integer userId, Map<Product, Integer> orderDetails) {
        User user = users.get(userId);
        if (user != null) {
            Order order = new Order(generateOrderId(), userId, orderDetails);
            orders.put(order.getId(), order);
            updateProductStocks(orderDetails);
        } else {
            System.out.println("User with ID " + userId + " is not found.");
        }
    }

    public Map<Integer, Product> getAvailableProducts() {
        return products;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    private void updateProductStocks(Map<Product, Integer> orderDetails) {
        for (Map.Entry<Product, Integer> entry : orderDetails.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            int currentStock = product.getStock();
            if (currentStock >= quantity) {
                product.setStock(currentStock - quantity);
            } else {
                System.out.println("Not enough products \"" + product.getName() + "\" on stock.");
            }
        }
    }

    private int generateOrderId() {
        return orders.size() + 1;
    }
}
