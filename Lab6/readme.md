
![Snowy Day](./winter.jpg)

# Лабораторна робота 6
## виконав : студент групи ПД-32 Ткачищен Валентин
***
### План роботи:

1. Ініціалізація Масиву: Створіть 3D масив для представлення кінотеатру. Ініціалізуйте масив нулями, що вказує на те, що всі місця вільні.

2. Бронювання Місць: Напишіть метод bookSeats(int hallNumber, int row, int[] seats), який приймає номер залу, номер ряду, номери місць для бронювання. Метод повинен позначити заброньовані місця, змінивши відповідні нулі на одиниці. Якщо місця заброньовані, система повинна сповістити про це користувача.

3. Скасування Бронювання: Напишіть метод cancelBooking(int hallNumber, int row, int[] seats), який скасовує бронювання, змінюючи відповідні одиниці назад на нулі.

4. Перевірка Наявності: Напишіть метод checkAvailability(int screen, int numSeats), який перевіряє, чи доступна задана кількість послідовних місць в будь-якому ряду зазначеного залу.

5. Друк Схеми Розміщення Місць: Напишіть метод printSeatingArrangement(int hallNumber), який друкує схему розміщення місць для даного залу, вказуючи заброньовані та доступні місця.

6. Бонус:
- Реалізуйте метод findBestAvailable(int hallNumber, int numSeats), який знаходить та повертає найкращі доступні послідовні місця для даної кількості місць в зазначеному залі.
- Реалізуйте метод autoBook(int hallNumber, int numSeats), який автоматично бронює найкращі доступні послідовні місця, знайдені методом findBestAvailable.

***
### Хід розробки:

Вигляд класу `Main`
```java
package org.example;

public class Main {
   public static void main(String[] args) {
      Cinema cinema = new Cinema();

      int hallNumber = 0;
      int row = 2;
      int[] seatsToBook = {3, 4, 5};

      cinema.bookSeats(hallNumber, row, seatsToBook);
      cinema.printSeatingArrangement(hallNumber);

      cinema.cancelBooking(hallNumber, row, seatsToBook);
      cinema.printSeatingArrangement(hallNumber);

      int numSeatsToCheck = 3;
      if (cinema.checkAvailability(hallNumber, numSeatsToCheck)) {
         System.out.println("Available sequential seats : " + numSeatsToCheck);
      } else {
         System.out.println("There are not enough free places for " + numSeatsToCheck + " sequential seats.");
      }

      int numSeatsToAutoBook = 4;

      cinema.autoBook(hallNumber, numSeatsToAutoBook);
      cinema.printSeatingArrangement(hallNumber);
   }
}
```

Вигляд класу `Cinema` :
```java
package org.example;


import java.util.ArrayList;
import java.util.List;

public class Cinema {

   private static final int NUM_HALLS = 5;
   private static final int NUM_ROWS = 10;
   private static final int NUM_SEATS_PER_ROW = 20;

   final int[][][] seatingArrangement;

   public Cinema() {
      this.seatingArrangement = new int[NUM_HALLS][NUM_ROWS][NUM_SEATS_PER_ROW];
      initializeSeatingArrangement();
   }

   private void initializeSeatingArrangement() {
      for (int hall = 0; hall < NUM_HALLS; hall++) {
         for (int row = 0; row < NUM_ROWS; row++) {
            for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
               seatingArrangement[hall][row][seat] = 0;
            }
         }
      }
   }

   public void bookSeats(int hallNumber, int row, int[] seats) {
      for (int seat : seats) {
         if (seatingArrangement[hallNumber][row][seat] == 1) {
            System.out.println("Seat " + seat + " in a row " + row + " hall " + hallNumber + " already booked.");
         } else {
            seatingArrangement[hallNumber][row][seat] = 1;
            System.out.println("Seat " + seat + " in a row " + row + " hall " + hallNumber + " successfully booked.");
         }
      }
   }

   public void cancelBooking(int hallNumber, int row, int[] seats) {
      for (int seat : seats) {
         if (seatingArrangement[hallNumber][row][seat] == 0) {
            System.out.println("Seat " + seat + " in a row " + row + " hall " + hallNumber + " is available.");
         } else {
            seatingArrangement[hallNumber][row][seat] = 0;
            System.out.println("Seat booking " + seat + " in a row " + row + " hall " + hallNumber + " cancelled.");
         }
      }
   }

   public boolean checkAvailability(int hallNumber, int numSeats) {
      for (int row = 0; row < NUM_ROWS; row++) {
         int consecutiveSeats = 0;
         for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
            if (seatingArrangement[hallNumber][row][seat] == 0) {
               consecutiveSeats++;
               if (consecutiveSeats == numSeats) {
                  return true;
               }
            } else {
               consecutiveSeats = 0;
            }
         }
      }
      return false;
   }

   public void printSeatingArrangement(int hallNumber) {
      System.out.println("Seating chart for the hall " + hallNumber + ":");
      for (int row = 0; row < NUM_ROWS; row++) {
         for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
            System.out.print(seatingArrangement[hallNumber][row][seat] + " ");
         }
         System.out.println();
      }
   }

   public List<Integer> findBestAvailable(int hallNumber, int numSeats) {
      List<Integer> bestSeats = new ArrayList<>();
      int currentConsecutiveSeats = 0;
      int startSeatIndex = -1;

      for (int row = 0; row < NUM_ROWS; row++) {
         for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
            if (seatingArrangement[hallNumber][row][seat] == 0) {
               if (currentConsecutiveSeats == 0) {
                  startSeatIndex = seat;
               }
               currentConsecutiveSeats++;

               if (currentConsecutiveSeats == numSeats) {
                  for (int i = startSeatIndex; i < startSeatIndex + numSeats; i++) {
                     bestSeats.add(i);
                  }
                  return bestSeats;
               }
            } else {
               currentConsecutiveSeats = 0;
            }
         }
      }
      return bestSeats;
   }

   public void autoBook(int hallNumber, int numSeats) {
      List<Integer> bestSeats = findBestAvailable(hallNumber, numSeats);
      if (bestSeats.isEmpty()) {
         System.out.println("There are not enough available consecutive seats to book " + numSeats + " seats in the hall " + hallNumber + ".");
      } else {
         int[] seatsArray = new int[bestSeats.size()];
         for (int i = 0; i < bestSeats.size(); i++) {
            seatsArray[i] = bestSeats.get(i);
         }
         bookSeats(hallNumber, 0, seatsArray);
         System.out.println("Automatic booking of " + numSeats + " seats in the hall " + hallNumber + " successfully completed.");
      }
   }
}

```
Тестовий клас `CinemaTest` :
```java
package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class CinemaTest {

   public Cinema cinema;
   @BeforeEach
   public void setUp() {
      cinema = new Cinema();
   }







   @Test
   void testBookSeats() {
      int hallNumber = 0;
      int row = 2;
      int[] seatsToBook = {3, 4, 5};

      cinema.bookSeats(hallNumber, row, seatsToBook);

      Assertions.assertEquals(1, cinema.seatingArrangement[hallNumber][row][3]);
      Assertions.assertEquals(1, cinema.seatingArrangement[hallNumber][row][4]);
      Assertions.assertEquals(1, cinema.seatingArrangement[hallNumber][row][5]);
   }

   @Test
   void testCancelBooking() {
      int hallNumber = 0;
      int row =2;
      int[] seatsToBook = {3, 4, 5};

      cinema.bookSeats(hallNumber, row, seatsToBook);
      cinema.cancelBooking(hallNumber, row, seatsToBook);

      Assertions.assertEquals(0, cinema.seatingArrangement[hallNumber][row][3]);
      Assertions.assertEquals(0, cinema.seatingArrangement[hallNumber][row][4]);
      Assertions.assertEquals(0, cinema.seatingArrangement[hallNumber][row][5]);

   }

   @Test
   void testCheckAvailability() {
      int hallNumber = 0;
      int numSeatsToCheck = 3;

      cinema.bookSeats(hallNumber, 0, new int[]{0, 1, 2});
      Assertions.assertTrue(cinema.checkAvailability(hallNumber, numSeatsToCheck));

      cinema.bookSeats(hallNumber, 1, new int[]{5, 6, 7});
      Assertions.assertTrue(cinema.checkAvailability(hallNumber, numSeatsToCheck));
   }

   @Test
   void testFindBestAvailable() {
      int hallNumber = 0;
      int numSeatsToFind = 4;

      List<Integer> bestSeats = cinema.findBestAvailable(hallNumber, numSeatsToFind);

      Assertions.assertEquals(numSeatsToFind, bestSeats.size());
      for (int seat : bestSeats) {
         Assertions.assertEquals(0, cinema.seatingArrangement[hallNumber][0][seat]);
      }
   }

   @Test
   void testAutoBook() {
      int hallNumber = 0;
      int numSeatsToAutoBook = 4;

      cinema.autoBook(hallNumber, numSeatsToAutoBook);

      for (int i = 0; i < numSeatsToAutoBook; i++) {
         Assertions.assertEquals(1, cinema.seatingArrangement[hallNumber][0][i]);
      }
   }

}
```

Тестування показало, що все працює належним чином.

pom.xml буде знаходитись в основній теці лабораторної роботи