import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookStore {
    public static void main(String[] args) {
        HashMap<String, String[]> peopleAndBooks = readFromFilePeopleAndBooks();
        HashMap<String, String[]> booksAndPeople = readFromFileBooksAndPeople();
        HashMap<String, Integer> booksTogether = readFromFileBooks2(peopleAndBooks);
        showInStringAmountOfTogetherBoughtBooks(booksTogether);
        makeTable(booksTogether);
//        makeTableTinglikToenaosus(booksTogether, booksAndPeople);
    }

    public static HashMap<String, String[]> readFromFilePeopleAndBooks() {
        HashMap<String, String[]> dictionary = new HashMap<>();
        try {
            File file = new File("C:\\Users\\danil\\git\\retail_data.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                String[] books = dictionary.getOrDefault(line[0], new String[0]);
                String[] updatedBooks = new String[books.length + 1];
                System.arraycopy(books, 0, updatedBooks, 0, books.length);
                updatedBooks[books.length] = line[1];
                dictionary.put(line[0], updatedBooks);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public static HashMap<String, String[]> readFromFileBooksAndPeople() {
        HashMap<String, String[]> dictionary = new HashMap<>();
        try {
            File file = new File("C:\\Users\\danil\\git\\retail_data.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                String[] people = dictionary.getOrDefault(line[1], new String[0]);
                String[] updatedPeople = new String[people.length + 1];
                System.arraycopy(people, 0, updatedPeople, 0, people.length);
                updatedPeople[people.length] = line[0];
                dictionary.put(line[1], updatedPeople);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public static HashMap<String, Integer> readFromFileBooks2(HashMap<String, String[]> peopleAndBooks) {
        HashMap<String, Integer> dictionary = new HashMap<>();
        for (String person : peopleAndBooks.keySet()) {
            String[] books = peopleAndBooks.get(person);
            for (int i = 0; i < books.length; i++) {
                for (int j = i + 1; j < books.length; j++) {
                    String key = books[i] + "&" + books[j];
                    int value = dictionary.getOrDefault(key, 0) + 1;
                    dictionary.put(key, value);
                }
            }
        }
        return dictionary;
    }

    public static void showInStringAmountOfTogetherBoughtBooks(HashMap<String, Integer> dictionary) {
        for (String key : dictionary.keySet()) {
            int value = dictionary.get(key);
            if (value > 1) {
                String[] splitted = key.split("&");
                System.out.println("Book with id: " + splitted[0] + " and " + splitted[1] + " were bought by " + value + " people");
            }
        }
    }

    // returns a Map<person1, [book1, book2]> person_id and list of books this person bought
    public static Map<String, String[]> readFromPeopleAndBooksFile(String filename) throws IOException {
        Map<String, String[]> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(",");
                String[] books = map.getOrDefault(splitted[0], new String[0]);
                String[] updatedBooks = new String[books.length + 1];
                System.arraycopy(books, 0, updatedBooks, 0, books.length);
                updatedBooks[books.length] = splitted[1];
                map.put(splitted[0], updatedBooks);
            }
        }
        return map;
    }

    // returns a Map<book1, [person1, person2]> book_id and list of people bought this book
    public static Map<String, String[]> readFromBooksAndPeopleFile(String filename) throws IOException {
        Map<String, String[]> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(",");
                String[] people = map.getOrDefault(splitted[1], new String[0]);
                String[] updatedPeople = new String[people.length + 1];
                System.arraycopy(people, 0, updatedPeople, 0, people.length);
                updatedPeople[people.length] = splitted[0];
                map.put(splitted[1], updatedPeople);
            }
        }
        return map;
    }

    // returns a Map<book1&book2, 2> both books and amount of people bought them
    public static Map<String, Integer> readFromBooks2(Map<String, String[]> map) {
        Map<String, Integer> res = new HashMap<>();
        for (String person : map.keySet()) {
            String[] books = map.get(person);
            for (int i = 0; i < books.length; i++) {
                String book1 = books[i];
                for (int j = i + 1; j < books.length; j++) {
                    String book2 = books[j];
                    String key = book1 + "&" + book2;
                    res.put(key, res.getOrDefault(key, 0) + 1);
                }
            }
        }
        return res;
    }

    // shows how many people bought two books
    public static void showInStringAmountOfTogetherBoughtBooks(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value > 1) {
                String[] splitted = key.split("&");
                System.out.printf("Book with id: %s and %s were bought by %d people%n", splitted[0], splitted[1], value);
            }
        }
    }

    public static void makeTable(Map<String, Integer> map) {
        System.out.printf("%-16s%-16s%-16s%n", "First Book", "Second Book", "Amount of persons bought");
        for (String key : map.keySet()) {
            String[] splitted = key.split("&");
            System.out.printf("%-16");
        }
    }
}