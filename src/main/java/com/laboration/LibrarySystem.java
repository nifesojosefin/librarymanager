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
            switch (choice) {
                case "1" -> library.addBook();
                case "2" -> library.registerMember();
                case "3" -> library.borrowBook();
                case "4" -> library.returnBook();
                case "5" -> library.searchBook();
                case "6" -> library.printAllBooks();
                case "e", "E" -> System.exit(0);
                default -> IO.println("Invalid choice. Please try again.");
            }
        }
    }
}
