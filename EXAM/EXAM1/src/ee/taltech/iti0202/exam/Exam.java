package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.List;

public class Exam {

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
        for (int i = 8; i < 100000; i++) {
            if (i % 2 != 0 | i % 3 != 0 | i % 5 != 0 | i % 7 != 0) {
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
     * longestDistanceBetweenEqualSymbols("abcdabbg") => 4
     *
     * @param s input string
     * @return longest distance
     */
    public static int longestDistanceBetweenEqualSymbols(String s) {
        int dict = -1;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char char1 = s.charAt(i);
            String s1 =String.valueOf(char1);
            list.add(s1.toLowerCase());
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

    public static void main(String[] args) {
        //1
//        System.out.println(primeFactorsSum(1));  // => 0
//        System.out.println(primeFactorsSum(1));  // => 0
//        System.out.println(primeFactorsSum(40));  // => 11 (2 + 2 + 2 + 5)
//        System.out.println(primeFactorsSum(8881));  // => 190
//        System.out.println(primeFactorsSum(999961));  // => 999961
        //2
        System.out.println(longestDistanceBetweenEqualSymbols("abcda")); // => 3
        System.out.println(longestDistanceBetweenEqualSymbols("aaaa")); // => 2
        System.out.println(longestDistanceBetweenEqualSymbols("aia")); // => 1
        System.out.println(longestDistanceBetweenEqualSymbols("aiu")); // => -1
        System.out.println(longestDistanceBetweenEqualSymbols("")); // => -1
        System.out.println(longestDistanceBetweenEqualSymbols("abcdabbg")); // => 4
        System.out.println(longestDistanceBetweenEqualSymbols("aaaaaaaaaaaaa")); // => 11
    }
}
