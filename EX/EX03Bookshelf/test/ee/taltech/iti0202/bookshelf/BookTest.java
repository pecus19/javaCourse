package ee.taltech.iti0202.bookshelf;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BookTest {

    Book book = new Book("Java", "Ago", 2023, 1);
    Book book2 = new Book("Python", "Danila", 2002, 10);
    Book book3 = new Book("C++", "Nikita", 2999, 1234);

    Person person = new Person("Siiii", 9);
    Person person2 = new Person("CR&", 1234);
    Person person3 = new Person("CR&", 1123233);

    @org.junit.jupiter.api.Test
    void checkId() {
        assertEquals(30, book3.getId());
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
        assertEquals(8, person.getMoney());
    }

    @org.junit.jupiter.api.Test
    void checkMoneyAfterSelling() {
        person.buyBook(book);
        person.sellBook(book);
        assertEquals(9, person.getMoney());
    }

    @org.junit.jupiter.api.Test
    void bookOfReturnNull() {
        Book.of("Book", "Me", 2023, 100);
        assertEquals(null, Book.of("Book", "Me", 2023, 100));
    }


    @org.junit.jupiter.api.Test
    void sellBookFalse() {
        person.buyBook(book);
        assertFalse(person2.sellBook(book));
    }

    @org.junit.jupiter.api.Test
    void basicBookOf() {
        assertEquals(100, Book.of("Book", "Me", 2023, 100).getPrice());
    }

    @org.junit.jupiter.api.Test
    void bookOfWithADoubleAdding() {
        Book.of("Book", "Me", 2023, 100);
        Book.of("Book2", "Me2", 2024, 120);
        assertEquals("Me2", Book.of("Book3", 12).getAuthor());
    }

    @org.junit.jupiter.api.Test
    void removeBook() {
        Book.of("Book", "Me", 2023, 100);
        Book book5 = new Book("Book", "Me", 2023, 100);
        book5.buy(person2);
        assertEquals(false, Book.removeBook(book5));
    }

    @org.junit.jupiter.api.Test
    void getBooksByAuthorTest() {
        Book.of("Pecus", "Pecus", 2023, 100);
        Book.of("Pecus3", 150);
        Book.of("Pecus4", 160);
        assertEquals(3, Book.getBooksByAuthor("Pecus").size());
    }

    @org.junit.jupiter.api.Test
    void allSetterTests() {
        Book book100 = new Book("Pecus", "Pecus", 2023, 100);
        book100.setAuthor("Den");
        book100.setPrice(0);
        book100.setTitle("Title");
        book100.setYearOfPublishing(2312);
        assertEquals("Den", book100.getAuthor());
        assertEquals(0, book100.getPrice());
        assertEquals("Title", book100.getTitle());
        assertEquals(2312, book100.getYearOfPublishing());
    }

}