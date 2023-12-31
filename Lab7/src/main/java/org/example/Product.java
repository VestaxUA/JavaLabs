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
