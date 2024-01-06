package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void testCreateAccount() {
        BankAccount account = bank.createAccount("TestAccount1", 1000);
        assertNotNull(account);
        assertEquals("TestAccount1", account.getAccountName());
        assertEquals(1000, account.getBalance(), 0.001);
    }

    @Test
    public void testFindAccount() throws AccountNotFoundException {
        BankAccount account = bank.createAccount("TestAccount2", 500);
        int accountNumber = account.getAccountNumber();

        BankAccount foundAccount = bank.findAccount(accountNumber);
        assertNotNull(foundAccount);
        assertEquals(accountNumber, foundAccount.getAccountNumber());
        assertEquals("TestAccount2", foundAccount.getAccountName());
        assertEquals(500, foundAccount.getBalance(), 0.001);
    }

    @Test
    public void testTransferMoney() throws AccountNotFoundException, InsufficientFundsException, NegativeAmountException {
        BankAccount account1 = bank.createAccount("TestAccount1", 1000);
        BankAccount account2 = bank.createAccount("TestAccount2", 500);

        bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 300);

        assertEquals(700, account1.getBalance(), 0.001);
        assertEquals(800, account2.getBalance(), 0.001);
    }

    @Test
    public void testTransferMoneyWithInsufficientFunds() throws AccountNotFoundException, InsufficientFundsException, NegativeAmountException {
        assertThrows(InsufficientFundsException.class, () -> {
            BankAccount account1 = bank.createAccount("TestAccount1", 100);
            BankAccount account2 = bank.createAccount("TestAccount2", 500);

            bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 200); // Trying to transfer more than the balance of account1
        });
    }

    @Test
    public void testFindNonexistentAccount() {
        assertThrows(AccountNotFoundException.class, () -> {
            bank.findAccount(12345); // Assuming 12345 is a non-existent account number
        });
    }

    @Test
    public void testNegativeDeposit() throws NegativeAmountException {
        assertThrows(NegativeAmountException.class, () -> {
            BankAccount account = bank.createAccount("TestAccount1", 1000);
            account.deposit(-200); // Trying to deposit a negative amount
        });
    }
}
