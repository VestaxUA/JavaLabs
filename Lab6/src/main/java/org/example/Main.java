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