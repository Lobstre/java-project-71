package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @Override
    public final Integer call() throws Exception {
        try {
            var diff = Differ.generate(filePath1, filePath2, format);
            System.out.println(diff);
            return SUCCESS_EXIT_CODE;
        } catch (Exception e) {
            return ERROR_EXIT_CODE;
        }
    }

    public static void main(String... args) {
        var exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
