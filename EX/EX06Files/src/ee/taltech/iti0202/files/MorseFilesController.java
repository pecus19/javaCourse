//package ee.taltech.iti0202.files;
//
//import ee.taltech.iti0202.files.input.InputFilesBufferReader;
//import ee.taltech.iti0202.files.input.InputFilesScanner;
//import ee.taltech.iti0202.files.morse.MorseTranslator;
//import ee.taltech.iti0202.files.output.OutputFilesWriter;
//
//import java.util.List;
//import java.util.Map;
//
//public class MorseFilesController {
//
//    public static void main(String[] args) {
//        InputFilesScanner scanner = new InputFilesScanner();
//        List<String> lines = scanner.readTextFromFile("C:\\Users\\danil\\git\\iti0202-2023\\EX\\"
//                + "EX06Files\\src\\ee\\taltech\\iti0202\\files\\morse.txt");
//        lines.forEach(System.out::println); //lines in morse.txt which contains Morse codes
//
//        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
//        List<String> lines2 = bufferReader.readTextFromFile("C:\\Users\\danil\\git\\iti0202-2023\\EX\\"
//                + "EX06Files\\src\\ee\\taltech\\iti0202\\files\\morse.txt");
//        lines2.forEach(System.out::println); //lines in morse.txt which contains Morse codes
//
//        MorseTranslator translator = new MorseTranslator();
//        Map<String, String> codes = translator.addMorseCodes(lines);
//        codes.forEach((key, value) -> System.out.println(key + " " + value)); //key and value
//
//        List<String> input = scanner.readTextFromFile("C:\\Users\\danil\\git\\iti0202-2023\\EX\\EX06Files\\"
//                + "src\\ee\\taltech\\iti0202\\files\\input.txt");
//        input.forEach(System.out::println); //your input lines
//        System.out.println(translator.translateLineToMorse("lelit,"));
////        System.out.println(translator.translateLineToMorse2("lorem ipsum dolor sit amet, consectetur adipiscing elit,"));
//        System.out.println(".-.. --- .-. . --\t.. .--. ... ..- --\t-.. --- .-.. --- .-.\t... .. -\t.- -- . - --..--\t-.-. --- -. ... . -.-. - . - ..- .-.\t.- -.. .. .--. .. ... -.-. .. -. --.\t. .-.. .. - --..--");
////        List<String> morseLines = translator.translateLinesToMorse(input);
////        morseLines.forEach(System.out::println); //your input lines in Morse
//        System.out.println("--------------");
////        List<String> normalLines = translator.translateLinesFromMorse(morseLines);
//        System.out.println(translator.translateLineFromMorse(".-.. --- .-. . --\t.. .--. ... ..- --\t-.. --- .-.. --- .-.\t... .. -\t.- -- . - --..--\t-.-. --- -. ... . -.-. - . - ..- .-.\t.- -.. .. .--. .. ... -.-. .. -. --.\t. .-.. .. - --..--"));
////        normalLines.forEach(System.out::println); //your input lines in regular text
////
////        OutputFilesWriter writer = new OutputFilesWriter();
////        System.out.println(writer.writeLinesToFile(normalLines, "C:\\Users\\danil\\git\\iti0202-2023\\EX\\"
////                + "EX06Files\\src\\ee\\taltech\\iti0202\\files\\output.txt")); //true
////        //This should also create a new file/ write in an existing file
//    }
//}
