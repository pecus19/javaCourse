package ee.taltech.iti0202.files.output;

import ee.taltech.iti0202.files.input.FileReaderException;

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
        if (filename == null || lines == null) {
            return false;
        }
        try {
            FileWriter writer = new FileWriter(filename);
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return true;
        }

    }
}
