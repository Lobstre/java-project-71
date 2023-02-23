package hexlet.code.formatters;

import java.util.HashMap;
import java.util.TreeMap;

public class StylishFormatter {
    public static String stylishFormatter(TreeMap<String, HashMap<String, String>> diff) {
        StringBuilder builder = new StringBuilder("{\n");
        for (var field : diff.keySet()) {
            var changes = diff.get(field);

            if (changes.containsKey("=")) {
                var value = toPrettyString(changes.get("="));
                builder.append(String.format("    %s: %s\n", field, value));
                continue;
            }
            if (changes.containsKey("-")) {
                var value = toPrettyString(changes.get("-"));
                builder.append(String.format("  - %s: %s\n", field, value));
            }
            if (changes.containsKey("+")) {
                var value = toPrettyString(changes.get("+"));
                builder.append(String.format("  + %s: %s\n", field, value));
            }
        }
        builder.append("}");

        return builder.toString();
    }

    private static String toPrettyString(String text) {
        var prettyText = text.replace("\"", "");
        if (prettyText.startsWith("[") || prettyText.startsWith("{")) {
            prettyText = prettyText.replace(",", ", ").replace(":", "=");
        }
        return prettyText;
    }
}