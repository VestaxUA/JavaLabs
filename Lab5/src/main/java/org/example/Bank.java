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
