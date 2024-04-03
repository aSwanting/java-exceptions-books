package org.learning.javaexceptionsbooks;

public class Book {
    private String title, author, editor;
    private int pageCount;

    public Book(String title, String author, String editor, int pageCount) {

        validateTitle(title);
        this.title = title;

        validateAuthor(author);
        this.author = author;

        validateEditor(editor);
        this.editor = editor;

        validatePageCount(pageCount);
        this.pageCount = pageCount;

    }

    private void validateTitle(String title) throws IllegalArgumentException {
        if (title.trim().isEmpty()) throw new IllegalArgumentException("title too short");
    }

    private void validateAuthor(String author) throws IllegalArgumentException {
        if (author.trim().isEmpty()) throw new IllegalArgumentException("author too short");
    }

    private void validateEditor(String editor) throws IllegalArgumentException {
        if (editor.trim().isEmpty()) throw new IllegalArgumentException("editor too short");
    }

    private void validatePageCount(int pageCount) {
        if (pageCount < 1) throw new IllegalArgumentException("not enough pages");
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", editor='" + editor + '\'' +
                ", pageNumbers=" + pageCount +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        validateEditor(editor);
        this.editor = editor;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        validatePageCount(pageCount);
        this.pageCount = pageCount;
    }
}
