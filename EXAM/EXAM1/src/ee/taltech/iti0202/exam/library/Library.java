package ee.taltech.iti0202.exam.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Library {
    List<Book> books = new ArrayList<>();

    /**
     * Adds a book to the library.
     * <p>
     * If the same book (same instance) is already in the library, return false.
     * Otherwise the book is added to the library and return true.
     */
    public boolean addBook(Book book) {
        if (books.contains(book)) {
            return false;
        } else {
            books.add(book);
            return true;
        }
    }


    /**
     * Tries to lend a book.
     * <p>
     * If there is an available book with the exact name, then this book is returned.
     * Otherwise, if there is an available book where the title contains the name,
     * then this book is returned.
     * Otherwise, nothing is returned.
     * <p>
     * If there is a book, then this book will not be available any more (it is lent out).
     */
    public Optional<Book> lendBook(String name) {
        return Optional.empty();
    }

    /**
     * Returns a book.
     * <p>
     * If the given book is lent from the library, returns it and returns true.
     * Otherwise returns false.
     * <p>
     * After returning, the book can be lent again.
     */
    public boolean returnBook(Book book) {
        return false;
    }

    /**
     * Returns a map of ISBN and corresponding count of available books.
     */
    public Map<String, Integer> getBookAmounts() {
        return null;
    }

    /**
     * Returns how many times the book has been lent.
     * <p>
     * If the book is not in the library, returns -1.
     */
    public int getBookLendCount(Book book) {
        return 0;
    }
}
