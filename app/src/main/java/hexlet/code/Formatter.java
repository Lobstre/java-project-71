package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.HashMap;
import java.util.TreeMap;

public class Formatter {
    public static String formatter(TreeMap<String, HashMap<String, String>> diff, String formatName) throws Exception {
        switch (formatName) {
            case "stylish" -> {
                return StylishFormatter.stylishFormatter(diff);
            }
            case "plain" -> {
                return PlainFormatter.plainFormatter(diff);
            }
            case "json" -> {
                return JsonFormatter.jsonFormatter(diff);
            }
            default -> {
                var message = String.format("Unknown format name: %s. Can be 'stylish', 'plain' or 'json'",
                        formatName);
                throw new Exception(message);
            }
        }
    }
}
