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

    /**
     * @param owner owner
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * @param title            title
     * @param author           author
     * @param yearOfPublishing yearOfPublishing
     * @param price            price
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        id = getAndIncrementNextId();
    }

    /**
     * @return idCounter
     */
    public static int getAndIncrementNextId() {
        return idCounter++;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return yearOfPublishing
     */
    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    /**
     * @return owner
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param buyer buyer
     * @return false
     */
    public boolean buy(Person buyer) {
        if (getOwner() == buyer) {
            return false;
        } else if (buyer == null) {
            getOwner().sellBook(this);
            return true;
        } else if (getOwner() == null) {
            buyer.buyBook(this);
            return true;
        } else if (getPrice() >= buyer.getMoney()) {
            return false;
        } else {
            getOwner().sellBook(this);
            buyer.buyBook(this);
            return true;
        }

    }

    /**
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param author author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @param yearOfPublishing yearOfPublishing
     */
    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    /**
     * @param price price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @param title            title
     * @param author           author
     * @param yearOfPublishing yearOfPublishing
     * @param price            price
     * @return null
     */
    //Second part
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        if (books.size() >= 1) {
            for (int i = 0; i < books.size(); i++) {
                if (!Objects.equals(books.get(i).getTitle(), title)
                        && !Objects.equals(books.get(i).getAuthor(), author)) {
                    Book book1 = new Book(title, author, yearOfPublishing, price);
                    if (!books.contains(book1)) {
                        books.add(book1);
                        prevAuthor = author;
                        prevYearOfPublishing = yearOfPublishing;
                        return book1;
                    }
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

    /**
     * @param title title
     * @param price price
     * @return null
     */
    public static Book of(String title, int price) {
        if (prevAuthor.length() != 0 & prevYearOfPublishing != 0) {
            Book book1 = new Book(title, prevAuthor, prevYearOfPublishing, price);
            if (!books.contains(book1)) {
                books.add(book1);
                return book1;
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * @param owner owner
     * @return output
     */
    public static List<Book> getBooksByOwner(Person owner) {
        List<Book> output = new ArrayList<>();
        if (owner != null) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getOwner() == owner) {
                    output.add(books.get(i));
                }
            }
        }
        return output;
    }

    /**
     * @param book book
     * @return false
     */
    public static boolean removeBook(Book book) {
        if (book != null) {
            if (books.contains(book)) {
                book.getOwner().sellBook(book);
                return true;
            }
        } else {
            return false;
        }
        return false;
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

    /**
     * @param author author
     * @return output
     */
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