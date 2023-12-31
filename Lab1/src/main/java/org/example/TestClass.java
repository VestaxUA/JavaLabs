package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestClass {

    @Test
    public void testAddBook() {
        Library library = new Library();
        Book book1 = new Book("Book 1", "Author 1", "12345", 2000);

        library.addBook(book1);

        assertTrue(library.hasBookWithTitle("Book 1"));
    }

    @Test
    public void testSearchByTitle() {
        Library library = new Library();
        Book book1 = new Book("Book 1", "Author 1", "12345", 2000);
        Book book2 = new Book("Book 2", "Author 2", "67890", 2005);

        library.addBook(book1);
        library.addBook(book2);

        assertEquals(1, library.searchByTitle("Book 1").size());
        assertEquals(0, library.searchByTitle("Book 3").size());
    }

    @Test
    public void testRemoveBookByISBN() {
        Library library = new Library();
        Book book1 = new Book("Book 1", "Author 1", "12345", 2000);

        library.addBook(book1);

        assertTrue(library.removeBookByISBN("12345"));
        assertFalse(library.hasBookWithTitle("Book 1"));
    }

    @Test
    public void testHasBookWithTitle() {
        Library library = new Library();
        Book book1 = new Book("Book 1", "Author 1", "12345", 2000);

        library.addBook(book1);

        assertTrue(library.hasBookWithTitle("Book 1"));
        assertFalse(library.hasBookWithTitle("Book 2"));
    }
}
