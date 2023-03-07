package ee.taltech.iti0202.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputFilesBufferReaderTest {

    @Test
    void readTextFromFileBufferReaderTest() {
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        try {
            List<String> result = bufferReader.readTextFromFile("fdsfsd.txt");
        } catch (Exception e) {
            assertEquals("No such file", e.getMessage());
        }
    }

    @Test
    void readTextFromFileLinesWrongFile1Test() {
        InputFilesLines lines = new InputFilesLines();
        try {
            List<String> result = lines.readTextFromFile("fdsfsd.txt");
        } catch (Exception e) {
            Assertions.assertEquals("No such file", e.getMessage());
        }
    }

    @Test
    @DisplayName("Throws correct exception class")
    void readTextFromFileLinesWrongFileTest() {
        InputFilesLines reader = new InputFilesLines();
        assertThrows(FileReaderException.class,
                () -> reader.readTextFromFile("WrongName"),
                "FileReaderException was expected.");
    }

    @Test
    public void readTextFromFileLinesTest() {
        InputFilesLines reader = new InputFilesLines();
        String filename = "C:\\Users\\danil\\git\\iti0202-2023\\EX\\EX06Files\\src\\ee\\taltech\\iti0202\\input.txt";
        List<String> result = new ArrayList<>();
        result.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit,");
        Assertions.assertEquals(result, reader.readTextFromFile(filename));
    }

    @Test
    public void testFile3() {
        InputFilesLines reader = new InputFilesLines();
        String filename = "C:\\Users\\danil\\git\\iti0202-2023\\EX\\EX06Files\\src\\ee\\taltech\\iti0202\\input.txt";
        List<String> result = new ArrayList<>();
        result.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit,");
        Assertions.assertEquals(result, reader.readTextFromFile(filename));
    }

    @Test
    public void testFile2() {
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        try {
            List<String> result = bufferReader.readTextFromFile("fdsfsd.txt");
        } catch (Exception e) {
            Assertions.assertEquals("No such file", e.getMessage());
        }
    }

    @Test
    public void readFilesScannerTest() {
        InputFilesScanner filesScanner = new InputFilesScanner();
        String filename = "C:\\Users\\danil\\git\\iti0202-2023\\EX\\EX06Files\\src\\ee\\taltech\\iti0202\\input.txt";
        List<String> result = new ArrayList<>();
        result.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit,");
        Assertions.assertEquals(result, filesScanner.readTextFromFile(filename));
    }

    @Test
    public void readFilesBufferReaderTest() {
        InputFilesBufferReader inputFilesBufferReader = new InputFilesBufferReader();
        String filename = "C:\\Users\\danil\\git\\iti0202-2023\\EX\\EX06Files\\src\\ee\\taltech\\iti0202\\input.txt";
        List<String> result = new ArrayList<>();
        result.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit,");
        Assertions.assertEquals(result, inputFilesBufferReader.readTextFromFile(filename));
    }

    @Test
    public void readFilesScannerWrongFileTest() {
        InputFilesScanner filesScanner = new InputFilesScanner();
        try {
            List<String> result = filesScanner.readTextFromFile("fdsfsd.txt");
        } catch (Exception e) {
            Assertions.assertEquals("No such file", e.getMessage());
        }
    }
}
