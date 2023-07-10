package ee.taltech.iti0202.springboot.input;

import java.util.List;

public interface InputFilesReader {
    /**
     * @param filename filename
     * @return String
     */
    List<String> readTextFromFile(String filename);
}
