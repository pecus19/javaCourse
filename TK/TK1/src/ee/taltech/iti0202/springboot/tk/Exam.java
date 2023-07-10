package ee.taltech.iti0202.springboot.tk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Exam {
    public static final int BIG = -9999999;
    public static final int SMALL = 9999999;
    public static final int TO = 21;

    /**
     * Filter out the words that can be created with exactly 2 parts.
     * Each part can only be used only once.
     * Making words is case-sensitive.
     * Order of items in the output does not matter.
     * Each item should be in the answer only once.
     * Example:
     * CompileWords(
     * List.of("He", "llo", "y", "!", "yo", "i", "H", "yo!"),
     * List.of("Hello", "yo!", "Heyo!", "Hi!", "World", "yooo", "Hi", "llo")
     * ) => "Hello", "Heyo!", "yo!", "Hi"
     */
    public static List<String> compileWords(List<String> parts, List<String> words) {
        List<String> newParts = new ArrayList<>(parts);
        List<String> output = new ArrayList<>();
        newParts.addAll(parts);
        for (int j = 0; j < newParts.size(); j++) {
            for (int i = j + 1; i < newParts.size(); i++) {
                String str = newParts.get(j) + newParts.get(i);
                if (words.contains(str) && !output.contains(str)) {
                    output.add(str);
                }
            }
        }
        return output;
    }

    /**
     * Return the "centered" average of an array of ints, which we'll say is the mean average of the values,
     * except ignoring the largest and smallest values in the array. If there are multiple copies of the
     * smallest value, ignore just one copy, and likewise for the largest value. Use int division to produce
     * the final average. You may assume that the array is length 3 or more.
     * <p>
     * centeredAverage([1, 2, 3, 4, 100]) → 3
     * centeredAverage([1, 1, 5, 5, 10, 8, 7]) → 5
     * centeredAverage([-10, -4, -2, -4, -2, 0]) → -3
     */
    public static int centeredAverage(List<Integer> nums) {
        int min = SMALL;
        int max = BIG;
        int output = 0;
        for (int i = 0; i < nums.size(); i++) {
            min = Math.min(min, nums.get(i));
            max = Math.max(max, nums.get(i));
            output += nums.get(i);
        }
        output -= min + max;
        return output / (nums.size() - 2);
    }


    /**
     * Given 2 int values greater than 0, return whichever value is nearest to 21 without going over.
     * Return 0 if they both go over.
     * <p>
     * blackjack(19, 21) → 21
     * blackjack(21, 19) → 21
     * blackjack(19, 22) → 19
     */
    public static int blackjack(int a, int b) {
        if (a > TO && b > TO) {
            return 0;
        } else {
            int newA = TO - a;
            int newB = TO - b;
            if (newA < 0 && newB >= 0) {
                return b;
            }
            if (newB < 0 && newA >= 0) {
                return a;
            } else {
                return Math.max(a, b);
            }
        }
    }


    /**
     * Given a string and an int n, return a string made of n repetitions of the last n characters
     * of the string. You may assume that n is between 0 and the length of the string, inclusive.
     * <p>
     * repeatEnd("Hello", 3) → "llollollo"
     * repeatEnd("Hello", 2) → "lolo"
     * repeatEnd("Hello", 1) → "o"
     */
    public static String repeatEnd(String str, int n) {
        return str.substring(str.length() - n).repeat(n);

    }

    /**
     * Modify and return the given map as follows: if the keys "a" and "b" are both in the map
     * and have equal values, remove them both.
     * <p>
     * mapAB({"a": "aaa", "b": "aaa", "c": "cake"}) → {"c": "cake"}
     * mapAB({"a": "aaa", "b": "bbb"}) → {"a": "aaa", "b": "bbb"}
     * mapAB({"a": "aaa", "b": "bbb", "c": "aaa"}) → {"a": "aaa", "b": "bbb", "c": "aaa"}
     */
    public static Map<String, String> mapAB(Map<String, String> map) {
        if (map.containsKey("a") && map.containsKey("b")) {
            if (map.get("a").equals(map.get("b"))) {
                map.remove("a");
                map.remove("b");
            }
        }
        return map;
    }

    public static void main(String[] args) {
        //1
        System.out.println(compileWords(List.of("He", "llo", "y", "!", "yo", "i", "H", "yo!"),
                List.of("Hello", "yo!", "Heyo!", "Hi!", "World", "yooo", "Hi", "llo")));
        // => "Hello", "Heyo!", "yo!", "Hi"
//        System.out.println(centeredAverage(List.of(1, 2, 3, 4, 100))); // → 3
//        System.out.println(centeredAverage(List.of(1, 1, 5, 5, 10, 8, 7))); // → 5
//        System.out.println(centeredAverage(List.of(-10, -4, -2, -4, -2, 0))); // → -3
        //2
//        System.out.println(blackjack(19, 21)); // 21
//        System.out.println(blackjack(21, 19)); // 21
//        System.out.println(blackjack(19, 22)); // 19
//        System.out.println(blackjack(22, 22)); // 0
//        System.out.println(blackjack(1, 22)); // 22
//        System.out.println(blackjack(20, 22)); // 22
        //3
//        System.out.println(repeatEnd("Hello", 3)); // "llollollo"
//        System.out.println(repeatEnd("Hello", 2)); // "lolo"
//        System.out.println(repeatEnd("Hello", 1)); // "o"
        //4

    }
}
