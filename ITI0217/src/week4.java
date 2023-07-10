import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class week4 {
    static Map<Integer, String> map = new LinkedHashMap<>();
    public static final String delimiter = ",";

    public static void read(String csvFile) {
        try {
            Integer output;
            String line = " ";
            String[] tempArr;
            output = 1;
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
//                for (int i = 1; i < tempArr.length; i += 2) {
//                    if (Integer.parseInt(tempArr[i - 1]) == output) {
//                        System.out.print(tempArr[i] + " ");
//                    }
//                }
                System.out.println(tempArr.toString());
            }

            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //csv file to read
        String csvFile = "C:\\Users\\danil\\git\\iti0202-2023\\ITI0217\\src\\retail_data.txt";
        week4.read(csvFile);
    }
}
