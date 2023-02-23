package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {

    public static String generate(String text1, String text2, String formatName) throws Exception {

        if (text1.isEmpty() && text2.isEmpty()) {
            return "";
        }

        var diff = differ(text1, text2);
        return Formatter.formatter(diff, formatName);
    }

    private static TreeMap<String, HashMap<String, String>> differ(String text1, String text2)
            throws JsonProcessingException {

        ObjectMapper objectMapper = getMapper(text1);

        TreeSet<String> fields = new TreeSet<>();

        JsonNode node1 = objectMapper.readTree(text1);
        JsonNode node2 = objectMapper.readTree(text2);

        Iterator<String> it1 = node1.fieldNames();
        while (it1.hasNext()) {
            fields.add(it1.next());
        }
        Iterator<String> it2 = node2.fieldNames();
        while (it2.hasNext()) {
            fields.add(it2.next());
        }

        TreeMap<String, HashMap<String, String>> result = new TreeMap<>();
        for (var field : fields) {

            HashMap<String, String> changes = new HashMap<>();
            result.put(field, changes);

            if (!node1.has(field)) {
                result.get(field).put("+", node2.get(field).toString());
            } else if (!node2.has(field)) {
                result.get(field).put("-", node1.get(field).toString());
            } else {
                var value1 = node1.get(field).toString();
                var value2 = node2.get(field).toString();
                if (value1.equals(value2)) {
                    result.get(field).put("=", value2);
                } else {
                    result.get(field).put("-", value1);
                    result.get(field).put("+", value2);
                }
            }
        }

        return result;
    }

    private static ObjectMapper getMapper(String text) {
        ObjectMapper objectMapper;

        try {
            objectMapper = new ObjectMapper();
            objectMapper.readTree(text);
        } catch (Exception e1) {
            objectMapper = new YAMLMapper();
            try {
                objectMapper.readTree(text);
            } catch (Exception e2) {
                objectMapper = null;
            }
        }

        return objectMapper;
    }
}
