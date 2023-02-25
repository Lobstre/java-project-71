package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Tree {

    public static <T extends Map<String, String>> Map build(T map1, T map2) {

        TreeSet<String> fields = new TreeSet<>();
        fields.addAll(map1.keySet());
        fields.addAll(map2.keySet());

        TreeMap<String, LinkedHashMap<String, String>> result = new TreeMap<>();

        for (var field : fields) {

            LinkedHashMap<String, String> metaData = new LinkedHashMap<>();

            if (!map1.containsKey(field)) {
                metaData.put("type", "added");
                metaData.put("value", map2.get(field));
            } else if (!map2.containsKey(field)) {
                metaData.put("type", "deleted");
                metaData.put("value", map1.get(field));
            } else {
                var oldValue = map1.get(field);
                var newValue = map2.get(field);

                if (!oldValue.equals(newValue)) {
                    metaData.put("type", "changed");
                    metaData.put("value1", oldValue);
                    metaData.put("value2", newValue);
                } else {
                    metaData.put("type", "unchanged");
                    metaData.put("value", newValue);
                }
            }
            result.put(field, metaData);
        }

        return result;
    }
}
