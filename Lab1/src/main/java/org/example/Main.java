package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Book book1 = new Book("The Hunger Games", "Suzanne Collins", "978-1-234567-89-0", 2008);
        Book book2 = new Book("Brave New World", "Aldous Huxley", "978-1-234567-90-6", 1932);
        Book book3 = new Book("The Road", "Cormac McCarthy", "978-1-234567-91-3", 2006);
        Book book4 = new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "978-1-234567-92-0", 2005);
        Book book5 = new Book("Frankenstein", "Mary Shelley", "978-1-234567-93-7", 1818);
        Book book6 = new Book("The Alchemist", "Paulo Coelho", "978-1-234567-94-4", 1988);
        Book book7 = new Book("The Handmaid's Tale", "Margaret Atwood", "978-1-234567-95-1", 1985);

        Library library = new Library();

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);

        //Display
        System.out.println("Display all books in library:\n");
        library.displayBook();

        //Search by book title
        String searchTitle = "The Handmaid's Tale";

        if (library.hasBookWithTitle(searchTitle)) {
            System.out.println("\nSearching by book title '" + searchTitle + "':");
            List<Book> searchResult = library.searchByTitle(searchTitle);
            for (Book book : searchResult) {
                System.out.println("\nTitle: " + book.getTitle() + "\nAuthor: " + book.getAuthor() +
                        "\nISBN Number: " + book.getIsbnNum() + "\nYear of Publication: " + book.getPublicationDate() + "\n");
            }
        } else {
            System.out.println("\nBook by title '" + searchTitle + "' not in the library.");
        }

        // Delete by ISBN
        String isbnToDelete = "978-1-234567-93-7";

        if (library.removeBookByISBN(isbnToDelete)) {
            System.out.println("\nDelete by ISBN number '" + isbnToDelete + "': \nBook removed successfully.");
        } else {
            System.out.println("Book with this ISBN '" + isbnToDelete + "' not in the library .");
        }

        // Updated book list
        System.out.println("\nUpdated book list:");
        library.displayBook();

    }
}