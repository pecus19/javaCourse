package ee.taltech.iti0202.output;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OutputFilesWriterTest {
    @org.junit.jupiter.api.Test
    public void outputFilesWriterTrueTest() {

        List<String> list = new ArrayList<>(List.of("Something, Something2, Something3"));
        OutputFilesWriter outputFilesWriter = new OutputFilesWriter();
        Assertions.assertTrue(outputFilesWriter.writeLinesToFile(list, "output.txt"));
    }

    @Test
    public void outputFilesWriterFalseTest() {
        List<String> list = new ArrayList<>();
        OutputFilesWriter outputFilesWriter = new OutputFilesWriter();
        Assertions.assertFalse(outputFilesWriter.writeLinesToFile(list, ""));
    }
}
