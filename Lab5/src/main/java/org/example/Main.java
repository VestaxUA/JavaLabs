package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank();

            BankAccount account1 = bank.createAccount("Client1", 2300);
            BankAccount account2 = bank.createAccount("Client2", 540);

            System.out.println(account1.getAccountSummary());
            System.out.println(account2.getAccountSummary());

            account1.withdraw(200);
            account2.deposit(200);

            System.out.println(account1.getAccountSummary());
            System.out.println(account2.getAccountSummary());

            bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 300);

            System.out.println(account1.getAccountSummary());
            System.out.println(account2.getAccountSummary());

        } catch (NegativeAmountException | InsufficientFundsException | AccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}

