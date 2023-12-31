package org.example;

import java.util.ArrayList;
import java.util.List;
abstract class Item {
    String title;
    String uniqueID;
    public boolean isBorrowed;

    Item(String title, String uniqueID) {
        this.title = title;
        this.uniqueID = uniqueID;
        this.isBorrowed = false;
    }
    public boolean isBorrowed() {
        return !isBorrowed;
    }

    public String getTitle() {
        return title;
    }
    public String getUniqueID() {
        return uniqueID;
    }
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    abstract void borrowItem();

    abstract void returnItem();

}
