package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    public static List<String> splitBySpaces(String text) {
        text = text.trim();
        String[] tokens = text.split(" +");
        return Arrays.asList(tokens);
    }

    public static String columnsToRows(String input) {
        String[] lines = input.split("\n");

        StringBuilder[] newLines = new StringBuilder[lines[0].length()];

        for (int i=0; i < lines[0].length(); i++) {
            newLines[i] = new StringBuilder();
        }

        for (String line : lines) {
            for (int column = 0; column < lines[0].length(); column++) {
                newLines[column].append(line.charAt(column));
            }
        }

        StringBuilder newString = new StringBuilder();
        for (int i=0; i<lines[0].length(); i++) {
            newString.append(newLines[i].toString());
            if ( i < lines[0].length() - 1) {
                newString.append("\n");
            }
        }

        return newString.toString();
    }

    public static String allRowsReversed (String input) {
        return Arrays.stream(input.split("\\R"))
                .map(line -> new StringBuilder(line).reverse().toString())
                .collect(Collectors.joining("\n"));
    }

    public static String NWDiagonalsToRows(String input) {
        String[] lines = input.split("\n");

        int length = lines.length;

        int diagonalsAmount = 2 * length - 1;

        StringBuilder[] diagonals = new StringBuilder[diagonalsAmount];
        for (int i = 0; i < diagonalsAmount; i++) {
            diagonals[i] = new StringBuilder();
        }

        for (int k = 0; k < diagonalsAmount; k++) {
            int startRow = Math.max(0, k - length + 1);
            int endRow = Math.min(length - 1, k);

            for (int i = startRow; i <= endRow; i++) {
                int j = k - i;
                char ch = lines[i].charAt(j);
                diagonals[k].insert(0, ch);
            }
        }

        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < diagonalsAmount; i++) {
            newString.append(diagonals[i].toString());
            if (i < diagonalsAmount - 1) {
                newString.append('\n');
            }
        }

        return newString.toString();
    }

}
