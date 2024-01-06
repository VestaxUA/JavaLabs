
![Autumn Leaves](./autumn.jpg)

# Лабораторна робота 4.2
## виконав : студент групи ПД-32 Ткачищен Валентин
***
### План роботи:

1. Вимоги:
   1. Створіть клас Java Decoder зі статичними методами для кожного типу кодування.
   2. Використовуйте регулярні вирази, щоб визначити, який метод кодування було застосовано до кожного слова.
   3. Використовуйте StringBuilder для ефективних маніпуляцій з рядками.
   4. Створіть функцію main для демонстрації вашого декодера з прикладами зашифрованих повідомлень.
      Примітка: Це рішення припускає, що методи кодування не перетинаються. У реальному світі може знадобитися додаткові методи для визначення типу кодування з більшою точністю.

2. Покрити тестами функціональність програми.
***
### Хід розробки:

Спершу було створено клас `Decoder`, котрий є основним для роботи програми :
```java
package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {

   public static String decodeWord(String encodedWord) {
      if (containsNumbers(encodedWord)) {
         return decodeVowelReplacement(encodedWord);
      } else {
         return decodeConsonantReplacement(encodedWord);
      }
   }

   private static boolean containsNumbers(String word) {
      Pattern numberPattern = Pattern.compile(".*\\d.*");
      Matcher matcher = numberPattern.matcher(word);
      return matcher.find();
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
```

Далі, за вказівками, було зроблено `Main` клас з даними для демонстрації роботи декодера :

```java
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





```

Останнім кроком було створення тестів для декодера `DecoderTest` :

```java
package org.example;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoderTest {

    @Test
    public void testDecodeMessage() {
        String encodedMessage1 = "uftujoh";
        String expectedDecodedMessage1 = "testing";
        String actualDecodedMessage1 = Decoder.decodeWord(encodedMessage1);
        assertEquals(expectedDecodedMessage1, actualDecodedMessage1);

        String encodedMessage2 = "t2st3ng";
        String expectedDecodedMessage2 = "testing";
        String actualDecodedMessage2 = Decoder.decodeWord(encodedMessage2);
        assertEquals(expectedDecodedMessage2, actualDecodedMessage2);
    }
}

```

Тестування показало, що все працює належним чином.

pom.xml буде знаходитись в основній теці лабораторної роботи