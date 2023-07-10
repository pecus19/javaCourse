import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Second {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        HashMap<Integer, Integer[]> bookMap = new HashMap<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> peopleList = new ArrayList<>();
        long peopleCounter = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\danil\\git\\retail_data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int personId = Integer.parseInt(fields[0]);
                int bookId = Integer.parseInt(fields[1]);

                if (!arrayList.contains(bookId)) {
                    arrayList.add(bookId);
                }
                if (!peopleList.contains(personId)) {
                    peopleList.add(personId);
                    peopleCounter++;
                }
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
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = i + 1; j < arrayList.size(); j++) {
                int bookId1 = Integer.parseInt(String.valueOf(arrayList.get(i)));
                int bookId2 = Integer.parseInt(String.valueOf(arrayList.get(j)));
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
                if (count >= 1) {
                    double probXY = ((double) count / book1Owners.length) * 100; // Probability that a person who bought book X also bought book Y
                    double probYX = ((double) count / book2Owners.length) * 100; // Probability that a person who bought book Y also bought book X
                    System.out.println(bookId1 + "|" + bookId2 + "|" + count + "|" + "OF THOSE who bought book "
                            + bookId1 + ", " + probXY + "% also bought book " + bookId2 + ". " + "OF THOSE who bought book "
                            + bookId2 + ", " + probYX + "% also bought book " + bookId1 + ".");
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime) / 1000; // convert milliseconds to seconds
        System.out.println("Execution time in seconds: " + executionTime);
    }
}
