package com.laboration;

public record Book(String title, String author, String isbn) {
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        if (isValidIsbn(isbn))
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

    private boolean isValidIsbn(String isbn) {
        if (isbn.length() != 10 && isbn.length() != 13)
            return false;
        for (int i = 0; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i)))
                return false;
            }
        return true;
    }
}
