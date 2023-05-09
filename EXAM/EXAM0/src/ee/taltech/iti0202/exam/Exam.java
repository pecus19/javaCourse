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

    }


}
