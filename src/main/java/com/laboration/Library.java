package com.laboration;

public class Library {
        private Book[] books = new Book[10];
        private int bookCount = 0;
        private Member[] members = new Member[10];
        private int memberCount = 0;
        private int[] booksBorrowingStatus = new int[20];

    public void setInitialValuesForBorrowingStatus() {
        for (int i = 1; i < booksBorrowingStatus.length; i = i + 2)
            booksBorrowingStatus[i] = -1;
    }

    public void addBook() {
        if (bookCount >= books.length) {
            expandBooksArray();
            expandBooksBorrowingStatusArray();
        }
        String title = IO.readln("Enter the title of the book: ");
        String author = IO.readln("Enter the author of the book: ");
        String isbn = IO.readln("Enter the ISBN of the book (10 or 13 digits): ");
        try {
            books[bookCount] = new Book(title, author, isbn);
            IO.println("The book has been added");
            booksBorrowingStatus[bookCount + bookCount] = bookCount;
            bookCount++;
        }
        catch (IllegalArgumentException e) {
            IO.println("Invalid input. Please try again.");
        }
    }

    public void registerMember() {
        if (memberCount >= members.length) {
            expandMembersArray();
        }
        String name = IO.readln("Enter the name of the person: ");
        try {
            int membershipNumber = Integer.parseInt(IO.readln("Enter the membership number for the person: "));
            if (memberCount > 0)
                for (int i = 0; i < memberCount; i++) {
                    if (members[i].getMembershipNumber() == membershipNumber) {
                        IO.println("The membership number already exists. Please try again");
                    }
                }
            members[memberCount] = new Member(membershipNumber, name);
            IO.println("The member has been registered");
            memberCount++;
        }
        catch (IllegalArgumentException e) {
            IO.println("Invalid membership number. Please try again");
        }
    }

    public void borrowBook() {
        if (bookCount > 0 && memberCount > 0) {
            String title = IO.readln("Enter the title of the book you want to borrow: ");
            for (int i = 0; i < bookCount; i++) {
                if (books[i].getTitle().equalsIgnoreCase(title) && booksBorrowingStatus[i + 1] == -1) {
                    int membershipNumber = Integer.parseInt(IO.readln("Enter membership number of the borrower: "));
                    for (int j = 0; j < memberCount; j++) {
                        if (members[j].getMembershipNumber() == membershipNumber) {
                            if (members[j].isAllowedToBorrow()) {
                                booksBorrowingStatus[i + 1] = j;
                                members[j].addNumberOfBooksBorrowed();
                                IO.println("The book has been borrowed");
                            } else
                                IO.println("The member has already reached the maximum number of borrowed books and " +
                                        "is not allowed to borrow another book at the moment");
                        } else
                            IO.println("The membership number does not exist");
                    }
                } else
                    IO.println("The book does not exist or is already borrowed");
            }
        }
        else
            IO.println("Invalid operation. Please add books and members first.");
    }

    public void returnBook() {
        if (bookCount > 0 && isBooksBorrowed()) {
            String title = IO.readln("Enter the title of the book you want to return: ");
            for (int i = 0; i < bookCount; i++) {
                if (books[i].getTitle().equalsIgnoreCase(title) && booksBorrowingStatus[i + 1] != -1) {
                    booksBorrowingStatus[i + 1] = -1;
                    IO.println("The book has been returned");
                } else
                    IO.println("The book does not exist or is not borrowed");
            }
        }
        else
            IO.println("Invalid operation. There are no borrowed books to return.");
    }

    public void searchBook() {
        if (bookCount > 0) {
            String search = IO.readln("Enter the title or author of the book you want to search for: ");
            for (int i = 0; i < bookCount; i++) {
                if (books[i].getTitle().contains(search) || books[i].getAuthor().contains(search)) {
                    IO.println("Book found: " + books[i].getTitle() + " by " + books[i].getAuthor() +
                            " with ISBN: " + books[i].getIsbn());
                }
            }
            IO.println("No book matching the search was found");
        }
        else
            IO.println("Invalid operation. There are no books to search.");
    }

    public void printAllBooks() {
        if (bookCount > 0) {
            for (int i = 0; i < bookCount; i++) {
                IO.print(i + 1 + ". Title: " + books[i].getTitle() + ", author: " + books[i].getAuthor() +
                        ", ISBN: " + books[i].getIsbn());
                if (booksBorrowingStatus[i + 1] != -1) {
                    IO.println(". Status: borrowed by member number: " +
                            members[booksBorrowingStatus[i + 1]].getMembershipNumber() +
                            " (" + members[booksBorrowingStatus[i + 1]].getName() + ")");
                } else
                    IO.println(". Status: available to borrow");
            }
        }
        else
            IO.println("Invalid operation. There are no books to show.");
    }

    private boolean isBooksBorrowed() {
        for (int i = 1; i <= bookCount; i = i + 2) {
            if (booksBorrowingStatus[i] != -1)
                return true;
        }
        return false;
    }

    private void expandBooksArray() {
        Book[] newBooks = new Book[books.length * 2];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        books = newBooks;
    }

    private void expandMembersArray() {
        Member[] newMembers = new Member[members.length * 2];
        for (int i = 0; i < members.length; i++) {
            newMembers[i] = members[i];
        }
        members = newMembers;
    }

    private void expandBooksBorrowingStatusArray() {
        int[] newBooksBorrowingStatus = new int[booksBorrowingStatus.length * 2];
        for (int i = 0; i < booksBorrowingStatus.length; i++) {
            newBooksBorrowingStatus[i] = booksBorrowingStatus[i];
        }

        for (int i = booksBorrowingStatus.length + 1; i < newBooksBorrowingStatus.length; i = i + 2) {
            newBooksBorrowingStatus[i] = -1;
        }
        booksBorrowingStatus = newBooksBorrowingStatus;
    }
}