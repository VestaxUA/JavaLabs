
![Autumn Leaves](./autumn.jpg)

# Лабораторна робота 1
## виконав : студент групи ПД-32 Ткачищен Валентин
***
### План роботи:

1. Розробити програму для керування простою бібліотекою;
   1. Програма повинна вміти:
      1. Додавати нову книгу в бібліотеку
      2. Показувати всі книги в бібліотеці
      3. Шукати книгу за її назвою
      4. Видаляти книгу з бібліотеки за її номером ISBN
2. Покрити тестами функціональність програми;
***
### Хід розробки:

Першим кроком розробки програми було створення класу `Book`, який є моделю книги, що знаходиться в бібліотеці. Через це, в ньому було створено такі поля, як `bookTitle` (назва книги), `authorName` (ім'я автора), `isbnNum` (номер ISBN), `publicationDate` (рік видання книги).  

Також, в цьому класі присутній конструктор, який приймає всі обов'язкові характеристики книги і ініціалізує відповідні поля об'єкта `Book`.  

Для подальшого функціоналу програми, в класі також були зроблені методи для виведення тих чи інших характеристик книги : `getTitle()`, `getAuthor()`, `getIsbnNum()`, `getPublicationDate()`.

Повний вигляд класу `Book` :
```java
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
```

Далі було створено створено клас `Library`, котрий містить в собі конструктор списку книг в бібліотеці : `public Library () {
this.books = new ArrayList<>();
} `; та методи, пов'язані з використанням даних бібліотеки : `addBook(Book book)`, `displayBook()`, `searchByTitle(String title)`, `hasBookWithTitle(String title)`, `removeBookByISBN(String isbnNum)`.

Завдяки цим методам, клас `Library` дозволяє додавати, видаляти, відображати та шукати книги в бібліотеці на основі їхньої назви та ISBN номеру. 

Повний вигляд класу `Library` :
```java
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
```

Наступним кроком було створення головного класу `Main`. Він служить для створення об'єктів книг (`Book.class`) та бібліотеки (`Library.class`) і викликів методів цих об'єктів для взаємодії з книгами у бібліотеці.

Опис дій, які повинні виконуватися в класі `Main`:
1. Створення об'єктів книг:

   0. Створюються сім об'єктів класу `Book`, які представляють різні книги. Кожен об'єкт `Book` ініціалізується з назвою, автором, ISBN і роком публікації.
2. Створення об'єкта бібліотеки:

   0. Створюється об'єкт класу `Library` під назвою `library`. Він представляє собою бібліотеку, в якій зберігаються книги.
3. Додавання книг до бібліотеки:

   0. Кожна створена книга додається до бібліотеки за допомогою методу `addBook`, який викликається для об'єкта `library`.
4. Виведення списку всіх книг:

   0. Виводиться на екран інформація про всі книги, які є у бібліотеці, за допомогою методу `displayBook` класу `Library`.
5. Пошук книги за назвою:

   0. Проводиться пошук книги за назвою "The Handmaid's Tale" за допомогою методу `searchByTitle`. Якщо книга з такою назвою знайдена, то виводиться інформація про неї, включаючи назву, автора, ISBN та рік публікації. Якщо книга не знайдена, виводиться відповідне повідомлення.
6. Видалення книги за ISBN:

   0. Виконується спроба видалити книгу з ISBN "978-1-234567-93-7" за допомогою методу `removeBookByISBN`. Якщо книга була успішно видалена, виводиться повідомлення про успішне видалення. Якщо книга не знайдена, виводиться відповідне повідомлення.
7. Виведення оновленого списку книг:

   0. Виводиться на екран оновлений список книг у бібліотеці за допомогою методу `displayBook`.

Повний вигляд класу `Main`:
```java
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
```

Останнім кроком розробки був клас `TestClass`, який перевіряє функціональність програми за допомогою вбудованого в InteliJJ JUnit5.

Повний вигляд класу `TestClass`:
```java
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
```
При перевірці працездатності програми, JUnit-тест показав, що все працює, як треба.
