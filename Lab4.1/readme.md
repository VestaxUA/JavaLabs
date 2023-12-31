
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
```
Результатом перевірки словосполучення `"A man a plan a canal Panama"` було те, що це словосполучення є паліндромомом.


Наступним кроком було створення тестового класу :
```java
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
```

Тестування показало, що все працює належним чином.

pom.xml буде знаходитись в основній теці лабораторної роботи