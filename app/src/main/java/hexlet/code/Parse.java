package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parse {

    public static String parse(String readFilePath) throws Exception {

        Path path = Paths.get(readFilePath);
        if (!Files.exists(path)) {
            var message = String.format("File %s does not exist", path);
            throw new Exception(message);
        }

        return Files.readString(path);
    }
}