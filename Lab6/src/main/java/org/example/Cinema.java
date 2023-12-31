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

