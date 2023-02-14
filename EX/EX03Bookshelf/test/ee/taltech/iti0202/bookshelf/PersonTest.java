package ee.taltech.iti0202.bookshelf;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person3 = new Person("CR&", 1123233);
    Person person2 = new Person("CR&", 1234);

    @org.junit.jupiter.api.Test
    void getBooksByOwnerTest() {
        Book book99 = Book.of("Book", "Me", 2023, 100);
        Book book98 = Book.of("Book", 100);
        Book book97 = Book.of("Book", 100);
        if (book98 != null && book97 != null && book99 != null) {
            person3.buyBook(book97);
            person3.buyBook(book98);
            person3.buyBook(book99);
        }
        assertEquals(3, Book.getBooksByOwner(person3).size());
    }

    @org.junit.jupiter.api.Test
    void bookOfReturnNull2() {
        assertEquals(null, Book.of("Book", 100));
    }

    @org.junit.jupiter.api.Test
    void bookBuyReturnNullWithBuyerNull() {
        Book b1 = Book.of("Java EX01", "Ago Luberg", 2018, 3);
        Person bonusPerson = new Person("Joonas Boonus", 10000);
        Person bonusPerson2 = new Person("Joonas 2", 100);
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", 1997, 1000);
        if (b1 != null) {
            b1.buy(bonusPerson2);
            assertTrue(b1.buy(null));
        }
    }

    @org.junit.jupiter.api.Test
    void bookBuyReturnNullWithBuyerNullFalse() {
        Book b1 = Book.of("Java EX01", "Ago Luberg", 2018, 3);
        Person bonusPerson = new Person("Joonas Boonus", 1);
        Person bonusPerson2 = new Person("Joonas 2", 100);
        b1.buy(bonusPerson2);
        assertFalse(b1.buy(bonusPerson));
    }

    @org.junit.jupiter.api.Test
    void bookOfWithNullAuthor() {
        Book java3 = Book.of("Java EX01", 3);
        assertNull(java3);
    }

    @org.junit.jupiter.api.Test
    void removeBookThatDoesNotExist() {
        Book java3 = Book.of("Java EX01", 3);
        assertFalse(Book.removeBook(java3));
    }

    @org.junit.jupiter.api.Test
    void bookOfAddSameBookReturnNull() {
        Book b1 = Book.of("Java2023", "Aga", 2018, 3);
        assertNull(Book.of("Java2023", "Aga", 2018, 3));
    }

    @org.junit.jupiter.api.Test
    void removeBookTrue() {
        Book b172 = Book.of("b172", "Den12", 2018, 3);
        Person bonus21 = new Person("21", 10920);
        b172.buy(bonus21);
        assertTrue(Book.removeBook(b172));
    }
    @org.junit.jupiter.api.Test
    void sellBookWithoutBuyingFalse() {
        Book b172 = Book.of("b172", "Den12", 2018, 3);
        Person bonus31 = new Person("31", 1);
        bonus31.sellBook(b172);
        assertEquals("31", bonus31.getName());
        assertFalse(bonus31.sellBook(b172));
    }


}
