package org.example;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Crime and Punishment", "B001", "Fyodor Dostoevsky");
        Book book2 = new Book("The Lord of the Rings", "B002", "J.R.R. Tolkien");
        Book book3 = new Book("1984", "B003", "George Orwell");
        Book book4 = new Book("The Catcher in the Rye", "B004", "J.D. Salinger");
        Book book5 = new Book("One Hundred Years of Solitude", "B005", "Gabriel Garcia Marquez");

        DVD dvd1 = new DVD("The Matrix", "D001", 136);
        DVD dvd2 = new DVD("The Dark Knight", "D002", 152);
        DVD dvd3 = new DVD("Pulp Fiction", "D003", 154);

        library.add(book1);
        library.add(book2);
        library.add(book3);
        library.add(book4);
        library.add(book5);
        library.add(dvd1);
        library.add(dvd2);
        library.add(dvd3);

        Patron patron1 = new Patron("Eleanor", "P001");
        Patron patron2 = new Patron("Victor", "P002");
        Patron patron3 = new Patron("Isabella", "P003");
        Patron patron4 = new Patron("Henry", "P004");

        library.registerPatron(patron1);
        library.registerPatron(patron2);
        library.registerPatron(patron3);
        library.registerPatron(patron4);

        library.lendItem(patron1, book1);
        library.lendItem(patron2, dvd2);
        library.lendItem(patron3, book4);
        library.lendItem(patron4, dvd3);

        library.returnItem(patron2, dvd2);

        library.listAvailable();
        library.listBorrowed();
    }
}