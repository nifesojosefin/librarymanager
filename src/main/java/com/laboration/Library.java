package com.laboration;

public class Library {
    static void main() {
        Book[] books = new Book[20];
        int bookCount = 0;
        Member[] members = new Member[10];
        int memberCount = 0;
        int[] booksBorrowingStatus = new int[40];

    }

    static void printMenu() {
        IO.println("""
                The Library Management System
                ====================================
                1. Add book
                2. Register member
                3. Borrow book
                4. Return book
                5. Search for book (title or author)
                6. Show all books and status
                e. Exit
                """);
    }

    static void addBook(Book[] books, int[] booksBorrowingStatus, int bookCount) {
        String title = IO.readln("Enter the title of the book: ");
        String author = IO.readln("Enter the author of the book: ");
        int isbn = Integer.parseInt(IO.readln("Enter the ISBN of the book: "));
        books[bookCount] = new Book(title, author, isbn);
        if (bookCount > 0)
            booksBorrowingStatus[bookCount + 1] = bookCount;
    }

    static void registerMember(Member[] members, int memberCount) {
        int membershipNumber = Integer.parseInt(IO.readln("Enter the membership number of the member: "));
        String name = IO.readln("Enter the name of the member: ");
        members[memberCount] = new Member(membershipNumber, name);
    }
}
