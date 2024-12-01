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

        return String.valueOf(getSumOfDistances(list1, list2));
    }

    private static Integer getSumOfDistances(List<Integer> list1, List<Integer> list2) {
        int sum = 0;
        for (int i=0; i<list1.size(); i++) {
            System.out.println("i: " + i);
            System.out.println("list1: " + list1.get(i));
            System.out.println("list2: " + list2.get(i));
            System.out.println("mod: " + Math.abs(list1.get(i) - list2.get(i)));

            sum += Math.abs(list1.get(i) - list2.get(i));
            System.out.println("sum: " + sum);

        }

        return sum;
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
