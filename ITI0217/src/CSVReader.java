import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class CSVReader {
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
                for (int i = 1; i < tempArr.length ; i+=2) {
                    if (Integer.parseInt(tempArr[i - 1]) == output) {
                        System.out.print(tempArr[i] + " ");
                    } else {
                        System.out.println();
                        System.out.print(tempArr[i] + " ");
                        output = Integer.parseInt(tempArr[i - 1]);
                    }
                }
            }

            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //csv file to read
        String csvFile = "C:\\Users\\danil\\git\\danilaEXCEL.csv";
        CSVReader.read(csvFile);
    }
}