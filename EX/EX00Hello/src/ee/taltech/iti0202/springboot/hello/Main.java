package ee.taltech.iti0202.springboot.hello;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

        public static void main(String[] args) {
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 1)); // list = [0,1]
            while (list.size() < 10) {
                list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
                list.add(0, 1);
            }
            int a = 0;
            for (int i = 0; i < list.size(); i++) {
                a += list.get(i);
            }

            System.out.println(a);
        }
    
}