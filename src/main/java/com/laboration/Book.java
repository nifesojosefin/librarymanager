package com.laboration;

public record Book(String title, String author, int isbn) {
    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        if (isbn == 10 || isbn == 13)
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

    public int getIsbn() {
        return isbn;
    }
}
