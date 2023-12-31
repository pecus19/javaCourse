package ee.taltech.iti0202.springboot.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFilesWriter {
    /**
     * @param lines    lines
     * @param filename filename
     * @return false
     */
    public boolean writeLinesToFile(List<String> lines, String filename) {
        try (FileWriter writer = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(writer)) {
            for (String line : lines) {
                bw.write(line + System.lineSeparator());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
