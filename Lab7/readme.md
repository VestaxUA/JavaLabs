
![Snowy Day](./winter.jpg)

# Лабораторна робота 7
## виконав : студент групи ПД-32 Ткачищен Валентин
***
### План роботи:

1. Проектування Класів
   - Створення класів Product, User, та Order з необхідними полями та методами.
   - Реалізація методів для керування товарами у кошику користувача та розрахунку вартості замовлення.
2. Платформа Електронної Комерції
   - Створення класу ECommercePlatform для управління користувачами, товарами та замовленнями.
   - Реалізація методів для додавання та оновлення об'єктів.
3. ECommerceDemo
   - Ініціалізація та додавання користувачів та товарів.
   - Симуляція взаємодії користувачів із кошиком та обробка замовлень.
4. Розширені Функції
   - Реалізація сортування та фільтрації товарів.
   - Додавання рекомендацій користувачам на основі історії замовлень.
5. Симуляція
   - Виконання симуляції взаємодій, оновлення кошиків, обробка замовлень.
   - Відображення кінцевого стану користувачів, товарів та замовлень.
***
### Хід розробки:

Вигляд класу `Product`
```java
package org.example;

import java.util.Comparator;

public class Product implements Comparable<Product> {
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    public static class NameComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.name.compareTo(p2.name);
        }
    }

    public static class StockComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return Integer.compare(p1.stock, p2.stock);
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private Integer id;
    private String name;
    private double price;
    private int stock;

    public Product(Integer id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", stock=" + stock + "}";
    }
}

```
Вигляд класу `Order`
```java
package org.example;

import java.util.Map;
public class Order {
    private Integer id;
    private Integer userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Map<Product, Integer> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<Product, Integer> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order(Integer id, Integer userId, Map<Product, Integer> orderDetails) {
        this.id = id;
        this.userId = userId;
        this.orderDetails = orderDetails;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : orderDetails.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
}
```
Вигляд класу `User`
```java
package org.example;

import java.util.HashMap;
import java.util.Map;

public class User {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    private Integer id;
    private String username;
    private Map<Product, Integer> cart;


    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
        this.cart = new HashMap<>();
    }

    public void addToCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
    }

    public void removeFromCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            if (currentQuantity <= quantity) {
                cart.remove(product);
            } else {
                cart.put(product, currentQuantity - quantity);
            }
        }
    }

    public void modifyCart(Product product, int newQuantity) {
        if (cart.containsKey(product)) {
            if (newQuantity > 0) {
                cart.put(product, newQuantity);
            } else {
                cart.remove(product);
            }
        }
    }
}
```
Вигляд класу `ECommercePlatform`
```java
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
```
Вигляд класу `ECommerceDemo`
```java
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
```
Тестові класи :
- `UserTest` :
```java
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
```
- `ECommercePlatformTest` :
```java
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
```

Тестування показало, що все працює належним чином.

pom.xml буде знаходитись в основній теці лабораторної роботи