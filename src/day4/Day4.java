package day4;

import utils.FileUtils;
import utils.StringUtils;

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
        return findALlLeftToRight(StringUtils.columnsToRows(input));
    }

    private static Integer findAllBottomToUp(String input) {
        String newString = StringUtils.columnsToRows(input);
        String reversedNewString = new StringBuilder(newString).reverse().toString();
        return findALlLeftToRight(reversedNewString);
    }

    private static Integer findAllNWDiagonals(String input) {
        return findALlLeftToRight(StringUtils.NWDiagonalsToRows(input));
    }

    private static Integer findAllSWDiagonals(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return findALlLeftToRight(StringUtils.NWDiagonalsToRows(reversed));
    }

    private static Integer findALlNEDiagonals(String input) {
        String allRowsReversed = StringUtils.allRowsReversed(input);
        return findAllNWDiagonals(allRowsReversed);
    }

    private static Integer findAllSEDiagonals(String input) {
        String allRowsReversed = StringUtils.allRowsReversed(input);
        String reverseAllRowsReversed = new StringBuilder(allRowsReversed).reverse().toString();
        return findAllNWDiagonals(reverseAllRowsReversed);
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