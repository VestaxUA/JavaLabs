package org.example;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addToCart() {
        User user = new User(1, "john_doe");
        Product product = new Product(1, "Laptop", 999.99, 10);

        user.addToCart(product, 2);

        Map<Product, Integer> cart = user.getCart();
        assertTrue(cart.containsKey(product));
        assertEquals(2, cart.get(product));

        user.addToCart(product, 3);

        assertEquals(5, cart.get(product));
    }

    @Test
    void removeFromCart() {
        User user = new User(1, "john_doe");
        Product product = new Product(1, "Laptop", 999.99, 10);

        user.addToCart(product, 5);

        user.removeFromCart(product, 2);

        Map<Product, Integer> cart = user.getCart();
        assertEquals(3, cart.get(product));

        user.removeFromCart(product, 3);

        assertFalse(cart.containsKey(product));
    }

    @Test
    void modifyCart() {
        User user = new User(1, "john_doe");
        Product product = new Product(1, "Laptop", 999.99, 10);

        user.addToCart(product, 3);

        user.modifyCart(product, 5);

        Map<Product, Integer> cart = user.getCart();
        assertEquals(5, cart.get(product));

        user.modifyCart(product, 0);

        assertFalse(cart.containsKey(product));

        user.modifyCart(product, 3);
        assertFalse(cart.containsKey(product));
    }
}