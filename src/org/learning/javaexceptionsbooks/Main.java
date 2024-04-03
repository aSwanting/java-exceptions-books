package org.learning.javaexceptionsbooks;

public class Main {
    public static Book[] books;
    public static int bookCount;

    public static void main(String[] args) {

        bookCount = 3;
        books = new Book[bookCount];

        for (int i = 0; i < bookCount; i++) {
            books[i] = new Book("title-" + i, "author-" + i, "editor-" + i, 100);
        }
        for (Book book : books) System.out.println(book.toString());
    }
}
