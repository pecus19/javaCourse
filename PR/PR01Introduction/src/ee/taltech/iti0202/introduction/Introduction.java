package ee.taltech.iti0202.introduction;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Introduction {
    public static final int FIVE = 5;
    public static final int TWO = 2;
    public static final int ZERO = 0;


    /**
     * Method gets two numbers as parameters.
     * Method must answer if the given pair gives bad, normal or good outcome.
     * Outcome is "bad" if any of values is less than 5.
     * Outcome is "good" if one value equals doubled second value.
     * Outcome is "ok" if both values equal at least 5.
     * The priority is as follows: "bad", "good", "ok" (if several cases apply, take the higher / earlier).
     *
     * @param valueOne int
     * @param valueTwo int
     * @return String based on the values of valueOne and valueTwo
     */
    public String howIsOutcome(int valueOne, int valueTwo) {
        return valueOne < FIVE || valueTwo < FIVE ? "bad" : valueOne == valueTwo * TWO
                || valueTwo == valueOne * TWO ? "good" : "ok";
    }

    /**
     * Method gets a list of numbers.
     * Return a list containing only even numbers of the given list.
     * If the given list does not contain any even numbers, return an empty list.
     *
     * @param numbers given list that contains numbers.
     * @return list of even numbers.
     */
    public List<Integer> findEvenNumbersList(List<Integer> numbers) {
        ArrayList<Integer> output = new ArrayList<>();
        for (int i = ZERO; i < numbers.size(); i++) {
            if (numbers.get(i) % TWO == ZERO) {
                output.add(numbers.get(i));
            }
        }
        return output;
    }

    /**
     * Method gets an array of numbers.
     * Return an array containing only even numbers of the given array.
     * If the given array does not contain any even numbers, return an empty array.
     * <p>
     * You must not use the previous function in this function!
     *
     * @param numbers given array that contains numbers.
     * @return array of even numbers.
     */
    public int[] findEvenNumbersArray(int[] numbers) {
        int counter = ZERO;
        int len = ZERO;
        for (int i = ZERO; i < numbers.length; i++) {
            if (numbers[i] % TWO == ZERO) {
                len++;
            }
        }
        int[] output = new int[len];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % TWO == ZERO) {
                output[counter] = numbers[i];
                counter++;
            }
        }
        return output;
    }

    /**
     * Method gets two Strings as parameters.
     * If two words have the same length, just put them together. If the length is
     * different, remove so many letters from the beginning of the longer word that the two words are the same
     * length, and
     * then put them together.
     * If the first word was longer, return the answer in lower case. If the second word was longer,
     * return the answer in capital letters.
     * If both words are empty or with spaces, return FALSE.
     *
     * @param first  String
     * @param second String
     * @return String based on the values of first and second
     */
    public String findTheString(String first, String second) {
        String newFirst = first.trim();
        String newSecond = second.trim();
        if (newFirst.length() == ZERO && newSecond.length() == ZERO) {
            return "FALSE";
        } else {
            return newFirst.length() == newSecond.length() ? first + second
                    : (first.length() > second.length() ? (first.substring(first.length() - second.length())
                    + second).toLowerCase(Locale.ROOT) : (first.length() < second.length())
                    ? (first + second.substring(second.length() - first.length())).toUpperCase(Locale.ROOT) : "FALSE");
        }

    }

    /**
     * Method gets one String as a parameter.
     * In a given string, count the number of characters that appear exactly three times in a row.
     * System.out.println(introduction.countTripleChars("aaabbbabbb"));  // 3
     * System.out.println(introduction.countTripleChars("aaa"));  // 1
     * System.out.println(introduction.countTripleChars("aaaa"));  // 0
     * System.out.println(introduction.countTripleChars("aaaabbbabbbcCc"));  // 2
     * aaaaaaa
     *
     * @param word String
     * @return The number of triples
     */
    public int countTripleChars(String word) {
        String newWord = word;
        int num = 1;
        int counter = 0;
        for (int i = counter; i < newWord.length() - 1; i += 0) {
            if (newWord.charAt(i) != newWord.charAt(i + 1)) {
                newWord = newWord.replaceFirst(String.valueOf(newWord.charAt(i)), "");
            } else {
                while (num < newWord.length()) {
                    if (newWord.charAt(i) == newWord.charAt(num)) {
                        num++;
                    } else {
                        if (num == 3) {
                            counter++;
                        }
                        newWord = newWord.substring(num);
                        num = 1;
                    }
                }
                if (num == 3) {
                    counter++;
                }
                return counter;
            }
        }
//        System.out.println(counter);
        return 0;
    }

    /**
     * Run tests.
     *
     * @param args Arguments from command line.
     */
    public static void main(String[] args) {
        Introduction introduction = new Introduction();
//        System.out.println(introduction.howIsOutcome(1, 10));//=>"bad"
//        System.out.println(introduction.howIsOutcome(6, 12));//=>"good"
//        System.out.println(introduction.howIsOutcome(8, 9));//=>"ok"
//
//        System.out.println(introduction.howIsOutcome(3, 6)); // "bad"
//
//        List<Integer> nums = new ArrayList<>(Arrays.asList(4, 7, 5, 2, 1, 2, -2, 0));
//        List<Integer> nums2 = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
//        System.out.println(introduction.findEvenNumbersList(nums)); // [4, 2, 2, -2, 0]
//        System.out.println(introduction.findEvenNumbersList(nums2)); // [4, 2, 2, -2, 0]
//
//        int[] array = {9, 0, 24, -6, 3};
//        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array))); // [0, 24, -6]
//
//        String result = introduction.findTheString("Good", "afternoon");
//        System.out.println(result);  // GOODNOON
//        result = introduction.findTheString("Hello", "lo");
//        System.out.println(result);  // lolo
//        System.out.println(introduction.findTheString("", ""));  // FALSE
//        System.out.println(introduction.findTheString("", "   "));  // FALSE
//        System.out.println(introduction.findTheString("  ", "a"));  //  a  (with space in front)
//
//        System.out.println(introduction.countTripleChars("bbb"));  // 1
//        System.out.println(introduction.countTripleChars("aaabbbabbb"));  // 3
//        System.out.println(introduction.countTripleChars("aaa"));  // 1
//        System.out.println(introduction.countTripleChars("aaaa"));  // 0
//        System.out.println(introduction.countTripleChars("aaaabbbabbbcCc"));  // 2
    }
}
