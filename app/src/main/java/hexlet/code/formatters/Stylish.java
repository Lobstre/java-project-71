package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String render(Map<String, Map<String, String>> diff) {
        var builder = new StringBuilder("{\n");

        for (var field: diff.keySet()) {
            var metaData = diff.get(field);

            var type = metaData.get("type");
            switch (type) {
                case "added" -> {
                    var value = toPrettyString(metaData.get("value"));
                    var changeLog = String.format("  %s %s: %s\n", "+", field, value);
                    builder.append(changeLog);
                }

                case "deleted" -> {
                    var value = toPrettyString(metaData.get("value"));
                    var changeLog = String.format("  %s %s: %s\n", "-", field, value);
                    builder.append(changeLog);
                }

                case "changed" -> {
                    var oldValue = toPrettyString(metaData.get("value1"));
                    var newValue = toPrettyString(metaData.get("value2"));
                    var changeLog1 = String.format("  %s %s: %s\n", "-", field, oldValue);
                    var changeLog2 = String.format("  %s %s: %s\n", "+", field, newValue);
                    builder.append(changeLog1);
                    builder.append(changeLog2);
                }
                case "unchanged" -> {
                    var value = toPrettyString(metaData.get("value"));
                    var changeLog = String.format("  %s %s: %s\n", " ", field, value);
                    builder.append(changeLog);
                }
                default -> throw new RuntimeException();
            }
        }

        builder.append("}");

        return builder.toString();
    }

    private static String toPrettyString(String text) {
        var prettyText = text;
        if (prettyText.startsWith("{")) {
            prettyText = prettyText.replace(":", "=");
        }
        return prettyText;
    }
}
