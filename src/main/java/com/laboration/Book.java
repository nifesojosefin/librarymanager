package com.laboration;

public record Book(String title, String author, int isbn) {
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
