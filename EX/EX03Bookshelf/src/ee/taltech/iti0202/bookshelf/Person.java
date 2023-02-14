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

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public boolean buyBook(Book book) {
        if (book.getPrice() <= getMoney() & book != null) {
            if (book.getOwner() == null) {
                setMoney(getMoney() - book.getPrice());
                book.setOwner(this);
                books.add(book);
                return true;
            }
        }
        return false;
    }

    public boolean sellBook(Book book) {
        if (book.getOwner() == this & book != null) {
            setMoney(getMoney() + book.getPrice());
            book.setOwner(null);
            books.remove(book);
            return true;
        } else {
            return false;
        }
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //Second part

    public List<Book> getBooks() {
        return books;
    }
}