package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Differ {

    private static String getFileFormat(File file) {
        var filePath = file.getPath();
        if (!filePath.contains(".")) {
            return "";
        }
        var lastIndexOfDot = filePath.lastIndexOf(".") + 1;
        var format = filePath.substring(lastIndexOfDot);

        return format.toLowerCase();
    }

    private static Map getData(String filePath) throws Exception {

        var file = new File(filePath);
        var data = Files.readString(file.toPath());

        var format = getFileFormat(file);
        return Parser.parse(data, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        var map1 = getData(filePath1);
        var map2 = getData(filePath2);

        var diff = Tree.build(map1, map2);

        return Formatter.render(diff, formatName);
    }
}
