package ee.taltech.iti0202.bookshelf;

import org.junit.jupiter.api.Assertions;

public class PersonTest {
    public static final int SMALL = 1;
    public static final int BIG = 100000;
    public static final int NORMAL = 1000;
    public static final int ID = 30;
    Person person3 = new Person("CR&", BIG);
    Person person2 = new Person("CR&", NORMAL);

    @org.junit.jupiter.api.Test
    void getBooksByOwnerTest() {
        Book book99 = Book.of("Book", "Me", NORMAL, NORMAL);
        Book book98 = Book.of("Book", NORMAL);
        Book book97 = Book.of("Book", NORMAL);
        if (book98 != null && book97 != null && book99 != null) {
            person3.buyBook(book97);
            person3.buyBook(book98);
            person3.buyBook(book99);
        }
        Assertions.assertEquals(3, Book.getBooksByOwner(person3).size());
    }

    @org.junit.jupiter.api.Test
    void bookOfReturnNull2() {
        Assertions.assertEquals(null, Book.of("Book", NORMAL));
    }

    @org.junit.jupiter.api.Test
    void bookBuyReturnNullWithBuyerNull() {
        Book b1 = Book.of("Java EX01", "Ago Luberg", BIG, SMALL);
        Person bonusPerson = new Person("Joonas Boonus", BIG);
        Person bonusPerson2 = new Person("Joonas 2", NORMAL);
        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", BIG, BIG);
        if (b1 != null) {
            b1.buy(bonusPerson2);
            Assertions.assertTrue(b1.buy(null));
        }
    }

    @org.junit.jupiter.api.Test
    void bookBuyReturnNullWithBuyerNullFalse() {
        Book b1 = Book.of("Java EX01", "Ago Luberg", BIG, SMALL);
        Person bonusPerson = new Person("Joonas Boonus", SMALL);
        Person bonusPerson2 = new Person("Joonas 2", NORMAL);
        b1.buy(bonusPerson2);
        Assertions.assertFalse(b1.buy(bonusPerson));
    }

    @org.junit.jupiter.api.Test
    void bookOfWithNullAuthor() {
        Book java3 = Book.of("Java EX01", SMALL);
        Assertions.assertNull(java3);
    }

    @org.junit.jupiter.api.Test
    void removeBookThatDoesNotExist() {
        Book java3 = Book.of("Java EX01", SMALL);
        Assertions.assertFalse(Book.removeBook(java3));
    }

    @org.junit.jupiter.api.Test
    void bookOfAddSameBookReturnNull() {
        Book b1 = Book.of("Java2023", "Aga", NORMAL, SMALL);
        Assertions.assertNull(Book.of("Java2023", "Aga", NORMAL, SMALL));
    }

    @org.junit.jupiter.api.Test
    void removeBookTrue() {
        Book b172 = Book.of("b172", "Den12", NORMAL, SMALL);
        Person bonus21 = new Person("21", BIG);
        b172.buy(bonus21);
        Assertions.assertTrue(Book.removeBook(b172));
    }
    @org.junit.jupiter.api.Test
    void sellBookWithoutBuyingFalse() {
        Book b172 = Book.of("b172", "Den12", NORMAL, BIG);
        Person bonus31 = new Person("31", SMALL);
        bonus31.sellBook(b172);
        Assertions.assertEquals("31", bonus31.getName());
        Assertions.assertFalse(bonus31.sellBook(b172));
    }
}
