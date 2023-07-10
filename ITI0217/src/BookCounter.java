import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BookCounter {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // Read the file and build a map of book IDs to people who have bought them
        HashMap<Integer, Integer[]> bookMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\danil\\git\\retail_data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int personId = Integer.parseInt(fields[0]);
                int bookId = Integer.parseInt(fields[1]);
                Integer[] bookOwners = bookMap.get(bookId);
                if (bookOwners == null) {
                    bookOwners = new Integer[0];
                }
                Integer[] newBookOwners = new Integer[bookOwners.length + 1];
                System.arraycopy(bookOwners, 0, newBookOwners, 0, bookOwners.length);
                newBookOwners[newBookOwners.length - 1] = personId;
                bookMap.put(bookId, newBookOwners);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Prompt the user for two book IDs and calculate the number of people who have bought both books
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter the IDs of two books, separated by a space:");
            String userInput = userInputReader.readLine();
            String[] bookIds = userInput.split(" ");
            int bookId1 = Integer.parseInt(bookIds[0]);
            int bookId2 = Integer.parseInt(bookIds[1]);
            Integer[] book1Owners = bookMap.get(bookId1);
            Integer[] book2Owners = bookMap.get(bookId2);
            int count = 0;
            for (Integer book1Owner : book1Owners) {
                for (Integer book2Owner : book2Owners) {
                    if (book1Owner.equals(book2Owner)) {
                        count++;
                        break;
                    }
                }
            }
            System.out.println(count + " people have bought both books.");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        long endTime = System.currentTimeMillis();

        long executionTime = (endTime - startTime) / 1000; // convert milliseconds to seconds

        System.out.println("Execution time in seconds: " + executionTime);
    }
}
