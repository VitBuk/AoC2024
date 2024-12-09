package day5;

import java.util.List;

public class Rule {

    private Integer X;
    private Integer Y;

    public Rule(Integer X, Integer Y) {
        this.X = X;
        this.Y = Y;
    }

    public Integer getX() {
        return X;
    }

    public Integer getY() {
        return Y;
    }

    public boolean isOrderedByTheRule(List<Integer> pages) {
        if (!pages.contains(X) || !pages.contains(Y))
            return true;

        return pages.indexOf(X) < pages.indexOf(Y);
    }
}
