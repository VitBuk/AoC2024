package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    private static final String path = "src\\day1\\input.txt";
    public static String getAnswer(){
        List<String> listOfLines = getListOfLines(path);
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (String line : listOfLines) {
            list1.add(Integer.parseInt(line.substring(0,5)));
            list2.add(Integer.parseInt(line.substring(8,13)));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        getSumOfSimilarities(list1, list2);
        return String.valueOf(getSumOfDistances(list1, list2));
    }

    private static Integer getSumOfDistances(List<Integer> list1, List<Integer> list2) {
        int sum = 0;
        for (int i=0; i<list1.size(); i++) {
            sum += Math.abs(list1.get(i) - list2.get(i));
        }

        return sum;
    }

    private static void getSumOfSimilarities(List<Integer> list1, List<Integer> list2) {
        int simCount = 0;
        long sumOfSims = 0;

        for (int i=0; i<list1.size(); i++) {
           for (int j=0; j<list2.size(); j++) {
               System.out.println("list1.get(i): " + list1.get(i));
               System.out.println("list2.get(j): " + list2.get(j));

               if (list1.get(i).intValue() < list2.get(j).intValue() ) {
                   sumOfSims += list1.get(i) * simCount;
                   simCount = 0;
                   break;
               } else if (list1.get(i).intValue() == list2.get(j).intValue()) {
                   simCount++;
               }
           }
        }

        System.out.println("sumOfSims: " + sumOfSims);
    }

    private static List<String> getListOfLines (String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
