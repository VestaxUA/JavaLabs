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