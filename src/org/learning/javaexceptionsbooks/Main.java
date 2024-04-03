package org.learning.javaexceptionsbooks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Book[] books;

    public static void readingList() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many books do you want to add?");
        int bookCount = Integer.parseInt(scan.nextLine());
        books = new Book[bookCount];

        String title = null;
        String author = null;
        String editor = null;
        int pageCount = 0;

        for (int i = 0; i < books.length; i++) {
            System.out.println("book " + (i + 1));

            if (title == null || title.trim().isEmpty()) {
                System.out.println("enter title");
                title = scan.nextLine();
            }

            if (author == null || author.trim().isEmpty()) {
                System.out.println("enter author");
                author = scan.nextLine();
            }

            if (editor == null || editor.trim().isEmpty()) {
                System.out.println("enter editor");
                editor = scan.nextLine();
            }

            if (pageCount < 1) {
                System.out.println("enter number of pages");
                pageCount = Integer.parseInt(scan.nextLine());
            }

            try {
                books[i] = new Book(title, author, editor, pageCount);
                title = null;
                author = null;
                editor = null;
                pageCount = 0;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }
    }

    public static void outputReadingList() {
        try {
            FileWriter fw = new FileWriter("fw.txt");
            for (Book book : books) {
                fw.write(book.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printFromFile() {
        System.out.println("reading list from file...");
        File readingList = new File("fw.txt");
        Scanner reader;
        try {
            reader = new Scanner(readingList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            System.out.println(data);
        }
        reader.close();
    }

    public static void main(String[] args) {

        readingList();
        outputReadingList();
        printFromFile();

    }
}
