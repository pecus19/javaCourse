package ee.taltech.iti0202.exam.library;

import java.util.Optional;

public class Book {
    private String title;
    private String isbn;
    /**
     * Creates a new book with the given title and ISBN.
     */
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book("Truth and dare", "978-3-16-148410-0");
        Book book2 = new Book("Beauty and the Feast", "UGLY-123123");
        Book book3 = new Book("Phantom of the Java", "VOID-MAIN-123");
        Book book4 = new Book("What does the fox say?", "VOID-MAIN-123");
        Book book5 = new Book("a", "WAT-LOL");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        System.out.println(library.addBook(book4)); // false, already in library

        Book lendBook1 = library.lendBook("dare").get();
        System.out.println(lendBook1); // ee.ttu.iti0202.exam.library.Book@7225790e
        System.out.println(library.getBookAmounts()); // {UGLY-123123=1, VOID-MAIN-123=2}
        Book lendBook3 = library.lendBook("Java").get();
        System.out.println(lendBook3); // ee.ttu.iti0202.exam.library.Book@3ecf72fd

        System.out.println(library.getBookAmounts()); // {UGLY-123123=1, VOID-MAIN-123=1}
        System.out.println(library.returnBook(book3)); // true, same as lendBook3
        System.out.println(library.returnBook(lendBook1)); // true, same as book1
        System.out.println(library.returnBook(book1)); // false, already returned
        System.out.println(library.getBookLendCount(book1)); // 1
        System.out.println(library.getBookLendCount(book3)); // 1

        Optional<Book> lendBook5Optional = library.lendBook("a");
        System.out.println(library.getBookLendCount(book1)); // 2
        System.out.println(lendBook5Optional.get() == book1); // true, book1 was borrowed

        System.out.println(library.returnBook(book1)); // true
        System.out.println(library.returnBook(book5)); // false
        System.out.println(library.getBookLendCount(book5)); // -1, book5 not in library yet!

        library.addBook(book5);
        lendBook5Optional = library.lendBook("a"); // exact match
        System.out.println(lendBook5Optional.get() == book5); // true
    }


}
