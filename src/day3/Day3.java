package day3;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    private static final  String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
    private static final String path = "src\\day3\\input.txt";

    public static void getAnswer() {
        System.out.println(getSumOfMuls(FileUtils.getOneString(path)));
    }

    private static Integer getSumOfMuls(String input){
        Integer sumOfMuls = 0;
        List<String> matchResults = matchResults(input);
        for (int i=0; i<matchResults.size(); i++) {
            System.out.println(matchResults.get(i));
        }
        List<Integer> onlyNumbers = onlyNumbers(matchResults);

        for (int i=0; i<onlyNumbers.size(); i+=2) {
            sumOfMuls += onlyNumbers.get(i)*onlyNumbers.get(i+1);
        }

        return sumOfMuls;
    }

    private static List<String> matchResults(String input) {
        List<String> matchResults = new ArrayList<String>();

        Matcher matcher = Pattern.compile(regex).matcher(input);
        while (matcher.find()) matchResults.add(matcher.group());

        return matchResults;
    }

    private static List<Integer> onlyNumbers (List<String> stringList) {
        List<Integer> onlyNumbers = new ArrayList<>();
        int start;
        int comma;
        int end;

        for (String s : stringList) {
            start = s.indexOf('(');
            comma = s.indexOf(',');
            end = s.indexOf(')');

            String n1 = s.substring(start + 1, comma).trim();
            String n2 = s.substring(comma + 1, end).trim();

            onlyNumbers.add(Integer.parseInt(n1));
            onlyNumbers.add(Integer.parseInt(n2));
        }

        return onlyNumbers;
    }
}
