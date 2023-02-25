package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map parse(String data, String format) throws Exception {
        var result = switch (format) {
            case "json" -> Parser.parseJson(data);
            case "yml", "yaml" -> Parser.parseYaml(data);
            default -> throw new Exception("Unsupported format");
        };

        return result;
    }
    static Map parseJson(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = objectMapper.readValue(data, HashMap.class);
        return stringifyMapValues(dataMap);
    }

    static Map parseYaml(String data) throws Exception {
        var objectMapper = new YAMLMapper();
        Map<String, Object> dataMap = objectMapper.readValue(data, HashMap.class);
        return stringifyMapValues(dataMap);
    }

    private static Map stringifyMapValues(Map<String, Object> dataMap) {
        HashMap<String, String> result = new HashMap<>();
        for (var key : dataMap.keySet()) {
            var value = dataMap.get(key);
            result.put(key, String.valueOf(value));
        }

        return result;
    }
}
