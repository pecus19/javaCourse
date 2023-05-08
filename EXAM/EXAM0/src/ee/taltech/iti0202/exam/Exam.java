package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.List;

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
            if (nums.get(i) % 10 == 0 && nums.get(i) != 0) {
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
        return "wrong";
    }

    public static void main(String[] args) {
        //1
        System.out.println(tenRun(List.of(2, 10, 3, 4, 20, 5)));// => [2, 10, 10, 10, 20, 20]
        System.out.println(tenRun(List.of(10, 1, 20, 2)));// => [10, 10, 20, 20]
        System.out.println(tenRun(List.of(10, 1, 9, 20)));// => [10, 10, 10, 20]
    }

}
