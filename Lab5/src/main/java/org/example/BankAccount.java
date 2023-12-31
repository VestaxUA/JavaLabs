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



