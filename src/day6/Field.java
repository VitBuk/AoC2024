package day6;

import java.util.List;

public class Field {

    List<List<String>> field;

    public Field(List<List<String>> field) {
        this.field = field;
    }

    public String getByLocation(int x, int y) {
        return field.get(x).get(y);
    }
    
}
