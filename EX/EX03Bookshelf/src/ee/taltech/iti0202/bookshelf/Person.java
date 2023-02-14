package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int money;
    private List<Book> books = new ArrayList<>();

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    /**
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param book book
     * @return false
     */
    public boolean buyBook(Book book) {
        if (book != null && book.getPrice() <= getMoney()) {
            if (book.getOwner() == null) {
                setMoney(getMoney() - book.getPrice());
                book.setOwner(this);
                books.add(book);
                return true;
            }
        }
        return false;
    }

    /**
     * @param book book
     * @return false
     */
    public boolean sellBook(Book book) {
        if (book != null && book.getOwner() == null) {
            return false;
        }
        if (book.getOwner() == this & book != null) {
            setMoney(getMoney() + book.getPrice());
            book.setOwner(null);
            books.remove(book);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param money money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    //Second part

    /**
     * @return books
     */
    public List<Book> getBooks() {
        return books;
    }
}