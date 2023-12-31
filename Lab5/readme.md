
![Autumn Leaves](./autumn.jpg)

# Лабораторна робота 5
## виконав : студент групи ПД-32 Ткачищен Валентин
***
### План роботи:

1. Реалізувати клас BankAccount з членами класу accountNumber, accountName і balance.
2. Реалізувати методи deposit(double amount), withdraw(double amount), getBalance() та getAccountSummary().
3. Створити спеціалізовані класи винятків:
   - InsufficientFundsException
   - NegativeAmountException
   - AccountNotFoundException

4. Реалізуйте клас Bank, який зберігає колекцію об'єктів BankAccount.
5. У класі Bank, реалізуйте методи:
   - createAccount(String accountName, double initialDeposit)
   - findAccount(int accountNumber)
   - transferMoney(int fromAccountNumber, int toAccountNumber, double amount)
5. Обробляйте винятки відповідно в кожному методі.
6. Створіть тестові класи, де ви моделюєте різні сценарії для тестування обробки виняткових ситуацій.
***
### Хід розробки:

Вигляд класу `BankAccount`
```java
package org.example;

public class BankAccount {
   private int accountNumber;
   private String accountName;
   private double balance;


   public void deposit (double amount) throws NegativeAmountException {
      if (amount < 0) {
         throw new NegativeAmountException("Negative amount in the deposit");
      }
      balance += amount;
   }

   public void withdraw(double amount) throws InsufficientFundsException {
      if (balance < amount) {
         throw new InsufficientFundsException("Insufficient funds in the account");
      }
      balance -= amount;
   }

   public double getBalance () {
      return balance;
   }

   public void getAccountSummary() {
      System.out.println("Account Number is _ " + accountNumber + "\n" +
              "Account Name is _ "   + accountName   + "\n" +
              "Account Balance is _ "+ balance);
   }

   public int getAccountNumber () {
      return accountNumber;
   }

   public void setAccountNumber (int accountNumber) {
      this.accountNumber = accountNumber;
   }

   public String getAccountName () {
      return accountName;
   }

   public void setAccountName (String accountName) {
      this.accountName = accountName;
   }
}




```

Вигляд класів винятків :

- `InsufficientFundsException` :
```java
package org.example;

// InsufficientFundsException.java
public class InsufficientFundsException extends Exception {
   public InsufficientFundsException(String message) {
      super(message);
   }
}
```
- `NegativeAmountException` :
```java
package org.example;

// NegativeAmountException.java
public class NegativeAmountException extends Exception {
   public NegativeAmountException(String message) {
      super(message);
   }
}
```
- `AccountNotFoundException` :
```java
package org.example;

// AccountNotFoundException.java
public class AccountNotFoundException extends Exception {
   public AccountNotFoundException(String message) {
      super(message);
   }
}

```

Вигляд класу `Bank` :
```java
package org.example;


import java.util.ArrayList;
import java.util.List;

public class Bank {
   private final List<BankAccount> accounts;

   public Bank() {
      this.accounts = new ArrayList<>();
   }

   public void createAccount(String accountName, double initialDeposit, int accountNumber) throws NegativeAmountException {
      BankAccount newAccount = new BankAccount();
      newAccount.setAccountNumber(accountNumber);
      newAccount.deposit(initialDeposit);
      newAccount.setAccountName(accountName);
      accounts.add(newAccount);
   }

   public BankAccount findAccount(int accountNumber) throws AccountNotFoundException {
      for (BankAccount account : accounts) {
         if (account.getAccountNumber() == (accountNumber)) {
            return account;
         }
      }
      throw new AccountNotFoundException("Account with number " + accountNumber + " not found");
   }

   public void transferMoney (int fromAccountNumber, int toAccountNumber, double amount)
           throws AccountNotFoundException, InsufficientFundsException {

      BankAccount fromAccount = findAccount(fromAccountNumber);
      BankAccount toAccount = findAccount(toAccountNumber);

      fromAccount.withdraw(amount);
      try {
         toAccount.deposit(amount);
      } catch (NegativeAmountException e) {
         throw new RuntimeException(e);
      }
   }

   public List<BankAccount> getAccounts() {
      return accounts;
   }
}
```
Тестові класи :
- `BankAccountTest` :
```java
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    public void testDeposit() throws NegativeAmountException {
        BankAccount account = new BankAccount();
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void withdraw () throws InsufficientFundsException, NegativeAmountException {
        BankAccount account = new BankAccount();
        account.deposit(200.0);
        account.withdraw(50.0);
        assertEquals(150.0, account.getBalance());
    }
}
```
- `BankTest` :
```java
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    public void testCreateAccount() throws NegativeAmountException {
        Bank bank = new Bank();
        bank.createAccount("Joe", 200.0, 1);
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    void testFindAccount() throws AccountNotFoundException, NegativeAmountException {
        Bank bank = new Bank();
        bank.createAccount("Hanna", 300.0, 1);
        BankAccount foundAccount = bank.findAccount(1);
        assertNotNull(foundAccount);
        assertEquals("Hanna", foundAccount.getAccountName());
    }

    @Test
    public void testTransferMoney() throws AccountNotFoundException, NegativeAmountException, InsufficientFundsException {
        Bank bank = new Bank();
        bank.createAccount("Esmeralda", 500.0, 1);
        bank.createAccount("Hector", 350.0, 2);

        bank.transferMoney(1, 2, 75.0);
        assertEquals(425.0, bank.findAccount(1).getBalance());
        assertEquals(425.0, bank.findAccount(2).getBalance());
    }
}
```
Тестування показало, що все працює належним чином.

pom.xml буде знаходитись в основній теці лабораторної роботи