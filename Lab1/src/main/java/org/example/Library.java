package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library () {
        this.books = new ArrayList<>();
    }

    public void addBook (Book book) {
        books.add(book);
    }

    public void displayBook () {
        for (Book book : books) {
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("Author Name: " + book.getAuthor());
            System.out.println("ISNB Number: " + book.getIsbnNum());
            System.out.println("Date of Publication: " + book.getPublicationDate() + "\n");
        }
    }

    public List<Book> searchByTitle (String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean hasBookWithTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeBookByISBN (String isbnNum) {
        for (Book book : books) {
            if (book.getIsbnNum().equals(isbnNum)) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }
}
