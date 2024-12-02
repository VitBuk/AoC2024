package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    public static List<String> getListOfLines (String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
