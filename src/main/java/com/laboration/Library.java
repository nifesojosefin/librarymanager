package com.laboration;

public class Library {
    static void main() {
        Book[] books = new Book[20];
        int bookCount = 0;
        Member[] members = new Member[10];
        int memberCount = 0;
        int[] booksBorrowingStatus = new int[40];

        setInitialValuesForBorrowingStatus(booksBorrowingStatus);


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

    static void setInitialValuesForBorrowingStatus(int[] booksBorrowingStatus) {
        for (int i = 1; i < booksBorrowingStatus.length; i = i + 2)
            booksBorrowingStatus[i] = -1;
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
        int membershipNumber = Integer.parseInt(IO.readln("Enter the membership number for the person: "));
        for (int i = 0; i <= memberCount; i++) {
            if (members[i].getMembershipNumber() == membershipNumber) {
                IO.println("The membership number already exists");
            }
            else {
                String name = IO.readln("Enter the name of the person: ");
                members[memberCount] = new Member(membershipNumber, name);
                IO.println("The member has been registered");
            }
        }
    }

    static void borrowBook(Book[] books, int bookCount, Member[] members, int memberCount, int[] booksBorrowingStatus) {
        String title = IO.readln("Enter the title of the book you want to borrow: ");
        for (int i = 0; i <= bookCount; i++) {
            if (books[i].getTitle().equals(title) && booksBorrowingStatus[i+1] == -1) {
                int membershipNumber = Integer.parseInt(IO.readln("Enter membership number of the borrower: "));
                for (int j = 0; j <= memberCount; j++) {
                    if (members[j].getMembershipNumber() == membershipNumber) {
                        if (members[j].isAllowedToBorrow()) {
                            booksBorrowingStatus[i + 1] = j;
                            IO.println("The book has been borrowed");
                        }
                        else
                            IO.println("The member has already reached the maximum number of borrowed books and " +
                                    "is not allowed to borrow another book at the moment");
                    }
                    else
                        IO.println("The membership number does not exist");
                }
            }
            else
                IO.println("The book does not exist or is already borrowed");
        }
    }

    static void returnBook(Book[] books, int bookCount, int[] booksBorrowingStatus) {
        String title = IO.readln("Enter the title of the book you want to return: ");
        for (int i = 0; i <= bookCount; i++) {
            if (books[i].getTitle().equals(title) && booksBorrowingStatus[i+1] != -1) {
                booksBorrowingStatus[i + 1] = -1;
                IO.println("The book has been returned");
            }
            else
                IO.println("The book does not exist or is not borrowed");
        }
    }

}
