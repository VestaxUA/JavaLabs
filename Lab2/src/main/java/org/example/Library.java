package org.example;

import java.util.ArrayList;
import java.util.List;
class Library implements IManageable {
    List<Item> items;
    List<Patron> patrons;

    Library() {
        this.items = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    void registerPatron(Patron patron) {
        patrons.add(patron);
    }

    void lendItem(Patron patron, Item item) {
        if (item.isBorrowed()) {
            patron.borrow(item);
            item.borrowItem();
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    void returnItem(Patron patron, Item item) {
        if (patron.getBorrowedItems().contains(item)) {
            patron.returnItem(item);
            item.returnItem();
        } else {
            System.out.println("Patron did not borrow this item.");
        }

    }

    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public void remove(Item item) {
        items.remove(item);
    }

    @Override
    public void listAvailable() {
        System.out.println("Available Items:");
        for (Item item : items) {
            if (item.isBorrowed()) {
                System.out.println(item.getTitle());
            }
        }
    }

    @Override
    public void listBorrowed() {
        System.out.println("Borrowed Items:");
        for (Patron patron : patrons) {
            for (Item item : patron.getBorrowedItems()) {
                System.out.println(patron.getName() + " - " + item.getTitle());
            }
        }
    }
}
