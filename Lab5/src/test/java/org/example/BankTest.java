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