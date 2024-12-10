package day6;

import utils.FileUtils;

public class Day6 {
    private static final String path = "src\\day6\\input.txt";

    public static void getAnswer() {
        System.out.println(FileUtils.getListOfLines(path).get(0).length());
    }


}
