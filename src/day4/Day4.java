package day4;

import utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {
    private static final String path = "src\\day4\\input.txt";
    private static final String pattern =  "XMAS";

    public static void getAnswer(){
        System.out.println(findAll(FileUtils.getOneString(path)));
        System.out.println(findAllMASCrosses(FileUtils.getOneString(path)));
    }

    private static Integer findAll(String input) {
        int xmasCount = 0;
        xmasCount += findALlLeftToRight(input);
        xmasCount += findAllBackwards(input);
        xmasCount += findAllUpToBottom(input);
        xmasCount += findAllBottomToUp(input);
        xmasCount += findAllNWDiagonals(input);
        xmasCount += findALlNEDiagonals(input);
        xmasCount += findAllSWDiagonals(input);
        xmasCount += findAllSEDiagonals(input);

        return xmasCount;
    }

    private static Integer findALlLeftToRight(String input) {
        int count = 0;
        Matcher matcher = Pattern.compile(pattern).matcher(input);

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private static Integer findAllBackwards(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return findALlLeftToRight(reversed);
    }

    private static Integer findAllUpToBottom(String input) {
        return findALlLeftToRight(columnsToRows(input));
    }

    private static Integer findAllBottomToUp(String input) {
        String newString = columnsToRows(input);
        String reversedNewString = new StringBuilder(newString).reverse().toString();
        return findALlLeftToRight(reversedNewString);
    }

    private static Integer findAllNWDiagonals(String input) {
        return findALlLeftToRight(NWDiagonalsToRows(input));
    }

    private static Integer findAllSWDiagonals(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return findALlLeftToRight(NWDiagonalsToRows(reversed));
    }

    private static Integer findALlNEDiagonals(String input) {
        String allRowsReversed = allRowsReversed(input);
        return findAllNWDiagonals(allRowsReversed);
    }

    private static Integer findAllSEDiagonals(String input) {
        String allRowsReversed = allRowsReversed(input);
        String reverseAllRowsReversed = new StringBuilder(allRowsReversed).reverse().toString();
        return findAllNWDiagonals(reverseAllRowsReversed);
    }

    private static String allRowsReversed (String input) {
        return Arrays.stream(input.split("\\R"))
                .map(line -> new StringBuilder(line).reverse().toString())
                .collect(Collectors.joining("\n"));
    }

    private static String NWDiagonalsToRows(String input) {
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

    private static String columnsToRows(String input) {
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

    private static Integer findAllMASCrosses(String input) {
        String[] lines = input.split("\n");

        int rowsLength = lines.length;
        if (rowsLength == 0) return 0;
        int columnsLength = lines[0].length();

        char[][] grid = new char[rowsLength][columnsLength];

        for (int i = 0; i < rowsLength; i++) {
            String line = lines[i];

            for (int j = 0; j < columnsLength; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        int crossCount = 0;

        for (int i = 1; i < rowsLength - 1; i++) {
            for (int j = 1; j < columnsLength - 1; j++) {
                if (grid[i][j] == 'A') {
                    if (isPattern1(grid, i, j)) crossCount++;
                    if (isPattern2(grid, i, j)) crossCount++;
                    if (isPattern3(grid, i, j)) crossCount++;
                    if (isPattern4(grid, i, j)) crossCount++;
                }
            }
        }

        return crossCount;
    }

    private static boolean isPattern1(char[][] grid, int i, int j) {
        return grid[i - 1][j - 1] == 'M' &&
                grid[i - 1][j + 1] == 'M' &&
                grid[i + 1][j - 1] == 'S' &&
                grid[i + 1][j + 1] == 'S';
    }

    private static boolean isPattern2(char[][] grid, int i, int j) {
        return grid[i - 1][j - 1] == 'M' &&
                grid[i + 1][j - 1] == 'M' &&
                grid[i - 1][j + 1] == 'S' &&
                grid[i + 1][j + 1] == 'S';
    }

    private static boolean isPattern3(char[][] grid, int i, int j) {
        return grid[i - 1][j - 1] == 'S' &&
                grid[i + 1][j - 1] == 'S' &&
                grid[i - 1][j + 1] == 'M' &&
                grid[i + 1][j + 1] == 'M';
    }

    private static boolean isPattern4(char[][] grid, int i, int j) {
        return grid[i - 1][j - 1] == 'S' &&
                grid[i - 1][j + 1] == 'S' &&
                grid[i + 1][j - 1] == 'M' &&
                grid[i + 1][j + 1] == 'M';
    }
}