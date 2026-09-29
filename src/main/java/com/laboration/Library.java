package com.laboration;

public class Library {
    static void main() {
        Book[] books = new Book[20];
        int bookCount = 0;
        Member[] members = new Member[10];
        int memberCount = 0;
        int[] booksBorrowingStatus = new int[40];

        setInitialValuesForBorrowingStatus(booksBorrowingStatus);

        while (true) {
            printMenu();
            String choice = IO.readln("Enter your choice: ");
            if (choice.equals("1")) {
                bookCount = addBook(books, booksBorrowingStatus, bookCount);
            }
            else if (choice.equals("2")) {
                memberCount = registerMember(members, memberCount);
            }
            else if (choice.equals("3")) {
                if (bookCount > 0 && memberCount > 0)
                    borrowBook(books, bookCount, members, memberCount, booksBorrowingStatus);
                else
                    IO.println("Invalid operation. Please add books and members first.");
            }
            else if (choice.equals("4")) {
                if (bookCount > 0 && isBooksBorrowed(booksBorrowingStatus, bookCount))
                    returnBook(books, bookCount, booksBorrowingStatus);
                else
                    IO.println("Invalid operation. There are no borrowed books to return.");
            }
            else if (choice.equals("5")) {
                if (bookCount > 0)
                    searchBook(books, bookCount);
                else
                    IO.println("Invalid operation. There are no books to search.");
            }
            else if (choice.equals("6")) {
                if (bookCount > 0)
                    printAllBooks(books, bookCount, members, booksBorrowingStatus);
                else
                    IO.println("Invalid operation. There are no books to show.");
            }
            else if (choice.equalsIgnoreCase("e")) {
                IO.println("Exiting the program...");
                return;
            }
            else
                IO.println("Invalid choice. Please try again.");

            IO.println();
        }
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

    static int addBook(Book[] books, int[] booksBorrowingStatus, int bookCount) {
        String title = IO.readln("Enter the title of the book: ");
        String author = IO.readln("Enter the author of the book: ");
        try {
            String isbn = IO.readln("Enter the ISBN of the book: ");
            books[bookCount] = new Book(title, author, isbn);
            IO.println("The book has been added");
            if (bookCount > 0)
                booksBorrowingStatus[bookCount + 1] = bookCount;
            return ++bookCount;
        }
        catch (IllegalArgumentException e) {
            IO.println("Invalid ISBN. Please try again.");
        }
        return bookCount;
    }

    static int registerMember(Member[] members, int memberCount) {
        String name = IO.readln("Enter the name of the person: ");
        int membershipNumber = Integer.parseInt(IO.readln("Enter the membership number for the person: "));
        if (memberCount > 0)
            for (int i = 0; i < memberCount; i++) {
                if (members[i].getMembershipNumber() == membershipNumber) {
                    IO.println("The membership number already exists. Please try again");
                    return memberCount;
                }
            }
        members[memberCount] = new Member(membershipNumber, name);
        IO.println("The member has been registered");
        return ++memberCount;
    }

    static void borrowBook(Book[] books, int bookCount, Member[] members, int memberCount, int[] booksBorrowingStatus) {
        String title = IO.readln("Enter the title of the book you want to borrow: ");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equals(title) && booksBorrowingStatus[i+1] == -1) {
                int membershipNumber = Integer.parseInt(IO.readln("Enter membership number of the borrower: "));
                for (int j = 0; j < memberCount; j++) {
                    if (members[j].getMembershipNumber() == membershipNumber) {
                        if (members[j].isAllowedToBorrow()) {
                            booksBorrowingStatus[i + 1] = j;
                            members[j].addNumberOfBooksBorrowed();
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
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equals(title) && booksBorrowingStatus[i+1] != -1) {
                booksBorrowingStatus[i + 1] = -1;
                IO.println("The book has been returned");
            }
            else
                IO.println("The book does not exist or is not borrowed");
        }
    }

    static void searchBook(Book[] books, int bookCount) {
        String search = IO.readln("Enter the title or author of the book you want to search for: ");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().contains(search) || books[i].getAuthor().contains(search)) {
                IO.println("Book found: " + books[i].getTitle() + " by " + books[i].getAuthor() +
                        " with ISBN: " + books[i].getIsbn());
            }
        }
        IO.println("No book matching the search was found");
    }

    static void printAllBooks(Book[] books, int bookCount, Member[] members, int[] booksBorrowingStatus) {
        for (int i = 0; i < bookCount; i++) {
            IO.print(i+1 + ". Title: " + books[i].getTitle() + ", author: " + books[i].getAuthor() +
                    ", ISBN: " + books[i].getIsbn());
            if (booksBorrowingStatus[i+1] != -1) {
                IO.println(". Status: borrowed by member number: " +
                        members[booksBorrowingStatus[i+1]].getMembershipNumber() +
                        " (" + members[booksBorrowingStatus[i+1]].getName() + ")");
            }
            else
                IO.println(". Status: available to borrow");
        }
    }

    static boolean isBooksBorrowed(int[] booksBorrowingStatus, int bookCount) {
        for (int i = 1; i <= bookCount; i = i + 2) {
            if (booksBorrowingStatus[i] != -1)
                return true;
        }
        return false;
    }
}
