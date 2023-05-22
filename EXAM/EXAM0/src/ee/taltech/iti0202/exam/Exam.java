package ee.taltech.iti0202.exam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exam {

    /**
     * 01
     * <p>
     * For each multiple of 10 in the given array,
     * change all the values following it to be that multiple of 10,
     * until encountering another multiple of 10.
     * So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.
     * <p>
     * tenRun([2, 10, 3, 4, 20, 5]) => [2, 10, 10, 10, 20, 20]
     * tenRun([10, 1, 20, 2]) => [10, 10, 20, 20]
     * tenRun([10, 1, 9, 20]) => [10, 10, 10, 20]
     */
    public static List<Integer> tenRun(List<Integer> nums) {
        List<Integer> output = new ArrayList<>();
        Integer value = null;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) % 10 == 0) {
                value = nums.get(i);
                output.add(value);
            } else {
                if (value == null) {
                    output.add(nums.get(i));
                } else {
                    output.add(value);
                }
            }
        }
        return output;
    }


    /**
     * 02
     * <p>
     * Write a method that analyzes input String and returns all pairs of same letter that is present as lower-case and upper-case in input String.
     * Returned letter pairs have to be in alphabetic order. If there are multiple same letter pairs, then return only one. If there are no suitable pairs, return "".
     * Take latin alphabet 'a' - 'z' as base.
     * mixedPairs("abcABD") => "AaBb" (a and b are represented by both lowe and upper cased letter)
     * mixedPairs("aaaAAAaaaa") => "Aa"
     * mixedPairs("tere") => ""
     * mixedPairs("bBaacA") => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
     *
     * @param input
     * @return
     */
    public static String mixedPairs(String input) {
        TreeMap<String, String> map = new TreeMap<>();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.contains(input.substring(i, i + 1).toLowerCase(Locale.ROOT))
                    && input.contains(input.substring(i, i + 1).toUpperCase(Locale.ROOT))) {
                if (!map.containsKey(input.substring(i, i + 1).toLowerCase(Locale.ROOT))) {
                    map.put(input.substring(i, i + 1).toLowerCase(Locale.ROOT), (input.substring(i, i + 1).toUpperCase(Locale.ROOT))
                            + input.substring(i, i + 1).toLowerCase(Locale.ROOT));
                }
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            output.append(entry.getValue());
        }
        return output.toString();
    }

    /**
     * Given a list of numbers, count how many 2-s are alone (no 2 before or after it).
     * <p>
     * countSingleTwos([2, 2, 1, 3]) => 0
     * countSingleTwos([7, 6, 1, 3]) => 0
     * countSingleTwos([2, 2, 1, 2]) => 1
     * countSingleTwos([2, 2, 2, 1, 3, 2, 1, 2]) => 2
     */
    public static int countSingleTwos(List<Integer> numbers) {
        int counter = 0;
        if (numbers.get(0).equals(2) && !numbers.get(1).equals(2)) {
            counter++;
        }
        if (numbers.get(numbers.size() - 1).equals(2) && !numbers.get(numbers.size() - 2).equals(2)) {
            counter++;
        }
        for (int i = 1; i < numbers.size() - 1; i++) {
            if (numbers.get(i).equals(2)) {
                if (!Objects.equals(numbers.get(i), numbers.get(i - 1))
                        && !Objects.equals(numbers.get(i), numbers.get(i + 1))) {
                    counter++;
                }

            }
        }
        return counter;
    }

    /**
     * Write a method that takes a string and decodes it.
     * The string may contain some numbers.
     * All numbers need to be replaced with a corresponding letter from the alphabet.
     * Each number n references to n-th character of the lowercase alphabet (abcdefghijklmnopqrstuvwxyz).
     * If n is out of bounds, then it should start from "a" again. (0, 26 and 52 correspond to "a")
     * <p>
     * Examples:
     * decodeMessage("0") => "a"
     * decodeMessage("0b2d4f6") => "abcdefg"
     * decodeMessage("h8") => "hi"
     * decodeMessage("11o11") => "lol"
     * decodeMessage("h8 th4r30 p17ogramme43") => "hi there programmer"
     * decodeMessage(":14 19h8s 8s 84e45t34n58 54oo37e523423") => ":o this is getting cooler"
     * decodeMessage("This one doesn't need to be changed!") => "This one doesn't need to be changed!"
     *
     * @param message the message that needs to be decoded
     * @return decoded message
     */
    public static String decodeMessage(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder output = new StringBuilder();
        StringBuilder check = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            if (Character.isDigit(character)) {
                check.append(character);
            } else {
                if (check.length() > 0) {
                    int alpIndex = Integer.parseInt(String.valueOf(check)) % alphabet.length();
                    output.append(alphabet.charAt(alpIndex));
                    check.delete(0, check.length());
                    output.append(character);
                } else {
                    output.append(character);
                }
            }
            if (i == message.length() - 1) {
                if (check.length() > 0) {
                    int alpIndex = Integer.parseInt(String.valueOf(check)) % alphabet.length();
                    output.append(alphabet.charAt(alpIndex));
                }
            }
        }
        return output.toString();
    }

    /**
     * Return a list that contains the exact same numbers as the given list,
     * but rearranged so that all the zeros are grouped at the start of the list.
     * <p>
     * The order of the non-zero numbers does not matter.
     * So [1, 0, 0, 1] becomes [0 ,0, 1, 1].
     * You may modify and return the given list or make a new list.
     * <p>
     * zeroFront([1, 0, 0, 1]) => [0, 0, 1, 1]
     * zeroFront([0, 1, 1, 0, 1]) => [0, 0, 1, 1, 1]
     * zeroFront([1, 0]) => [0, 1]
     *
     * @param numbers list of integers
     * @return "ordered" list
     */
    public static List<Integer> zeroFront(List<Integer> numbers) {
        List<Integer> output = new ArrayList<>();
        List<Integer> numbersFake = new ArrayList<>(numbers);
        long counter;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).equals(0)) {
                counter = numbersFake.stream().filter(e -> e.equals(0)).count();
                if (counter != 0) {
                    for (int j = 0; j < counter; j++) {
                        output.add(0);
                    }
                }
                numbersFake.removeAll(Collections.singleton(0));
            }
        }
        output.addAll(numbersFake);
        return output;
    }

    /**
     * You are given a string as an input where which represents a sequence of numbers in the format `num, num, num`
     * Make a function frequencyBasedSort that returns a list where the most popular numbers of the input
     * are at the front and the least popular numbers are at the back of the list.
     * If two numbers are equally popular then the bigger number must come first.
     * <p>
     * Examples:
     * frequencyBasedSort("3,1") => {3, 1}
     * frequencyBasedSort("3,3,2,4,5,1,5") => {5, 5, 3, 3, 4, 2, 1}
     * frequencyBasedSort("1,2,3,4,5,1,2,2,3,3,1") => {3, 3, 3, 2, 2, 2, 1, 1, 1, 5, 4}
     *
     * @param input the sequence of numbers as a string, separate the numbers by coma and leave no empty spaces
     * @return a list that is sorted by to number popularity
     */
    public static List<Integer> frequencyBasedSort(String input) {
        List<Integer> output = new ArrayList<>();
        long counter;
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < input.length(); i += 2) {
            Integer key = Integer.parseInt(String.valueOf(input.charAt(i)));
//            output.add(key);
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
//        Stream<Map.Entry<Integer, Integer>> sorted =
//                map.entrySet().stream()
//                        .sorted(Map.Entry.comparingByValue()); how to reverse the result of this code
        //reversed
        Stream<Map.Entry<Integer, Integer>> sorted =
                map.entrySet().stream()
                        .sorted(Map.Entry.<Integer, Integer>comparingByValue()
                                .thenComparing(Map.Entry.<Integer, Integer>comparingByKey()).reversed());
        System.out.println(sorted.collect(Collectors.toList()).get(0));
        for (int i = 0; i < sorted.collect(Collectors.toList()).size(); i++) {
            String str = sorted.collect(Collectors.toList()).get(i).toString();
            String[] parts = str.split("=");
            Integer key = Integer.valueOf(parts[0]);
            Integer value = Integer.valueOf(parts[1]);
            for (int j = 0; j < value; j++) {
                output.add(key);
            }
        }
        return output;
    }

    /**
     * Find the prime factors of a number and return the sum of all the factors.
     * 0 and 1 are not prime.
     * <p>
     * Examples:
     * primeFactorsSum(10) => 2 + 5 => 7
     * Prime factors of 10 are 2 * 5 => 2 + 5 => answer is 7
     * <p>
     * primeFactorsSum(102) => 22
     * Prime factors of 102 are 2 * 3 * 17 => 2 + 3 + 17 => answer is 22
     * <p>
     * primeFactorsSum(1) => 0
     * primeFactorsSum(40) => 11 (2 + 2 + 2 + 5)
     * primeFactorsSum(8881) => 190
     * primeFactorsSum(999961) => 999961
     *
     * @param num input number. 1 <= num <= 1000000
     * @return sum of all prime factors
     */
    public static int primeFactorsSum(int num) {
        List<Integer> primeNumbers = new ArrayList<>(List.of(2, 3, 5, 7));
        int output = 0;
        int newNum = num;
        for (int i = 8; i < 1000000; i++) {
            int counter = 0;
            if (i % 2 == 0) {
                counter++;
            }
            if (i % 3 == 0) {
                counter++;
            }
            if (i % 5 == 0) {
                counter++;
            }
            if (i % 7 == 0) {
                counter++;
            }
            if (counter == 0) {
                primeNumbers.add(i);
            }
        }
        while (newNum != 1) {
            for (int i = 0; i < primeNumbers.size(); i++) {
                if (newNum % primeNumbers.get(i) == 0) {
                    newNum = newNum / primeNumbers.get(i);
                    output += primeNumbers.get(i);
                    i = -1;
                }
            }
        }
        return output;
    }
//    public class Order {
//        private int orderId;
//        private boolean orderCancellation = false;
//        private List<Product> productList = new ArrayList<>();
//        private static int nextFreeOrder = 0;
//
//        /**
//         * constructor
//         */
//        public Order() {
//            this.orderId = ++nextFreeOrder;
//        }
//
//        /**
//         * add new product to order
//         *
//         * @param product product
//         */
//        public void addProduct(Product product) {
//            productList.add(product);
//        }
//
//        /**
//         * order id getter
//         *
//         * @return id
//         */
//        public int getOrderId() {
//            return orderId;
//        }
//
//        /**
//         * calcel getter
//         *
//         * @return boolean
//         */
//        public boolean isOrderCancellation() {
//            return orderCancellation;
//        }
//
//
//        /**
//         * calcel setter
//         *
//         * @param orderCancellation boolean
//         */
//        public void setOrderCancellation(boolean orderCancellation) {
//            this.orderCancellation = orderCancellation;
//        }
//
//        /**
//         * product list getter
//         *
//         * @return list
//         */
//        public List<Product> getProductList() {
//            return productList;
//        }
//
//        /**
//         * get sum of all products
//         *
//         * @return sum
//         */
//        public int getProductsSum() {
//            return productList.stream()
//                    .mapToInt(p -> p.getPrice())
//                    .sum();
//        }
//
//        /**
//         * ,ethod cancel order
//         *
//         * @param shop shop
//         */
//        public void cancelOrder(Shop shop) {
//            setOrderCancellation(true);
//            productList.forEach(product -> product.setInUse(false));
//            productList.clear();
//
//        }
//    }
//public class Product {
//    private String name;
//    private int price;
//    private boolean inUse = false;
//
//    /**
//     * constructor
//     *
//     * @param name  name
//     * @param price price
//     */
//    public Product(String name, int price) {
//        this.name = name;
//        this.price = price;
//    }
//
//    /**
//     * name getter
//     *
//     * @return name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * name setter
//     *
//     * @param name name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * price getter
//     *
//     * @return price
//     */
//    public int getPrice() {
//        return price;
//    }
//
//    /**
//     * price setter
//     *
//     * @param price price
//     */
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    /**
//     * in use getter
//     *
//     * @return boolean
//     */
//    public boolean isInUse() {
//        return inUse;
//    }
//
//    /**
//     * in use setter
//     *
//     * @param inUse boolean
//     */
//    public void setInUse(boolean inUse) {
//        this.inUse = inUse;
//    }
//}
//public class Shop {
//    private List<Order> orderList = new ArrayList<>();
//    private List<Product> productList = new ArrayList<>();
//    private Map<Integer, Order> orderMap = new HashMap<>();
//    private Map<String, Product> productMap = new HashMap<>();
//
//    /**
//     * constructor
//     */
//    public Shop() {
//    }
//
//    /**
//     * toode lisatakse poodi. Kui täpselt sama instants on juba poes olemas, tagastab false.
//     * Kõikidel muudel juhtudel lisab toote poodi ja tagastab true.
//     *
//     * @param product product
//     * @return boolean
//     */
//    public boolean addProduct(Product product) {
//        if (!productList.contains(product)) {
//            productList.add(product);
//            productMap.put(product.getName(), product);
//            return true;
//        }
//        return false;
//    }
//
//
//    /**
//     * loob uue tellimuse ja tagastab selle numbri. Tellimuse numbrid on järjest täisarvud alates 1-st.
//     * Ehk siis esimene tellimus on numbriga 1, teine tellimus numbriga 2 jne. Ülesande jaoks võite luua eraldi klassi
//     * Order, aga saab lahendada ka ilma selleta.
//     *
//     * @return num
//     */
//    public int createNewOrder() {
//        Order order = new Order();
//        orderList.add(order);
//        orderMap.put(order.getOrderId(), order);
//        return order.getOrderId();
//    }
//
//    /**
//     * lisab toote tellimusse. Kui sellise numbriga tellimust ei ole, tagastab false.
//     * Kui sellise nimega (täpne vaste) toode on olemas ja ei ole mõnes teises tellimuses, siis lisatakse kõige
//     * odavam selle nimega toode tellimusse ja tagastab true. Kui toode on lisatud ühte tellimusse, siis seda
//     * enam teise tellimusse lisada ei saa. Kui sellise nimega toodet ei ole (või kõik on juba mõnes tellimuses),
//     * siis tagastab false. Kui tellimus on tühistatud (vt. altpoolt), siis toodet ei lisata ja tagastab false
//     *
//     * @param orderNumber order number
//     * @param itemName    item name
//     * @return boolean
//     */
//    public boolean addProductToOrder(int orderNumber, String itemName) {
//        if (!orderMap.containsKey(orderNumber)) {
//            return false;
//        } else if (productMap.get(itemName).isInUse()) {
//            return false;
//        } else if (orderMap.get(orderNumber).isOrderCancellation()) {
//            return false;
//        } else {
//            Product cheapestProduct = productList.stream()
//                    .sorted(Comparator.comparingInt(Product::getPrice))
//                    .findFirst().get();
//            orderMap.get(orderNumber).addProduct(cheapestProduct);
//            productMap.get(itemName).setInUse(true);
//            return true;
//        }
//    }
//
//    /**
//     * tagastab tellimuse kogusumma (toodete hindade summa). Kui sellise tellimust pole, tagastab -1.
//     * Tühja tellimuse kogusumma on 0.
//     *
//     * @param orderNumber order number
//     * @return number;
//     */
//    public int getOrderSum(int orderNumber) {
//        if (!orderMap.containsKey(orderNumber)) {
//            return -1;
//        } else {
//            return orderMap.get(orderNumber).getProductsSum();
//        }
//    }
//
//    /**
//     * tellimus tühistatakse. Kui sellist tellimust ei ole või see on juba tühistatud, tagastab false.
//     * Muul juhul tehakse tellimus tühjaks (selle toodete hulk saab tühjaks ja kogusumma muutub 0-ks)
//     * ja tagastatakse true. Kõik tooted, mis olid tühistatud tellimuses, on poes jälle kättesaadavad.
//     * Tühistatud tellimusse ei saa enam tooteid lisada.
//     *
//     * @param orderNumber order number
//     * @return boolean
//     */
//    public boolean cancelOrder(int orderNumber) {
//        if (!orderMap.containsKey(orderNumber) || orderMap.get(orderNumber).isOrderCancellation()) {
//            return false;
//        } else {
//            orderMap.get(orderNumber).cancelOrder(this);
//            return true;
//        }
//
//    }
//
//    /**
//     * tagastab kõik poes saada olevad tooted Listina
//     *
//     * @return
//     */
//    public List<Product> getAvailableProducts() {
//        return productList;
//    }
//
//
//}
//public class Library {
//    private Map<String, Integer> booksAmount = new HashMap<>();
//    private List<Book> uniqueBooksInLibrary = new ArrayList<>();
//    private List<Book> allBooks = new ArrayList<>();
//
//    /**
//     * Adds a book to the library.
//     * <p>
//     * If the same book (same instance) is already in the library, return false.
//     * Otherwise the book is added to the library and return true.
//     */
//    public boolean addBook(Book book) {
//        if (!uniqueBooksInLibrary.contains(book)) {
//            uniqueBooksInLibrary.add(book);
//            if (!booksAmount.containsKey(book.getIsbn())) {
//                booksAmount.put(book.getIsbn(), 1);
//            } else {
//                booksAmount.put(book.getIsbn(), booksAmount.get(book.getIsbn()) + 1);
//            }
//            allBooks.add(book);
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Tries to lend a book.
//     * <p>
//     * If there is an available book with the exact name, then this book is returned.
//     * Otherwise, if there is an available book where the title contains the name,
//     * then this book is returned.
//     * Otherwise, nothing is returned.
//     * <p>
//     * If there is a book, then this book will not be available any more (it is lent out).
//     */
//    public Optional<Book> lendBook(String name) {
//        Optional<Book> result;
//        result = uniqueBooksInLibrary.stream()
//                .filter(book -> book.getTitle().equals(name))
//                .filter(book -> !book.isLent())
//                .findFirst();
//        if (result.isEmpty()) {
//            result = uniqueBooksInLibrary.stream()
//                    .filter(book -> book.getTitle().contains(name))
//                    .filter(book -> !book.isLent())
//                    .findFirst();
//        }
//        if (result.isPresent()) {
//            Book book = result.get();
//            book.setLent(true);
//            book.setLend(book.getLend() + 1);
//            booksAmount.put(book.getIsbn(), booksAmount.get(book.getIsbn()) - 1);
//        }
//        System.out.println(result);
//        return result;
//    }
//
//    /**
//     * Returns a book.
//     * <p>
//     * If the given book is lent from the library, returns it and returns true.
//     * Otherwise returns false.
//     * <p>
//     * After returning, the book can be lent again.
//     */
//    public boolean returnBook(Book book) {
//        if (allBooks.contains(book) && book.isLent()) {
//            book.setLent(false);
//            booksAmount.put(book.getIsbn(), booksAmount.get(book.getIsbn()) + 1);
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Returns a map of ISBN and corresponding count of available books.
//     */
//    public Map<String, Integer> getBookAmounts() {
//        return booksAmount;
//    }
//
//    /**
//     * Returns how many times the book has been lent.
//     * <p>
//     * If the book is not in the library, returns -1.
//     */
//    public int getBookLendCount(Book book) {
//        if (!allBooks.contains(book)) {
//            return -1;
//        }
//
//        return book.getLend();
//    }
//
//    public static void main(String[] args) {
//        Library library = new Library();
//        Book book1 = new Book("Truth and dare", "978-3-16-148410-0");
//        Book book2 = new Book("Beauty and the Feast", "UGLY-123123");
//        Book book3 = new Book("Phantom of the Java", "VOID-MAIN-123");
//        Book book4 = new Book("What does the fox say?", "VOID-MAIN-123");
//        Book book5 = new Book("a", "WAT-LOL");
//        library.addBook(book1);
//        library.addBook(book2);
//        library.addBook(book3);
//        library.addBook(book4);
//
//        System.out.println("\n0st block");
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Step "+i+" before lend. Book map size= "+library.getBookAmounts().size());
//            System.out.println("Available book "+library.getBookAmounts().entrySet().stream()
//                    .mapToInt((b->b.getValue())).sum());
//            Book lendBook1 = library.lendBook("dare").get();
//            System.out.println("Step "+i+" after lend and before return. Book map size ="+library.getBookAmounts().size());
//            System.out.println("Available book "+library.getBookAmounts().entrySet().stream()
//                    .mapToInt((b->b.getValue())).sum());
//            library.returnBook(lendBook1);
//            System.out.println("Step "+i+" after return. Book map size ="+library.getBookAmounts().size());
//            System.out.println("Available book "+library.getBookAmounts().entrySet().stream()
//                    .mapToInt((b->b.getValue())).sum());
//        }





        /**
         * Find the longest distance between two equal symbols.
         * <p>
         * The same symbol can contain in this distance.
         * <p>
         * If there are no equals symbols, return -1.
         * <p>
         * longestDistanceBetweenEqualSymbols("abcda") => 3
         * longestDistanceBetweenEqualSymbols("aaaa") => 2
         * longestDistanceBetweenEqualSymbols("aia") => 1
         * longestDistanceBetweenEqualSymbols("aiu") => -1
         * longestDistanceBetweenEqualSymbols("") => -1
         * longestDistanceBetweenEqualSymbols("abcdabbg") => 4, 1 6
         *
         * @param s input string
         * @return longest distance
         */
    public static int longestDistanceBetweenEqualSymbols(String s) {
        int dict = -1;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char char1 = s.charAt(i);
            list.add(String.valueOf(char1));
        }
        for (int i = 0; i < list.size(); i++) {
            int first = list.indexOf(list.get(i));
            int last = list.lastIndexOf(list.get(i));
            if (first != last) {
                dict = last - first - 1;
            }

        }
        return dict;
    }

    /**
     * 03
     * <p>
     * Write a recursive method (no loops, no global variables, calls itself)
     * which separates different letters by single space.
     * <p>
     * recSeparate("abc") => "a b c"
     * recSeparate("aabbc") => aa bb c"
     * recSeparate("aaaabbbccd") => "aaaa bbb cc d"
     * recSeparate("") => ""
     * recSeparate("aaa") => "aaa"
     *
     * @param text
     * @return
     */
    public static String recSeparate(String text) {
        if (text.isEmpty() || text.length() == 1) {
            return text;
        } else {
            char firstChar = text.charAt(0);
            String remainingText = text.substring(1);
            String separatedText = recSeparate(remainingText);
            if (firstChar != separatedText.charAt(0)) {
                return firstChar + " " + separatedText;
            } else {
                return firstChar + separatedText;
            }
        }
    }

    /**
     * Write a  method that finds correct difference between two timestamps. Timestamps are given in format HH:MM where
     * HH is two-digit hour marker and MM is two-digit
     * minutes marker. Result must be also in format HH:MM. Difference means basically "time2" minus "time1".
     * timeDiff("10:00", "10:00") => "00:00"
     * timeDiff("10:00", "11:01") => "01:01"
     * timeDiff("10:00", "09:01") => "23:01"
     * timeDiff("10:00", "08:59") => "22:59"
     * timeDiff("10:01", "10:00") => "23:59"
     *
     * @param time1 time as HH:MM
     * @param time2 time as HH:MM
     * @return time difference as HH:MM
     */
    public static String timeDiff(String time1, String time2) {
        int newTime1 = Integer.parseInt(time1.split(":")[0]) * 60 + Integer.parseInt(time1.split(":")[1]);
        int newTime2 = Integer.parseInt(time2.split(":")[0]) * 60 + Integer.parseInt(time2.split(":")[1]);
        if (newTime2 > newTime1) {
            int hoursInt = (newTime2 - newTime1) / 60;
            String hoursStr = hoursInt > 9 ? String.valueOf(hoursInt) : String.format("0%s", hoursInt);
            int minInt = (newTime2 - newTime1) % 60;
            String minStr = minInt > 9 ? String.valueOf(minInt) : String.format("0%s", minInt);
            return String.format("%s:%s", hoursStr, minStr);
        }
        if (newTime2 < newTime1) {
            int timeDiff = newTime1 - newTime2;
            int minInt = 60 - (timeDiff % 60);
            int hoursInt = minInt != 0 ? 23 - (timeDiff / 60) : 24 - (timeDiff / 60);
            if (minInt == 60) {
                minInt = 0;
                hoursInt++;
            }
            String hoursStr = hoursInt > 9 ? String.valueOf(hoursInt) : String.format("0%s", hoursInt);
            String minStr = minInt > 9 ? String.valueOf(minInt) : String.format("0%s", minInt);
            return String.format("%s:%s", hoursStr, minStr);
        }
        return "00:00";
    }

    /**
     * //     * Given a string, encode the string using Run-length encoding.
     * //     * RLE is a form of data compression, where runs (consecutive data elements)
     * //     * are replaced by just one data value and count.
     * //     * <p>
     * //     * encode("TalTech") => "Taltech"
     * //     * encode("TTU") => "2TU"
     * //     * encode("WWWABBBA") => "3WA3BA"
     * //     * encode("  ") => "2 "
     * //     *
     * //     * @param data string to encode
     * //     * @return e1ncod2ed string
     * //
     */
    public static String encode(String data) {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        String str;
//        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < data.length(); i++) {
            if (i != 0) {
                str = String.valueOf(data.charAt(i - 1));
                if (String.valueOf(data.charAt(i)).equals(str)) {
                    counter++;
                } else {
                    if (counter != 1) {
                        output.append(counter).append(str);
                        counter = 1;
                    } else {
                        output.append(str);
                    }
                }
            }
            if (i == data.length() - 1) {
                str = String.valueOf(data.charAt(i - 1));
                if (String.valueOf(data.charAt(i)).equals(str)) {
                    output.append(counter).append(str);
                } else {
                    output.append(data.charAt(i));
                }
            }
        }
        return output.toString();
    }

    /**
     * Write a  method where you have to find out, how many times "search" String is found in "where" String.
     * If boolean "ordered" is TRUE, then we search from left to right, otherwise if FALSE, then we search from right
     * to left.
     * findWordBothSides("kferfoesvsvfbr", "koer", true) -> 1
     * findWordBothSide("wdhbsdbc", "kass", true) -> 0
     * findWordBothSide("llfvafamdfmmmhjfagdaurtss", "lamas", true) -> 2
     * findWordBothSide("ednjubhsdwnonvnvh", "hobune", false) -> 1
     * findWordBothSide("aaa", "a", true) -> 3
     * findWordBothSide("aaa", "aa", true) -> 1
     * findWordBothSide("hahaha", "ha", false) -> 2
     * findWordBothSide("babananaan", "banana", true) -> 1
     */
    public static int findWordBothSide(String where, String search, boolean ordered) {
        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean checker = true;
        int counter = 0;
        if (ordered) {
            for (int i = 0; i < where.length(); i++) {
                if (search.contains(String.valueOf(where.charAt(i)))) {
                    list.add(String.valueOf(where.charAt(i)));
                }
            }
        } else {
            for (int i = where.length() - 1; i > -1; i--) {
                if (search.contains(String.valueOf(where.charAt(i)))) {
                    list.add(String.valueOf(where.charAt(i)));
                }
            }
//            System.out.println(newWhere);
        }

        while (checker) {
            for (int i = 0; i < search.length(); i++) {
                if (list.contains(String.valueOf(search.charAt(i)))) {
                    int index = list.indexOf(String.valueOf(search.charAt(i)));
                    list.remove(index);
                    stringBuilder.append(search.charAt(i));
                    if (String.valueOf(stringBuilder).equals(search)) {
                        stringBuilder.setLength(0);
                        counter++;
                    }
                } else {
                    checker = false;
                }
            }
        }

        return counter;
    }


    public static void main(String[] args) {
        //1
//        System.out.println(tenRun(List.of(2, 10, 3, 4, 20, 5)));// => [2, 10, 10, 10, 20, 20]
//        System.out.println(tenRun(List.of(10, 1, 20, 2)));// => [10, 10, 20, 20]
//        System.out.println(tenRun(List.of(10, 1, 9, 20)));// => [10, 10, 10, 20]
//        System.out.println(tenRun(List.of(10, 0, 30, 40)));// => [10, 20, 30, 40]
        //2
//        System.out.println(mixedPairs("abcABD"));// => "AaBb" (a and b are represented by both lowe and upper cased letter)
//        System.out.println(mixedPairs("aaaAAAaaaa"));// => "Aa"
//        System.out.println(mixedPairs("tere"));// => ""
//        System.out.println(mixedPairs("bBaacA"));// => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
        //3
//        countSingleTwos(List.of(2, 2, 1, 3));// => 0
//        System.out.println(countSingleTwos(List.of(2, 2, 1, 3)));// => 0
//        System.out.println(countSingleTwos(List.of(7, 6, 1, 3)));// =>0
//        System.out.println(countSingleTwos(List.of(2, 2, 1, 2)));// =>1
//        System.out.println(countSingleTwos(List.of(2, 2, 2, 1, 3, 2, 1, 2)));// =>2
//        System.out.println(countSingleTwos(List.of(2, 0, 2, 1, 2, 3, 2, 1)));// =>4
//        System.out.println(countSingleTwos(List.of(2, 2, 2, 1, 2, 3, 2, 1, 2)));// =>3
        //4
//        System.out.println(decodeMessage("0")); //"a"
//        System.out.println(decodeMessage("0b2d4f6")); // "abcdefg"
//        System.out.println(decodeMessage("h8")); // "hi"
//        System.out.println(decodeMessage("11o11")); //"lol"
//        System.out.println(decodeMessage("h8 th4r30 p17ogramme43")); // "hi there programmer"
//        System.out.println(decodeMessage(":14 19h8s 8s 84e45t34n58 54oo37e523423")); // => ":o this is getting cooler"
//        System.out.println(decodeMessage("This one doesn't need to be changed!")); // => "This one doesn't need to be changed!"
        //5
//        System.out.println(zeroFront(Arrays.asList(0, 1, 0))); // 0, 0, 1
//        System.out.println(zeroFront(Arrays.asList(0, 1, 0, -1, 1, -1)));
//        System.out.println(zeroFront(Arrays.asList(-1, -1, 0, -1, 1, -1)));
//        System.out.println(zeroFront(Arrays.asList(-1, -1, 1, -1, 1, -1)));
        //6
//        System.out.println(frequencyBasedSort("1,2,1,3"));  // 1, 1, 3, 2
//        System.out.println(frequencyBasedSort("3,1"));// => {3, 1}
//        System.out.println(frequencyBasedSort("3,3,2,4,5,1,5")); //=> {5, 5, 3, 3, 4, 2, 1}
//        System.out.println(frequencyBasedSort("1,2,3,4,5,1,2,2,3,3,1")); //=> {3, 3, 3, 2, 2, 2, 1, 1, 1, 5, 4}
        //7
//        System.out.println(primeFactorsSum(1));  // => 0
//        System.out.println(primeFactorsSum(1));  // => 0
//        System.out.println(primeFactorsSum(40));  // => 11 (2 + 2 + 2 + 5)
//        System.out.println(primeFactorsSum(8881));  // => 190
//        System.out.println(primeFactorsSum(999961));  // => 999961
        //8
//        System.out.println(longestDistanceBetweenEqualSymbols("abcda"));  // => 3
//        System.out.println(longestDistanceBetweenEqualSymbols("aaaa"));  // => 2
//        System.out.println(longestDistanceBetweenEqualSymbols("aia"));  // => 1
//        System.out.println(longestDistanceBetweenEqualSymbols("aiu"));  // => -1
//        System.out.println(longestDistanceBetweenEqualSymbols(""));  // => -1
//        System.out.println(longestDistanceBetweenEqualSymbols("abcdabbg"));  // => 4
        //9
//        System.out.println(recSeparate("abc"));  // => "a b c"
//        System.out.println(recSeparate("aabbc"));  // => aa bb c"
//        System.out.println(recSeparate("aaaabbbccd"));  // => "aaaa bbb cc d"
//        System.out.println(recSeparate(""));  // => ""
//        System.out.println(recSeparate("aaa"));  // => "aaa"
        //10
//        System.out.println(timeDiff("10:00", "10:00"));  // => "00:00"
//        System.out.println(timeDiff("10:00", "11:01"));  // => "01:01"
//        System.out.println(timeDiff("10:00", "09:01"));  // => "23:01"
//        System.out.println(timeDiff("10:00", "08:59"));  // => "22:59"
//        System.out.println(timeDiff("10:01", "10:00"));  // => "23:59"
//        System.out.println(timeDiff("13:31", "10:00"));  // => 20:29
//        System.out.println(timeDiff("23:54", "15:23"));  // => 15:29
//        System.out.println(timeDiff("15:23", "23:54"));  // => 08:31
//        System.out.println(timeDiff("08:0", "10:00")); // "02;00"
//        System.out.println(timeDiff("08:00", "01:00")); // "17;00"
//        System.out.println(timeDiff("08:34", "01:44")); // "17;10"
//        System.out.println(timeDiff("15:13", "21:43")); // "06:30"
        //11
//        System.out.println(encode("TalTech")); // "Taltech"
//        System.out.println(encode("rrrREaEdaa")); // "3rEaEd2a"
//        System.out.println(encode("TTU")); // "2TU"
//        System.out.println(encode("WWWABBBA")); // "3WA3BA"
//        System.out.println(encode("WWWABBBAA")); // "3WA3B2A"
//        System.out.println(encode("  ")); // "2 "
        //12
//        System.out.println(findWordBothSide("kferfoesvsvfbr", "koer", true)); // -> 1
//        System.out.println(findWordBothSide("wdhbsdbc", "kass", true)); // -> 0
//        System.out.println(findWordBothSide("llfvafamdfmmmhjfagdaurtss", "lamas", true)); // -> 2
//        System.out.println(findWordBothSide("ednjubhsdwnonvnvh", "hobune", false)); // -> 1
//        System.out.println(findWordBothSide("ednjubhsdwnonvnvh", "hobune", false)); // -> 1
//        System.out.println(findWordBothSide("aaa", "a", true)); // -> 3
//        System.out.println(findWordBothSide("aaa", "aa", true)); //-> 1
//        System.out.println(findWordBothSide("hahaha", "ha", false)); // -> 2
        findWordBothSide("hahaha", "ha", false); // -> 2
//        System.out.println(findWordBothSide("babananaan", "banana", true)); // -> 1

    }


}
