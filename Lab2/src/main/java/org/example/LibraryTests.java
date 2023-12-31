package org.example;

import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class LibraryTests {
   private Library library;
    private Book book1;
    private Book book2;
    private Book book3;
    private DVD dvd1;
    private DVD dvd2;
    private DVD dvd3;
    private Patron patron1;
    private Patron patron2;
    private Patron patron3;
    private Patron patron4;

    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    void setUp() {
        library = new Library();

        book1 = new Book("Crime and Punishment", "B001", "Fyodor Dostoevsky");
        book2 = new Book("The Lord of the Rings", "B002", "J.R.R. Tolkien");
        book3 = new Book("1984", "B003", "George Orwell");
        DVD dvd1 = new DVD("The Matrix", "D001", 136);
        dvd2 = new DVD("The Dark Knight", "D002", 152);
        dvd3 = new DVD("Pulp Fiction", "D003", 154);

        patron1 = new Patron("Eleanor", "P001");
        patron2 = new Patron("Victor", "P002");
        patron3 = new Patron("Isabella", "P003");
        patron4 = new Patron("Henry", "P004");

        library.add(book1);
        library.add(book2);
        library.add(book3);
        library.add(dvd1);
        library.add(dvd2);
        library.add(dvd3);

        library.registerPatron(patron1);
        library.registerPatron(patron2);
        library.registerPatron(patron3);
        library.registerPatron(patron4);
    }

    @Test
    void testBorrowAndReturn() {
        assertFalse(book1.isBorrowed());
        library.lendItem(patron1, book1);
        assertTrue(book1.isBorrowed());
        assertTrue(patron1.getBorrowedItems().contains(book1));
        library.returnItem(patron1, book1);
        assertFalse(book1.isBorrowed());
        assertFalse(patron1.getBorrowedItems().contains(book1));
    }

    @Test
    void testLendUnavailableItem() {
        library.lendItem(patron1, book2);
        assertTrue(book2.isBorrowed());
        library.lendItem(patron2, book2);
        assertTrue(book2.isBorrowed()); // should remain borrowed
    }

    @Test
    void testReturnUnborrowedItem() {
        library.returnItem(patron1, book3);
        assertFalse(book3.isBorrowed()); // should remain not borrowed
    }

    @Test
    void testListAvailableAndBorrowed() {
        library.lendItem(patron1, dvd2);

        // Test listAvailable
        library.listAvailable(); // Ensure it doesn't throw exceptions

        // Test listBorrowed
        library.listBorrowed(); // Ensure it doesn't throw exceptions
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test");
    }

    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("After all tests");
    }
}
