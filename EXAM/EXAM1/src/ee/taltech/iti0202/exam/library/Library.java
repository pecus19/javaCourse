package ee.taltech.iti0202.exam.library;

import java.util.*;

public class Library {
    List<Book> books = new ArrayList<>();
    Map<Book, Integer> booksMap = new HashMap<>();

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
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(name) && !books.get(i).isLend()) {
                if (!booksMap.containsKey(books.get(i))) {
                    booksMap.put(books.get(i), 1);
                } else {
                    booksMap.put(books.get(i), booksMap.get(books.get(i)) + 1);
                }
                return Optional.of(books.get(i));
            }
        }
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
        if (book.isLend()) {
            book.setLend(false);
            return true;
        } else {
            return false;
        }
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
        if (!booksMap.containsKey(book)) {
            return -1;
        } else {
            booksMap.get(book);
        }
        return -1;
    }
}
