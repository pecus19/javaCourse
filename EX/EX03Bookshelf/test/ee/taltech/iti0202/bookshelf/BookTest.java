package ee.taltech.iti0202.bookshelf;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BookTest {

    public static final int SMALL = 1;
    public static final int BIG = 100000;
    public static final int NORMAL = 1000;
    public static final int ID = 30;

    Book book = new Book("Java", "Ago", BIG, SMALL);
    Book book2 = new Book("Python", "Danila", BIG, NORMAL);
    Book book3 = new Book("C++", "Nikita", BIG, BIG);

    Person person = new Person("Siiii", SMALL);
    Person person2 = new Person("CR&", NORMAL);
    Person person3 = new Person("CR&", BIG);

    @org.junit.jupiter.api.Test
    void checkId() {
        assertEquals(ID, book3.getId());
    }

    @org.junit.jupiter.api.Test
    void basicBuyTestTrue() {
        assertEquals(true, person.buyBook(book));
    }

    @org.junit.jupiter.api.Test
    void basicBuyTestFalse() {
        person.buyBook(book);
        assertEquals(false, person.buyBook(book));
    }

    @org.junit.jupiter.api.Test
    void buyBookTrue() {
        assertEquals(true, book3.buy(person2));
    }

    @org.junit.jupiter.api.Test
    void buyBookFalse() {
        assertEquals(true, book2.buy(person));
    }

    @org.junit.jupiter.api.Test
    void checkMoneyAfterBuying() {
        person.buyBook(book);
        assertEquals(0, person.getMoney());
    }

    @org.junit.jupiter.api.Test
    void checkMoneyAfterSelling() {
        person.buyBook(book);
        person.sellBook(book);
        assertEquals(SMALL, person.getMoney());
    }

    @org.junit.jupiter.api.Test
    void bookOfReturnNull() {
        Book.of("Book", "Me", BIG, NORMAL);
        assertEquals(null, Book.of("Book", "Me", BIG, NORMAL));
    }


    @org.junit.jupiter.api.Test
    void sellBookFalse() {
        person.buyBook(book);
        assertFalse(person2.sellBook(book));
    }

    @org.junit.jupiter.api.Test
    void basicBookOf() {
        assertEquals(NORMAL, Book.of("Book", "Me", NORMAL, NORMAL).getPrice());
    }

    @org.junit.jupiter.api.Test
    void bookOfWithADoubleAdding() {
        Book.of("Book", "Me", NORMAL, NORMAL);
        Book.of("Book2", "Me2", NORMAL, NORMAL);
        assertEquals("Me2", Book.of("Book3", NORMAL).getAuthor());
    }

    @org.junit.jupiter.api.Test
    void removeBook() {
        Book.of("Book", "Me", BIG, NORMAL);
        Book book5 = new Book("Book", "Me", BIG, NORMAL);
        book5.buy(person2);
        assertEquals(false, Book.removeBook(book5));
    }

    @org.junit.jupiter.api.Test
    void getBooksByAuthorTest() {
        Book.of("Pecus", "Pecus", NORMAL, NORMAL);
        Book.of("Pecus3", NORMAL);
        Book.of("Pecus4", NORMAL);
        assertEquals(3, Book.getBooksByAuthor("Pecus").size());
    }

    @org.junit.jupiter.api.Test
    void allSetterTests() {
        Book book100 = new Book("Pecus", "Pecus", BIG, NORMAL);
        book100.setAuthor("Den");
        book100.setPrice(NORMAL);
        book100.setTitle("Title");
        book100.setYearOfPublishing(NORMAL);
        assertEquals("Den", book100.getAuthor());
        assertEquals(NORMAL, book100.getPrice());
        assertEquals("Title", book100.getTitle());
        assertEquals(NORMAL, book100.getYearOfPublishing());
    }
}
