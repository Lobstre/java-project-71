package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

    @Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Hello world 1.0",
        description = "Compares two configuration files and shows a difference.")
    class HelloWorld implements Callable<String> {

        @Option(names = { "-f", "--format" }, description = "output format", defaultValue = "stylish", showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
        String format;

        @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
        String firstFile;

        @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
        String secondFile;

        @Override
    public String call() throws Exception {
        System.out.println("Hello world!");
        return "Hello world!";
    }
}
public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new HelloWorld()).execute(args);
        System.exit(exitCode);
    }
}
