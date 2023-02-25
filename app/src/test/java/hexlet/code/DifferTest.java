package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String resourcesPath;
    private static String jsonFilePath1;
    private static String jsonFilePath2;
    private static String yamlFilePath1;
    private static String yamlFilePath2;

    @BeforeAll
    public static void beforeAll() {
        resourcesPath = new File("src/test/resources").getAbsolutePath();

        jsonFilePath1 = resourcesPath + "/fixtures/json/file1.json";
        jsonFilePath2 = resourcesPath + "/fixtures/json/file2.json";

        yamlFilePath1 = resourcesPath + "/fixtures/yml/file1.yml";
        yamlFilePath2 = resourcesPath + "/fixtures/yml/file2.yml";
    }

    @Test
    public void testDefaultValue() {
        assertDoesNotThrow(() -> {
            new CommandLine(new App()).execute(jsonFilePath1, jsonFilePath2);
        });
    }

    private void testStylishAbstract(String filePath1, String filePath2) throws Exception {
        var path = Paths.get(resourcesPath + "/fixtures/expectedStylish");
        var expected = Files.readString(path);
        var actual = Differ.generate(filePath1, filePath2, "stylish");

        assertEquals(expected, actual);
    }

    private void testPlainAbstract(String filePath1, String filePath2) throws Exception {
        var path = Paths.get(resourcesPath + "/fixtures/expectedPlain");
        var expected = Files.readString(path);
        var actual = Differ.generate(filePath1, filePath2, "plain");

        assertEquals(expected, actual);
    }

    private void testJsonAbstract(String filePath1, String filePath2) throws Exception {
        Path expectedPath = Paths.get(resourcesPath + "/fixtures/expectedJson.json");
        var expected = Files.readString(expectedPath);
        var actual = Differ.generate(filePath1, filePath2, "json");

        assertEquals(expected, actual);
    }

    @Test
    public void testJsonStylish() throws Exception {
        testStylishAbstract(jsonFilePath1, jsonFilePath2);
    }

    @Test
    public void testYamlStylish() throws Exception {
        testStylishAbstract(yamlFilePath1, yamlFilePath2);
    }

    @Test
    public void testJsonPlain() throws Exception {
        testPlainAbstract(jsonFilePath1, jsonFilePath2);
    }

    @Test
    public void testYamlPlain() throws Exception {
        testPlainAbstract(yamlFilePath1, yamlFilePath2);
    }

    @Test
    public void testJsonJson() throws Exception {
        testJsonAbstract(jsonFilePath1, jsonFilePath2);
    }

    @Test
    public void testYamlJson() throws Exception {
        testJsonAbstract(yamlFilePath1, yamlFilePath2);
    }
}
