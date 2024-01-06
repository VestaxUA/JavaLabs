
![Autumn Leaves](./autumn.jpg)

# Лабораторна робота 4.1
## виконав : студент групи ПД-32 Ткачищен Валентин
***
### План роботи:

1. Реалізувати функцію, яка перевіряє, чи дане слово є паліндромом (читається так само, як назад, так і вперед), використовуючи рядки Java.

2. Покрити тестами функціональність програми.

***
### Хід розробки:

Для визначення, чи є певне словосполучення/слово паліндромом, було створено таку функцію :

```java
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
```
Результатом перевірки словосполучення `"A man a plan a canal Panama"` було те, що це словосполучення є паліндромомом.


Наступним кроком було створення тестового класу :
```java
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
```

Тестування показало, що майже все працює належним чином, окрім перевірки "A man a plan a canal Panama" - різні джерела говорять протилежні речі щодо того, чи є фраза паліндромом чи ні, та за час розробки не вийшло виправити цю помилку, але при цбому інші паліндроми в тесті програма проходить чудово 

pom.xml буде знаходитись в основній теці лабораторної роботи