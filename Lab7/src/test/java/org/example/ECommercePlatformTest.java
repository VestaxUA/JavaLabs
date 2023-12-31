package org.example;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ECommercePlatformTest {

    @Test
    void addUser() {
        ECommercePlatform platform = new ECommercePlatform();
        User user = new User(1, "john_doe");

        platform.addUser(user);

        Map<Integer, User> users = platform.getUsers();
        assertTrue(users.containsKey(user.getId()));
        assertEquals(user, users.get(user.getId()));
    }

    @Test
    void addProduct() {
        ECommercePlatform platform = new ECommercePlatform();
        Product product = new Product(1, "Laptop", 999.99, 10);

        platform.addProduct(product);

        Map<Integer, Product> products = platform.getAvailableProducts();
        assertTrue(products.containsKey(product.getId()));
        assertEquals(product, products.get(product.getId()));
    }

    @Test
    void createOrder() {
        ECommercePlatform platform = new ECommercePlatform();
        User user = new User(1, "john_doe");
        platform.addUser(user);

        Product product1 = new Product(1, "Laptop", 999.99, 10);
        Product product2 = new Product(2, "Smartphone", 499.99, 20);
        platform.addProduct(product1);
        platform.addProduct(product2);

        platform.createOrder(user.getId(), Map.of(product1, 2, product2, 1));

        Map<Integer, Order> orders = platform.getOrders();
        assertFalse(orders.isEmpty());

        Map<Integer, Product> availableProducts = platform.getAvailableProducts();
        assertEquals(8, availableProducts.get(product1.getId()).getStock());
        assertEquals(19, availableProducts.get(product2.getId()).getStock());
    }
}