package day2;

import utils.FileUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Day2 {

    private static final String path = "src\\day2\\input.txt";
    public static void getAnswer() {
        int safeReportsCounter = 0;
        for (String s : FileUtils.getListOfLines(path)){
            if (isSafeLevel(s)) safeReportsCounter+=1;
        }

        System.out.println(safeReportsCounter);
    }

    private static boolean isSafeLevel(String report) {
        List<Integer> levelList = getLevelList(report);
        boolean isIncreasingPattern = false;
        boolean isSafe = false;

        for (int i=0; i<levelList.size()-1; i++) {
            if (i==0){
                if (levelList.get(i) < levelList.get(i+1)) {
                    isIncreasingPattern = true;
                } else if (levelList.get(i) > levelList.get(i+1)) {
                    isIncreasingPattern = false;
                } else {
                    break;
                }
            }

            if (isIncreasingPattern && levelList.get(i+1) - levelList.get(i) > 0 && levelList.get(i+1) - levelList.get(i) <=3) {
                isSafe = true;
            } else if (!isIncreasingPattern && levelList.get(i) - levelList.get(i+1) > 0 && levelList.get(i) - levelList.get(i+1) <=3 ){
                isSafe = true;
            } else {
                isSafe = false;
                break;
            }
        }

        return isSafe;
    }

    private static List<Integer> getLevelList (String report) {
        List<Integer> levelList = new ArrayList<>();
        for (String s: StringUtils.splitBySpaces(report)) {
            levelList.add(Integer.parseInt(s));
        }

        return levelList;
    }

}
