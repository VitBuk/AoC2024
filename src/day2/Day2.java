package day2;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static String getAnswer() {
        return "";
    }

    private static boolean isSafeLevel(String level) {
        List<Integer> levelList = getLevelList(level);
        return true;
    }

    private static List<Integer> getLevelList (String level) {
        List<Integer> levelList = new ArrayList<>();
        for (String s: StringUtils.splitBySpaces(level)) {
            levelList.add(Integer.parseInt(s));
        }

        return levelList;
    }

}
