package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {
    // helper method to get the output expected for a test
    public static String GetOutputFromFile(String fileName) {
        String rootPath = System.getProperty("user.dir");
        String testsFilesPath = Paths.get(rootPath, "src", "test", "java", "utils", "outputs").toString();

        Path fileTestPath = Paths.get(testsFilesPath, fileName);
        String output = null;
        try {
            output = String.join("\n", Files.readAllLines(fileTestPath, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}
