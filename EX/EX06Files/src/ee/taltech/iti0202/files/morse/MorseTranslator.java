package ee.taltech.iti0202.files.morse;

import java.util.*;

public class MorseTranslator {
    private Map<String, String> morseMap = new HashMap<>();
    private Map<String, String> fromMap = new HashMap<>();

    /**
     * @param lines lines
     * @return null
     */
    public Map<String, String> addMorseCodes(List<String> lines) {
        if (!lines.isEmpty()) {
            for (int i = 0; i < lines.size(); i++) {
                String[] split = lines.get(i).split(" ");
                if (!morseMap.containsKey(split[0].toLowerCase(Locale.ROOT))) {
                    morseMap.put(split[0].toLowerCase(Locale.ROOT), split[1].toLowerCase(Locale.ROOT));
                }
                if (!fromMap.containsKey(split[1].toLowerCase(Locale.ROOT))) {
                    morseMap.put(split[1].toLowerCase(Locale.ROOT), split[0].toLowerCase(Locale.ROOT));
                }
            }
        }
        return morseMap;
    }

    /**
     * @param lines lines
     * @return null
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> output = new ArrayList<>();
        if (!lines.isEmpty()) {
            for (int i = 0; i < lines.size(); i++) {
                output.add(translateLineToMorse(lines.get(i)));
            }
        }
        return output;
    }

    /**
     * @param lines lines
     * @return null
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> output = new ArrayList<>();
        if (!lines.isEmpty()) {
            for (int i = 0; i < lines.size(); i++) {
                output.add(translateLineFromMorse(lines.get(i)));
            }
        }
        return output;
    }

    /**
     * @param line line
     * @return null
     */
    private String translateLineToMorse(String line) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char char1 = line.charAt(i);
            String str = String.valueOf(char1);
            if (str.equals(" ")) {
                output.append("\t");
                continue;
            }
            if (morseMap.containsKey(str.toLowerCase(Locale.ROOT))) {
                output.append(morseMap.get(str.toLowerCase(Locale.ROOT)));
                output.append(" ");
            }
        }
        return output.toString();
    }

    /**
     * @param line line
     * @return null
     */
    private String translateLineFromMorse(String line) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char char1 = line.charAt(i);
            String str = String.valueOf(char1);
            if (str.equals(" ")) {
                output.append("\t");
                continue;
            }
            if (fromMap.containsKey(str.toLowerCase(Locale.ROOT))) {
                output.append(fromMap.get(str.toLowerCase(Locale.ROOT)));
                output.append(" ");
            }
        }
        return output.toString();
    }

//    public static void main(String[] args) {
//        MorseTranslator morseTranslator = new MorseTranslator();
//        morseTranslator.addMorseCodes(List.of("Lorem ipsum dolor sit amet, consectetur adipiscing elit,"));
//        System.out.println(morseTranslator.translateLineToMorse("Lorem ipsum dolor sit amet, consectetur adipiscing elit,"));
//    }
}
