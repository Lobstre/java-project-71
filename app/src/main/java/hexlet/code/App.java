package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

    @Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Hello world 1.0",
        description = "Compares two configuration files and shows a difference.")
    class HelloWorld implements Callable<String> {

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