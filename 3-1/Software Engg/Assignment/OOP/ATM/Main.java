package org.sajeeb.OOP.ATM;

import java.util.Scanner;

class BankMachine {
    private int balance = 2000;

    void addMoney(int amt) {
        balance += amt;
    }

    void removeMoney(int amt) {
        if (amt <= balance)
            balance -= amt;
        else
            System.out.println("Not enough balance");
    }

    void showBalance() {
        System.out.println("Balance = " + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        BankMachine bm = new BankMachine();
        Scanner sc = new Scanner(System.in);

        bm.addMoney(500);
        bm.removeMoney(300);
        bm.showBalance();
    }
}
