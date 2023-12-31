package org.example;

public class Book {

    private String bookTitle;
    private String authorName;
    private String isbnNum;
    private int publicationDate;

    public Book(String bookTitle, String authorName, String isbnNum, int publicationDate) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.isbnNum = isbnNum;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return this.bookTitle;
    }

    public String getAuthor() {
        return this.authorName;
    }

    public String getIsbnNum() {
        return this.isbnNum;
    }

    public int getPublicationDate() {
        return this.publicationDate;
    }
}
