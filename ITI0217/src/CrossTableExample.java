import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class CrossTableExample {

    public static void main(String[] args) {
        // Initialize the cross table with zeros
        int[][] crossTable = new int[50][50];

        // Read in the book and person data from file
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\danil\\git\\retail_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int personId = Integer.parseInt(values[0]);
                int bookId = Integer.parseInt(values[1]);

                // Check this book against all previously seen books
                for (int i = 0; i < bookId; i++) {
                    if (crossTable[i][bookId] > 0) {
                        // Book i and bookId were bought together by someone else
                        crossTable[i][bookId]++;
                        crossTable[bookId][i]++; // crossTable is symmetric
                    }
                }

                // Increment the count for this book
                crossTable[bookId][bookId]++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print the cross table
        for (int i = 0; i < crossTable.length; i++) {
            for (int j = i+1; j < crossTable[i].length; j++) {
                if (crossTable[i][j] > 0) {
                    System.out.println("Books " + i + " and " + j + " were bought together by " + crossTable[i][j] + " people.");
                }
            }
        }
    }
}