
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static final String delimiter = ",";
    public static Map<Integer, String> map = new HashMap<>();
    public static int counter = 0;

    public static int read(String csvFile, int x, int y) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            String[] tempArr;
            int sum = 0;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                int check;
                for (int i = 1; i < tempArr.length; i += 2) {
                    if (!map.containsKey(Integer.parseInt(tempArr[i - 1]))) {
                        map.put(Integer.parseInt(tempArr[i - 1]), tempArr[i]);
                    } else {
                        String per = map.get(Integer.parseInt(tempArr[i - 1]));
                        map.put(Integer.parseInt(tempArr[i - 1]), per += ",");
                        map.put(Integer.parseInt(tempArr[i - 1]), per + tempArr[i]);
                    }
                }
            }
            for (String value : map.values()) {
                if (value.contains(x + "") && value.contains(y + "")) {

                    counter++;
                }
            }
            System.out.println(counter);
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        //csv file to read
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String csvFile = "C:\\Users\\danil\\git\\retail_data.txt";
        System.out.println("Enter the IDs of two books, separated by a space:");
        Integer userInput = Integer.valueOf(userInputReader.readLine());
        Integer userInput2 = Integer.valueOf(userInputReader.readLine());
        Test.read(csvFile, userInput, userInput);
    }

}
