package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the encoded message:");
        String encodedMessage = scanner.nextLine();

        String[] words = encodedMessage.split("\\s+");

        System.out.println("Encoded message: " + encodedMessage);
        System.out.println("Decoded message:");

        for (String word : words) {
            String decodedWord = Decoder.decodeWord(word);
            System.out.print(decodedWord + " ");
        }
    }
}




