package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testIsPalindromeWithPalindrome() {
        assertTrue(Main.isPalindrome("racecar"));
        assertTrue(Main.isPalindrome("A man a plan a canal Panama"));
    }

    @Test
    public void testIsPalindromeWithNonPalindrome() {
        assertFalse(Main.isPalindrome("hello"));
        assertFalse(Main.isPalindrome("Not a palindrome"));
    }

    @Test
    public void testIsPalindromeWithEmptyString() {
        assertTrue(Main.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeWithWhitespace() {
        assertTrue(Main.isPalindrome("   a man a plan a canal Panama   "));
    }

    @Test
    public void testIsPalindromeWithSingleCharacter() {
        assertTrue(Main.isPalindrome("a"));
    }
}