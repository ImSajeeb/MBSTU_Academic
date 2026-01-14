package com.sajeeb;

public class BankTest {
    public static void main(String[] args) {

        BankAccount account = new BankAccount("ACC101", 5000);

        account.deposit(2000);
        account.withdraw(1500);

       // System.out.println("Current Balance: " + account.balance);
       System.out.println("Current Balance2: " + account.getBalance());

    }
}
