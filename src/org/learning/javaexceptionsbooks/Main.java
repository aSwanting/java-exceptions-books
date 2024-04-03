package org.learning.javaexceptionsbooks;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Book book = null;
        Book[] books = new Book[10];
        String title = null, author = null, editor = null;
        int pageCount = 0;

        for (int i = 0; i < books.length; i++) {
            System.out.println("book " + (i + 1));

            if (title == null) {
                System.out.println("enter title");
                title = scan.nextLine();
            }

            if (author == null) {
                System.out.println("enter author");
                author = scan.nextLine();
            }

            if (editor == null) {
                System.out.println("enter editor");
                editor = scan.nextLine();
            }

            if (pageCount == 0) {
                System.out.println("enter number of pages");
                pageCount = Integer.parseInt(scan.nextLine());
            }

            try {
                books[i] = new Book(title, author, editor, pageCount);
                title = null;
                author = null;
                editor = null;
                pageCount = 0;
//            books[i] = new Book("title", "author", "editor", 9 - i);
//            book = new Book("title", "author", "editor", 0);
//            Book book = new Book(title, author, editor, pageCount);
//            book.setTitle("");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }
    }
}
