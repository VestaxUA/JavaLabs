package org.example;

public class Main {
    public static boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        return cleanWord.equals(new StringBuilder(cleanWord).reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("radar")); // true
        System.out.println(isPalindrome("banana")); // false
        System.out.println(isPalindrome("hannah")); // true
        System.out.println(isPalindrome("A man a plan a canal Panama")); // false
    }
}
