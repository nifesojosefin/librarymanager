package com.laboration;

public class LibrarySystem {
    static void main() {
        Library library = new Library();
        library.setInitialValuesForBorrowingStatus();

        while (true) {
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
            String choice = IO.readln("Enter your choice: ");
            if (choice.equals("1")) {
                if (bookCount == books.length) {
                    books = expandBooksArray(books);
                    booksBorrowingStatus = expandBooksBorrowingStatusArray(booksBorrowingStatus, bookCount);
                }
                bookCount = addBook(books, booksBorrowingStatus, bookCount);
            }
            else if (choice.equals("2")) {
                if (memberCount == members.length)
                    members = expandMembersArray(members);
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
}
