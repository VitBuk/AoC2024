package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String getOneString(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<List<String>> getListOfListOfStrings(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            List<List<String>> listOfLines = lines.stream()
                    .map(row -> row.chars()
                            .mapToObj(c -> String.valueOf((char)c))
                            .collect(Collectors.toList()))
                    .toList();

            return listOfLines;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
