package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    public static int idCounter = 0;
    private final int id;
    private Person owner;
    private static List<Book> books = new ArrayList<>();
    private static String prevAuthor = "";
    private static Integer prevYearOfPublishing = 0;

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        id = getAndIncrementNextId();
    }

    public static int getAndIncrementNextId() {
        return idCounter++;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public Person getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public boolean buy(Person buyer) {
        if (buyer != null) {
            if (buyer.getMoney() >= getPrice() & getOwner() != buyer) {
                buyer.setMoney(buyer.getMoney() - getPrice());
                if (getOwner() != null) {
                    getOwner().setMoney(getOwner().getMoney() + getPrice());
                }
                setOwner(buyer);
                return true;
            }
        } else {
            return false;
        }
        return false;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //Second part
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        if (books.size() >= 1) {
            for (int i = 0; i < books.size(); i++) {
                if (!Objects.equals(books.get(i).getTitle(), title) && !Objects.equals(books.get(i).getAuthor(), author)) {
                    Book book1 = new Book(title, author, yearOfPublishing, price);
                    books.add(book1);
                    prevAuthor = author;
                    prevYearOfPublishing = yearOfPublishing;
                    return book1;
                } else {
                    return null;

                }
            }
            return null;
        } else {
            Book book1 = new Book(title, author, yearOfPublishing, price);
            books.add(book1);
            prevAuthor = author;
            prevYearOfPublishing = yearOfPublishing;
            return book1;
        }
    }

    public static Book of(String title, int price) {
        if (prevAuthor.length() != 0 & prevYearOfPublishing != 0) {
            Book book1 = new Book(title, prevAuthor, prevYearOfPublishing, price);
            books.add(book1);
            return book1;
        } else {
            return null;
        }
    }


    public static List<Book> getBooksByOwner(Person owner) {
        List<Book> output = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getOwner() == owner) {
                output.add(books.get(i));
            }
        }
        return output;
    }

    public static boolean removeBook(Book book) {
        if (books.contains(book)) {
            book.getOwner().sellBook(book);
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "title='" + title + '\'' +
//                ", author='" + author + '\'' +
//                ", yearOfPublishing=" + yearOfPublishing +
//                ", price=" + price +
//                ", id=" + id +
//                ", owner=" + owner +
//                '}';
//    }

    public static List<Book> getBooksByAuthor(String author) {
        List<Book> output = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().toLowerCase(Locale.ROOT).equals(author.toLowerCase(Locale.ROOT))) {
                output.add(books.get(i));
            }
        }
        return output;
    }
}