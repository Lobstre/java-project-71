package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.TreeMap;

public class JsonFormatter {
//    public static String jsonFormatter(TreeMap<String, HashMap<String, String>> diff) {
//
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("{\n");
//        for (var field : diff.keySet()) {
//            var changes = diff.get(field);
//
//            builder.append(String.format("  \"%s\": ", field));
//
//            if (changes.containsKey("+") && changes.containsKey("-")) {
//                var oldValue = changes.get("-");
//                var newValue = changes.get("+");
//                builder.append(String.format("{ \"-\": %s, \"+\": %s }", oldValue, newValue));
//
//            } else if (changes.containsKey("-")) {
//                var value = changes.get("-");
//                builder.append(String.format("{ \"-\": %s }", value));
//
//            } else if (changes.containsKey("+")) {
//                var value = changes.get("+");
//                builder.append(String.format("{ \"+\": %s }", value));
//
//            } else if (changes.containsKey("=")) {
//                var value = changes.get("=");
//                builder.append(String.format("{ \"=\": %s }", value));
//            }
//
//            if (!diff.lastKey().equals(field)) {
//                builder.append(",\n");
//            }
//        }
//        builder.append("\n}");
//
//        return builder.toString();
//    }

    public static String jsonFormatter(TreeMap<String, HashMap<String, String>> diff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }
}