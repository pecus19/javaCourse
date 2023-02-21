//package ee.taltech.iti0202.stock;
//
//import ee.taltech.iti0202.stock.exceptions.StockException;
//import ee.taltech.iti0202.stock.product.Product;
//import ee.taltech.iti0202.stock.stock.Stock;
//
//import java.util.Optional;
//
//public class StockDemo {
//
//    public static void main(String[] args) throws StockException {
//        Stock fruitStock = new Stock("fruit-stock-1", 4);
//
//
//        Product cheapApple = new Product("apple", 3);
//        Product expensiveApple = new Product("apple", 9);
//        Product orange = new Product("orange", 5);
//        Product mango = new Product("mango", 6);
//        Product pear = new Product("pear", 4);
//        System.out.println(pear.getId()); // 5
////
//        fruitStock.addProduct(expensiveApple);
//        fruitStock.addProduct(cheapApple);
//        System.out.println(fruitStock.getProducts()); // ("apple", 9);, "apple", 3);
//
//        Optional<Product> apple = fruitStock.getProduct("apple"); // Optional.of("apple", 3))
//        apple.ifPresent(System.out::println); // cheapApple
//
//        fruitStock.addProduct(orange);
//        fruitStock.addProduct(mango);
//        System.out.println(fruitStock.getProducts().size()); // 4
//        System.out.println(fruitStock.getProducts("apple")); // "apple", 3), "apple", 9);
//
//        try {
//            fruitStock.addProduct(pear);
//        } catch (StockException e) {
//            System.out.println(e.getReason()); // STOCK_IS_FULL
//        }
//
//        try {
//            fruitStock.addProduct(mango);
//        } catch (StockException e) {
//            System.out.println(e.getReason()); // STOCK_ALREADY_CONTAINS_PRODUCT
//        }
//
//        System.out.println(fruitStock.removeProduct("mango")); // Optional.of(mango)
//        System.out.println(fruitStock.removeProduct("orange")); // Optional.of(mango)
////        System.out.println(fruitStock.removeProduct("apple")); // Optional.of(mango)
////        removedMango.ifPresent(System.out::println);
//
////        System.out.println(fruitStock.removeProduct("apple")); // Optional["apple", 3);]
////        System.out.println(fruitStock.removeProduct("apple").get()); // Optional["apple", 9);]
////        System.out.println(fruitStock.removeProduct("dumpling")); // Optional.empty
//
//    }
//}