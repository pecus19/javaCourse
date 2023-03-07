package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
                    fromMap.put(split[1].toLowerCase(Locale.ROOT), split[0].toLowerCase(Locale.ROOT));
                }
            }
        }
        System.out.println(morseMap);
        System.out.println(fromMap);
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
    public String translateLineToMorse(String line) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char char1 = line.charAt(i);
            String str = String.valueOf(char1);
            if (morseMap.containsKey(str.toLowerCase(Locale.ROOT))) {
                output.append(morseMap.get(str.toLowerCase(Locale.ROOT)));
                if (i != line.length() - 1) {
                    output.append(" ");
                }
            } else {
                output.append(" ");
            }
        }

        return output.toString();
    }

    /**
     * @param line line
     * @return null
     */
    public String translateLineFromMorse(String line) {
//        System.out.println(fromMap);
        StringBuilder output = new StringBuilder();
        String[] words = line.split("\\t");
        for (int i = 0; i < words.length; i++) {
            String[] splt = words[i].split(" ");
            for (int j = 0; j < splt.length; j++) {
                output.append(fromMap.get(splt[j]));
            }
            if (i != words.length - 1) {
                output.append(" ");
            }
        }
        return output.toString();
    }
}
