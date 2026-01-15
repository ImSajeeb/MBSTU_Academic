package org.sajeeb.OOP.Encapsulation;

class Book {
    private int copies = 5;

    public void issueBook() {
        if (copies > 0)
            copies--;
    }

    public int availableCopies() {
        return copies;
    }
}

public class Main {
    public static void main(String[] args) {
        Book b = new Book();
        b.issueBook();
        System.out.println(b.availableCopies());
    }
}
