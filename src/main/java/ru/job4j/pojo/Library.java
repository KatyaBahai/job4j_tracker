package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Librarian", 384);
        Book book2 = new Book("Earth", 256);
        Book book3 = new Book("Suitcase", 160);
        Book book4 = new Book("Clean Code", 464);
        Book[] books = {book1, book2, book3, book4};
        for (int index = 0; index < books.length; index++) {
            Book current = books[index];
            System.out.println("Book " + current.getName() + ", number of pages: " + current.getPages());
        }
        System.out.println();
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int index = 0; index < books.length; index++) {
            Book current = books[index];
            System.out.println("Book " + current.getName() + ", number of pages: " + current.getPages());
        }
        System.out.println();
        for (int index = 0; index < books.length; index++) {
            Book current = books[index];
            if ("Clean Code".equals(current.getName())) {
                System.out.println("Book " + current.getName() + ", number of pages: " + current.getPages());
            }
        }
    }
}
