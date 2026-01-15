package org.sajeeb.OOP.Calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();
        boolean run = true;

        while (run) {

            System.out.println("\n--- Calculator Menu ---");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            if (choice == 5) {
                run = false;
                System.out.println("Calculator closed.");
                break;
            }

            System.out.print("Enter first number: ");
            double a = sc.nextDouble();

            System.out.print("Enter second number: ");
            double b = sc.nextDouble();

            try {
                double result = 0;

                switch (choice) {
                    case 1:
                        result = calc.add(a, b);
                        break;
                    case 2:
                        result = calc.subtract(a, b);
                        break;
                    case 3:
                        result = calc.multiply(a, b);
                        break;
                    case 4:
                        result = calc.divide(a, b);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        continue;
                }

                System.out.println("Result = " + result);

            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
