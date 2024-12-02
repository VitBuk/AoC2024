package utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static List<String> splitBySpaces(String text) {
        text = text.trim();
        String[] tokens = text.split(" +");
        return Arrays.asList(tokens);
    }
}
