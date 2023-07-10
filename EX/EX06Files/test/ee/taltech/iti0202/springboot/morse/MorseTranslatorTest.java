package ee.taltech.iti0202.springboot.morse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MorseTranslatorTest {

    @Test
    public void addMorseCodesTest() {
        MorseTranslator morseTranslator = new MorseTranslator();
        Map<String, String> map = new HashMap<>(Map.of("a", ".-", "b", "-..."));
        assertEquals(map, morseTranslator.addMorseCodes(List.of("A .-", "B -...")));
    }

    @Test
    public void translateLinesToMorseTest() {
        MorseTranslator morseTranslator = new MorseTranslator();
        List<String> list = new ArrayList<>(List.of(".- -..."));
        List<String> list2 = new ArrayList<>(List.of("AB"));
        morseTranslator.addMorseCodes(List.of("A .-", "B -..."));
        assertEquals(list, morseTranslator.translateLinesToMorse(list2));
    }

    @Test
    public void translateLinesFromMorseTest() {
        MorseTranslator morseTranslator = new MorseTranslator();
        List<String> list = new ArrayList<>(List.of("ab"));
        List<String> list2 = new ArrayList<>(List.of(".- -..."));
        morseTranslator.addMorseCodes(List.of("A .-", "B -..."));
        assertEquals(list, morseTranslator.translateLinesFromMorse(list2));
    }
}
