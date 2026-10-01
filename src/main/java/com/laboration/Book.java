package com.laboration;

public record Book(String title, String author, String isbn) {
    public Book(String title, String author, String isbn) {
        if (isNotValidInput(title) || isNotValidInput(author) || isNotValidIsbn(isbn))
            throw new IllegalArgumentException();
        else {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        }
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

    private boolean isNotValidInput(String input) {
        return input.isBlank();
    }

    private boolean isNotValidIsbn(String isbn) {
        if (isbn.length() != 10 && isbn.length() != 13)
            return true;
        for (int i = 0; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i)))
                return true;
            }
        return false;
    }
}
