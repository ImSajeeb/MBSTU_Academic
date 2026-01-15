package org.validation;

import org.validation.model.Order;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();

        scanner.nextLine(); // consume newline

        System.out.print("Enter Department of MBSTU: ");
        String department = scanner.nextLine();

        Order order = new Order(age, department);

        try {
            Validator.validate(order);
            System.out.println("Order is VALID");
        }
        catch (Exception e) {
            System.out.println("Validation FAILED: " + e.getMessage());
        }

        scanner.close();
    }
}
