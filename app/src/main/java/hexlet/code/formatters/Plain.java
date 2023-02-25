package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String render(Map<String, Map<String, String>> diff) {

        var builder = new StringBuilder();

        for (var field : diff.keySet()) {

            var metaData = diff.get(field);
            var type = metaData.get("type");
            switch (type) {
                case "added" -> {
                    var value = toPrettyString(metaData.get("value"));
                    builder.append(String.format("Property '%s' was added with value: %s", field, value));
                }

                case "deleted" -> builder.append(String.format("Property '%s' was removed", field));

                case "changed" -> {
                    var oldValue = toPrettyString(metaData.get("value1"));
                    var newValue = toPrettyString(metaData.get("value2"));
                    var changeLog = String.format("Property '%s' was updated. From %s to %s",
                            field, oldValue, newValue);
                    builder.append(changeLog);
                }
                default -> {
                    continue;
                }
            }

            builder.append("\n");
        }

        var trailingNewLineIndex = builder.lastIndexOf("\n");
        builder.replace(trailingNewLineIndex, builder.length(), "");

        return builder.toString();
    }

    private static String toPrettyString(String json) {
        var isArray = json.startsWith("[") && json.endsWith("]");
        var isObject = json.startsWith("{") && json.endsWith("}");
        if (isArray || isObject) {
            return "[complex value]";
        }

        if (List.of("true", "false", "null").contains(json)) {
            return json;
        }

        try {
            Double.parseDouble(json);
        } catch (NumberFormatException nfe) {
            return "'" + json + "'";
        }

        return json;
    }
}
