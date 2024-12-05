package day5;

public class Rule {

    private static Integer first;
    private static Integer next;

    public Rule(Integer first, Integer next) {
        Rule.first = first;
        Rule.next = next;
    }

    public static Integer getFirst() {
        return first;
    }

    public static void setFirst(Integer first) {
        Rule.first = first;
    }

    public static Integer getNext() {
        return next;
    }

    public static void setNext(Integer next) {
        Rule.next = next;
    }
}
