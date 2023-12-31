package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {

    public static String decodeWord(String encodedWord) {
        Pattern numberPattern = Pattern.compile(".*\\d.*");
        Matcher matcher = numberPattern.matcher(encodedWord);

        if (matcher.find()) {
            return decodeVowelReplacement(encodedWord);
        } else {
            return decodeConsonantReplacement(encodedWord);
        }
    }

    private static String decodeVowelReplacement(String encodedWord) {
        return encodedWord
                .replace("1", "a")
                .replace("2", "e")
                .replace("3", "i")
                .replace("4", "o")
                .replace("5", "u");
    }

    private static String decodeConsonantReplacement(String encodedWord) {
        StringBuilder decodedWord = new StringBuilder();
        for (char c : encodedWord.toCharArray()) {
            if (Character.isLetter(c)) {
                char decodedChar = findPreviousConsonant(c);
                decodedWord.append(decodedChar);
            } else {
                decodedWord.append(c);
            }
        }
        return decodedWord.toString();
    }

    private static char findPreviousConsonant(char currentConsonant) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int index = new String(alphabet).indexOf(currentConsonant);

        if (index == -1) {
            return currentConsonant;
        }

        int previousIndex = (index - 1 + alphabet.length) % alphabet.length;
        return alphabet[previousIndex];
    }
}
