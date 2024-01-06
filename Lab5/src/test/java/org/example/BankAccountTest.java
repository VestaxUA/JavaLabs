package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    public void testDeposit() throws NegativeAmountException {
        BankAccount account = new BankAccount(1, "Account1", 0);
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException, NegativeAmountException {
        BankAccount account = new BankAccount(2, "Account2", 200.0);
        account.withdraw(50.0);
        assertEquals(150.0, account.getBalance(), 0.001);
    }
}
