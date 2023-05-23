package ee.taltech.iti0202.exam.library;

import java.util.*;

public class Library {
    List<Book> books = new ArrayList<>();
    private Map<String, Integer> booksMap = new HashMap<>();

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
        Optional<Book> output;
        output = books.stream().filter(book -> !book.isLend() | book.getTitle().equals(name)).findFirst();
        if (output.isEmpty()) {
            output = books.stream().filter(book -> book.getTitle().contains(name) | !book.isLend()).findFirst();
        }
        if (output.isPresent()) {
            Book book = output.get();
            if (!booksMap.containsKey(book.getIsbn())) {
                booksMap.put(book.getIsbn(), 1);
            } else {
                booksMap.put(book.getIsbn(), booksMap.get(book.getIsbn()) + 1);
            }
            book.setLend(true);
        }
        return output;
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
        if (books.contains(book) && book.isLend()) {
            book.setLend(false);
            booksMap.put(book.getIsbn(), booksMap.get(book.getIsbn()) + 1);
            return true;
        }
        return false;
    }

    /**
     * Returns a map of ISBN and corresponding count of available books.
     */
    public Map<String, Integer> getBookAmounts() {
        return booksMap;
    }

    /**
     * Returns how many times the book has been lent.
     * <p>
     * If the book is not in the library, returns -1.
     */
    public int getBookLendCount(Book book) {
        if (!booksMap.containsKey(book.getTitle())) {
            return -1;
        }
        return booksMap.get(book.getTitle());
    }
}
