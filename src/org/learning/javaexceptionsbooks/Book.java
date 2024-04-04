package org.learning.javaexceptionsbooks;

public class Book {
    private String title, author, editor;
    private int pageCount;

    public Book(String title, String author, String editor, int pageCount) {

        validateString("Title", title);
        this.title = title;

        validateString("Author", author);
        this.author = author;

        validateString("Editor", editor);
        this.editor = editor;

        validatePageCount("Number of pages", pageCount);
        this.pageCount = pageCount;

    }

    private void validateString(String field, String value) throws IllegalArgumentException {
        if (field.trim().isEmpty() || field == null) throw new IllegalArgumentException(field + " cannot be empty");
    }

    private void validatePageCount(String field, int value) {
        if (value <= 0) throw new IllegalArgumentException(field + " must be a number > 0");
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
        validateString("Title", title);
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateString("Author", author);
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        validateString("Editor", editor);
        this.editor = editor;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        validatePageCount("Number of pages", pageCount);
        this.pageCount = pageCount;
    }
}
