package ee.taltech.iti0202.springboot.hello;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Simple class to test whether you get feedback.
 */
public class Hello {
    /**
     * Returns a greeting for the given person.
     * If name is empty string, then returns "Hello!".
     * Otherwise returns "Hello, [name]!"
     *
     * @param name Name of the person.
     * @return Greeting
     */
    public static String getGreeting(String name) {
        return name.length() > 0 ? String.format("Hello, %s!", name) : "Hello!";
    }

    /**
     * The main method which is executed when the program is executed.
     *
     * @param args Arguments from command line
     */
    public static int getSum() {
        int sum = 0;
        for (int a = 50; a >= 0; a--) {
            if (a % 10 == 0) {
                sum += 1;
            }
            if (a % 5 == 0) {
                sum -= 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        System.out.println(hello.getSum());
    }
}
