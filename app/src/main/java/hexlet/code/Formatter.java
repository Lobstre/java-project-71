package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

import static hexlet.code.RenderDiff.RenderDiff;

public class Formatter {
    public static String render(Map diff, String formatName) throws Exception {
        var renderedText = switch (RenderDiff) {
            case stylish -> Stylish.render(diff);
            case plain -> Plain.render(diff);
            case json -> Json.render(diff);
            default -> {
                var message = String.format("Unknown format name: %s. Can be 'stylish', 'plain' or 'json'",
                        formatName);
                throw new Exception(message);
            }
        };

        return renderedText;
    }
}
