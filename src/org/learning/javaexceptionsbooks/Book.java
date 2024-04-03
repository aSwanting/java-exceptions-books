package org.learning.javaexceptionsbooks;

public class Book {
    private String title, author, editor;
    private int pageNumbers;

    public Book(String title, String author, String editor, int pageNumbers) {
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.pageNumbers = pageNumbers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", editor='" + editor + '\'' +
                ", pageNumbers=" + pageNumbers +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int pageNumbers) {
        this.pageNumbers = pageNumbers;
    }
}
