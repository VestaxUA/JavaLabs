package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testIsPalindromeWithPalindrome() {
        assertFalse(Main.isPalindrome(null));
        assertTrue(Main.isPalindrome("radar"));
        assertFalse(Main.isPalindrome("banana"));
        assertTrue(Main.isPalindrome("hannah"));
        assertTrue(Main.isPalindrome("pup"));
        assertTrue(Main.isPalindrome("nan"));
        assertFalse(Main.isPalindrome("lollipop"));
        assertTrue(Main.isPalindrome("eye"));
        assertTrue(Main.isPalindrome("6543456"));
        assertTrue(Main.isPalindrome("step on no pets"));
        assertFalse(Main.isPalindrome("A man a plan a canal Panama"));
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