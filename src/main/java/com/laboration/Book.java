package com.laboration;

public record Book(String title, String author, String isbn) {
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        if (isbn.length() == 10 || isbn.length() == 13)
            this.isbn = isbn;
        else
            throw new IllegalArgumentException();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
}
