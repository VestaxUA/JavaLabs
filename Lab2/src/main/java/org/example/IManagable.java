package org.example;

interface IManageable {
    void add(Item item);

    void remove(Item item);

    void listAvailable();

    void listBorrowed();
}
