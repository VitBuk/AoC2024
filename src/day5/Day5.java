package day5;

import utils.FileUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    private static final String path = "src\\day5\\input.txt";
    private static final String rulesPath = "src\\day5\\rules.txt";
    private static List<Rule> rules;
    public static void getAnswer(){
        setRules(FileUtils.getListOfLines(rulesPath));
        Integer middleSum = getMiddlePageSum(getListOfInputs(FileUtils.getListOfLines(path)));
        System.out.println(middleSum);
    }

    private static void setRules(List<String> textRules) {
        rules = new ArrayList<>();
        for (String textRule : textRules) {
            Rule rule = new Rule(Integer.parseInt(textRule.substring(0,2)), Integer.parseInt(textRule.substring(3,5)));
            rules.add(rule);
        }

        for (Rule rule : rules) {
            System.out.println("Rule: x = " + rule.getX() + " y = " + rule.getY());
        }
    }

    private static List<List<Integer>> getListOfInputs(List<String> inputs) {
        List<List<Integer>> listOfInputs = new ArrayList<>();
        for (String input : inputs) {
            List<String> pages = StringUtils.splitByRegex(input, ",");
            List<Integer> integerInput = new ArrayList<>();
            for (String page : pages) {
                integerInput.add(Integer.parseInt(page));
            }

            listOfInputs.add(integerInput);
        }

        for  (List<Integer> list : listOfInputs) {
            String output = list.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));

            System.out.println(output);
        }
        return listOfInputs;
    }

    private static Integer getMiddlePageSum(List<List<Integer>> listOfInputs) {
        Integer sumOfMiddles = 0;
        boolean allRulesPassed = true;
        for (List<Integer> input : listOfInputs) {
            String output = input.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));

            System.out.println("Output: " + output);

            for (Rule rule : rules) {
                System.out.println("Rule: " + rule.getX() + " | " + rule.getY());
                if (!rule.isOrderedByTheRule(input)) {
                    System.out.println("False");
                    allRulesPassed = false;
                    break;
                }
            }

            System.out.println("middle: " + input.get(input.size()/2));
            if (allRulesPassed)
                sumOfMiddles += input.get(input.size()/2);

            allRulesPassed = true;
        }

        return sumOfMiddles;
    }
}
