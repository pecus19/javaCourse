package ee.taltech.iti0202.springboot.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int money;
    private List<Book> books = new ArrayList<>();

    /**
     * @param name  name
     * @param money money
     */
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
        if (book != null && book.getOwner() == null && book.getPrice() <= getMoney()) {
            setMoney(getMoney() - book.getPrice());
            book.setOwner(this);
            books.add(book);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param book book
     * @return false
     */
    public boolean sellBook(Book book) {
        if (book != null && book.getOwner() == null) {
            return false;
        }
        if (book != null && book.getOwner() == this) {
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
