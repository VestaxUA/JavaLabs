
![Autumn Leaves](./autumn.jpg)

# Лабораторна робота 2
## виконав : студент групи ПД-32 Ткачищен Валентин
***
### План роботи:

1. Удосконаліть систему, яка дозволяє бібліотекареві керувати предметами (книгами, DVD) та клієнтами.

- Система повинна мати можливість:
  - Додавати предмети (книги, DVD) до бібліотеки.
  - Видаляти предмети з бібліотеки.
  - Реєструвати читача.
  - Видавати предмет читачеві.
  - Повертати предмет у бібліотеку.
  - Показувати список доступних предметів.
  - Показувати список взятих предметів та їхніх читачів

2. Покрити тестами функціональність програми.

***
### Хід розробки:

Першим було створено абстрактний клас `Item`, котрий вміщує в собі інформацію, що може належати як книзі, так й DVD-диску,
тобто назву (`String title`), індентифікатор (`String uniqueID`) та булеве значення, чи була ця книга запозичена (`public boolean isBorrowed`),
а також декілька абстракцій, пов'язані з певними операціями, наприклад поверненню предмета до бібліотеки (`returnItem()`)
або навпаки, видача його читачеві (`borrowIten()`).

Повний вигляд класу `Item` :
```java
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
```
Наступним кроком було створення класів `Book` та `DVD` - ці класи наслідують `title` та `uniqueID` від класу `Item`,
але кожен з цих класів має свою унікальну зміну - `Book` має `author`, а `DVD` має `duration`. В цілому, обидва класи є майже ідентичними
по функціоналу та наповненню

Повний вигляд класу `Book`:
```java
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
```

Повний вигляд класу `DVD`:
```java
class DVD extends Item {
    int duration;

    DVD(String title, String uniqueID, int duration) {
        super(title, uniqueID);
        this.duration = duration;
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
```
Після створення класів щодо речей, далі було створено клас `Patron`, котрий має в собі змінні, пов'язані з читачами -
ім'я (`Name`), ідентифікатор (`ID`) та список запозичених книг/дисків (`borrowedItems`). Серед методів є, наприклад, метод
повернення книги від читача (`returnItem`) або метод для отримання імені (`getName`) чи ID (`getID`) читача. 

Повний вигляд класу `Patron`:
```java
class Patron {
    String name;
    String ID;
    List<Item> borrowedItems;

    Patron(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.borrowedItems = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }

    public void borrow(Item item) {
        borrowedItems.add(item);
    }

    public void returnItem(Item item) {
        borrowedItems.remove(item);
    }

}
```

Далі, було створено інтерфейс `IManagable`, що визначає набір методів, які будуть використовуватися для управління бібліотекою.

Повний вигляд інтерфейсу `IManagable`:
```java
interface IManageable {
    void add(Item item);

    void remove(Item item);

    void listAvailable();

    void listBorrowed();
}
```

Передостаннім кроком розробки самої програми було створення класу `Library`, який зберігає в собі списки читачів та предметів в самій бібліотеці, а також різні методи, 
котрі маніпулють з цими списками, наприклад, метод `lendItem` вписує читача в список тих, хто має запозичений предмет, а сам предмет - в список запозичених, якщо ж він вже запозичений, то
програма пише, що предмет вже запозичений іншим читачем.

Повний вигляд класу `Library`:
```java
class Library implements IManageable {
    List<Item> items;
    List<Patron> patrons;

    Library() {
        this.items = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    void registerPatron(Patron patron) {
        patrons.add(patron);
    }

    void lendItem(Patron patron, Item item) {
        if (item.isBorrowed()) {
            patron.borrow(item);
            item.borrowItem();
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    void returnItem(Patron patron, Item item) {
        if (patron.getBorrowedItems().contains(item)) {
            patron.returnItem(item);
            item.returnItem();
        } else {
            System.out.println("Patron did not borrow this item.");
        }

    }

    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public void remove(Item item) {
        items.remove(item);
    }

    @Override
    public void listAvailable() {
        System.out.println("Available Items:");
        for (Item item : items) {
            if (item.isBorrowed()) {
                System.out.println(item.getTitle());
            }
        }
    }

    @Override
    public void listBorrowed() {
        System.out.println("Borrowed Items:");
        for (Patron patron : patrons) {
            for (Item item : patron.getBorrowedItems()) {
                System.out.println(patron.getName() + " - " + item.getTitle());
            }
        }
    }
}
```

Останнім для розробки програми був крок створення головного методу `Main`, котррий має в собі зазначені параметри - профілі читачів, 
список книг та дисків та виклики методів з певними даними для обробки.

Повний вигляд класу `Main`:
```java
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
```

Остаточно завершаючим етапом було тестування за допомогою JUnit - для цього було створено клас `TestItem` бо для 
тестування підходили якраз методи цього класу.

Повний вигляд класу `TestItem`:
```java
public class TestItem {
    @org.junit.jupiter.api.Test
    void getTitle() {
        Item item = new Book("Sample Book", "B001", "Sample Author");
        Assertions.assertEquals("Sample Book", item.getTitle());
    }

    @org.junit.jupiter.api.Test
    void getUniqueID() {
        Item item = new Book("Sample Book", "B001", "Sample Author");
        Assertions.assertEquals("B001", item.getUniqueID());
    }

    @org.junit.jupiter.api.Test
    void isBorrowed() {
        Library library = new Library();
        Patron patron1 = new Patron("Alice", "P001");
        Patron patron2 = new Patron("Bob", "P002");

        Item item1 = new Book("Book 1", "B001", "Author 1");
        Item item2 = new Book("Book 2", "B002", "Author 2");

        library.registerPatron(patron1);
        library.registerPatron(patron2);
        library.add(item1);
        library.add(item2);

        library.lendItem(patron1, item1);
        library.lendItem(patron2, item2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        library.listBorrowed();

        System.setOut(System.out);

        String printedOutput = outputStream.toString();

        Assertions.assertTrue(printedOutput.contains("Alice - Book 1"));
        Assertions.assertTrue(printedOutput.contains("Bob - Book 2"));
    }
}
```
При перевірці працездатності програми, JUnit-тест показав, що все працює, як треба.

Для використання JUnit було прописано dependency в pom.xml