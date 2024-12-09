package day5;

import utils.FileUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    private static final String path = "src\\day5\\input.txt";
    private static final String rulesPath = "src\\day5\\rules.txt";
    private static List<Rule> rules;
    public static void getAnswer(){
        setRules(FileUtils.getListOfLines(rulesPath));
        List<List<Integer>> listOfUpdates = new ArrayList<>();
        listOfUpdates = getListOfInputs(FileUtils.getListOfLines(path));
        System.out.println(getMiddlePageSum(listOfUpdates));

        List<List<Integer>> incorrectUpdates = getIncorrectUpdates(listOfUpdates);
        List<List<Integer>> correctedUpdates = new ArrayList<>();

        for (List<Integer> update : incorrectUpdates) {
            correctedUpdates.add(putInCorrectOrder(update));
        }

        System.out.println(getMiddlePageSum(correctedUpdates));
    }

    private static void setRules(List<String> textRules) {
        rules = new ArrayList<>();
        for (String textRule : textRules) {
            Rule rule = new Rule(Integer.parseInt(textRule.substring(0,2)), Integer.parseInt(textRule.substring(3,5)));
            rules.add(rule);
        }
    }

    private static List<List<Integer>> getListOfInputs(List<String> updates) {
        List<List<Integer>> listOfInputs = new ArrayList<>();
        for (String update : updates) {
            List<String> pages = StringUtils.splitByRegex(update, ",");
            List<Integer> integerInput = new ArrayList<>();
            for (String page : pages) {
                integerInput.add(Integer.parseInt(page));
            }

            listOfInputs.add(integerInput);
        }

        return listOfInputs;
    }

    private static List<List<Integer>> getIncorrectUpdates(List<List<Integer>> updates) {
        List<List<Integer>> incorrectUpdates = new ArrayList<>();
        for (List<Integer> update : updates) {
            for (Rule rule : rules) {
                if (!rule.isOrderedByTheRule(update)) {
                    incorrectUpdates.add(update);
                    break;
                }
            }
        }

        return incorrectUpdates;
    }

    private static List<Integer> putInCorrectOrder(List<Integer> update) {
        boolean allRulesPassed = false;
        while (!allRulesPassed) {
            for (Rule rule : rules) {
                if (!rule.isOrderedByTheRule(update)) {
                    Collections.swap(update, update.indexOf(rule.getX()), update.indexOf(rule.getY()));
                }
            }

            for (Rule rule : rules) {
                if (rule.isOrderedByTheRule(update)) {
                    allRulesPassed = true;
                } else {
                    allRulesPassed = false;
                    break;
                }
            }
        }

        return update;
    }
    private static Integer getMiddlePageSum(List<List<Integer>> listOfInputs) {
        Integer sumOfMiddles = 0;
        boolean allRulesPassed = true;
        for (List<Integer> input : listOfInputs) {
            String output = input.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));

            for (Rule rule : rules) {
                if (!rule.isOrderedByTheRule(input)) {
                    allRulesPassed = false;
                    break;
                }
            }

            if (allRulesPassed)
                sumOfMiddles += input.get(input.size()/2);

            allRulesPassed = true;
        }

        return sumOfMiddles;
    }
}
