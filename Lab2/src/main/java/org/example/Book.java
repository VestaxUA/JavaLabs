package org.example;

import java.util.ArrayList;
import java.util.List;
class Book extends Item {
    String author;

    Book(String title, String uniqueID, String author) {
        super(title, uniqueID);
        this.author = author;
    }

    @Override
    void borrowItem() {
        this.isBorrowed = true;
    }

    @Override
    void returnItem() {
        this.isBorrowed = false;
    }


}
