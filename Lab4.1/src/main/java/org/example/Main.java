package org.example;

public class Main {
        public static boolean isPalindrome (String word){

            word = word.replaceAll("\\s", "").toLowerCase();

            String reversedWord = new StringBuilder(word).reverse().toString();

            return word.equals(reversedWord);
        }

        public static void main (String[]args){
            String word = "A man a plan a canal Panama";
            if (isPalindrome(word)) {
                System.out.println(word + " is palindrome.");
            } else {
                System.out.println(word + " isn`t palindrome.");
            }
        }
}