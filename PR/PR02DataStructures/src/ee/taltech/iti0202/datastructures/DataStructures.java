package ee.taltech.iti0202.datastructures;

import java.util.*;

public class DataStructures {

    /**
     * Given String is a sentence with some words.
     * There are only single whitespace between every word and no punctuation marks.
     * Also there are no capital letters in input string.
     * <p>
     * Return the longest word from the input sentence.
     * <p>
     * If there are more than one word with the same length then return the word which comes alphabetically first.
     * <p>
     * Hints:
     * You can split words into an array using "str.split()"
     * Sorting the list with the longest words can definitely help you to find the word which comes alphabetically fir
     *
     * @param sentence input String to find the longest words
     * @return the longest String from input
     */
    public static String findLongestWord(String sentence) {
        String[] split = sentence.split(" ");
        String output = "";
        for (int i = 0; i < Arrays.stream(split).sorted().toList().size(); i++) {
            if (Arrays.stream(split).sorted().toList().get(i).length() > output.length()) {
                output = Arrays.stream(split).sorted().toList().get(i);
            }
        }
        return output;
    }

    /**
     * Classic count the words exercise.
     * <p>
     * From input count all the words and collect results to map.
     *
     * @param sentence array of strings, can't be null.
     * @return map containing all word to count mappings.
     */
    public static Map<String, Integer> wordCount(String[] sentence) {
        Map<String, Integer> map = new HashMap<>();
        for (String name : sentence) {
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            } else {
                map.put(name, 1);
            }
        }
        return map;
    }

    /**
     * Loop over the given list of strings to build a resulting list of string like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that the string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        ArrayList<String> output = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<>();
        for (String name : words) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        for (Map.Entry<String, Integer> el : map.entrySet()) {
            if (el.getValue() > 1) {
                int num = el.getValue() / 2;
                for (int i = 0; i < num; i++) {
                    output.add(el.getKey());
                }
            }
        }
        return output;
    }

    Map<String, Integer> students = new HashMap<>();

    /**
     * Method to save student and student's grade(you should use a Map here).
     * Only add student if his/hers grade is in the range of 0-5.
     *
     * @param studentInfo String with a pattern (name:grade)
     */
    public void addStudent(String studentInfo) {
        String[] tokens = studentInfo.split(":");
        int grade;
        grade = Integer.parseInt(tokens[1]);
        switch (grade) {
            case 0, 1, 2, 3, 5, 4 -> students.put(tokens[0], grade);
            default -> students.put(tokens[0], -1);
        }
    }


    /**
     * Method to get student's grade.
     * Return the student's grade by his/hers name.
     * You can assume that the user is already added(previous function with student's info already called).
     *
     * @param name String students name
     * @return int student's grade.
     */
    public int getStudentGrade(String name) {
        return students.getOrDefault(name, 0);

    }

    /**
     * Main.
     *
     * @param args Commend line arguments.
     */
    public static void main(String[] args) {
//        System.out.println(findLongestWord("nimi on salastatud"));  // "salastatud"
//        System.out.println(findLongestWord("aaa bbbbbb"));  // "bbbbb"
//        System.out.println(findLongestWord("hello ahllo")); // "ahllo"
//        System.out.println(findLongestWord("aaa b")); // "aaa"
//        System.out.println(findLongestWord("bbb ccc aaa")); // "aaa "
//        System.out.println(findLongestWord("bbb aaa ccc")); // "aaa "

        System.out.println(wordCount(new String[]{})); // empty
        System.out.println(wordCount(new String[]{"eggs", "SPAM", "eggs", "bacon", "SPAM", "bacon", "SPAM"})); //
//        {bacon=2, eggs=2, SPAM=3}
//
//        System.out.println(onlyEvenWords(Arrays.asList("foo", "bar", "baz", "baz", "bar", "foo"))); //
//         [baz, bar, foo] any order
//        System.out.println(onlyEvenWords(Arrays.asList("a", "b", "b", "a"))); // [b, a] any order
//        System.out.println(onlyEvenWords(Arrays.asList("eggs", "bacon", "SPAM", "ham", "SPAM", "SPAM"))); // [SPAM]
//        System.out.println(onlyEvenWords(Arrays.asList("eggs", "bacon", "SPAM"))); // []

        DataStructures dataStructures = new DataStructures();
//
        dataStructures.addStudent("Ago:5");
        dataStructures.addStudent("Martin:0");
        dataStructures.addStudent("Margo:3");
        dataStructures.addStudent("Cheater:6");
        dataStructures.addStudent("Vasaja:-4");

        System.out.println(dataStructures.getStudentGrade("Ago")); // 5
        System.out.println(dataStructures.getStudentGrade("Martin")); // 0
        System.out.println(dataStructures.getStudentGrade("Margo")); // 3
        System.out.println(dataStructures.getStudentGrade("Cheater")); // -1
        System.out.println(dataStructures.getStudentGrade("Vasaja")); // -1
    }
}
