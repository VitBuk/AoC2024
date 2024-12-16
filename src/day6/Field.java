package day6;

import java.util.List;

public class Field {

    private List<List<String>> field;
    private Guard guard;

    public Field(List<List<String>> field, Guard guard) {
        this.field = field;
        this.guard = guard;
    }

    public String getByLocation(int x, int y) {
        return field.get(x).get(y);
    }

    public void markWithX(int x, int y){
        field.get(x).set(y, "X");
    }

    public Guard moveGuard(Guard guard) {
        if (canMove(guard)) {
            // sign previous guard position with X
            field.get(guard.getX()).set(guard.getY(), "X");

            if (guard.getDirecton() == Directon.NORTH)
                guard.setX(guard.getX() - 1);
            else if (guard.getDirecton() == Directon.EAST)
                guard.setY(guard.getY() + 1 );
            else if (guard.getDirecton() == Directon.WEST)
                guard.setY(guard.getY() - 1);
            else if (guard.getDirecton() == Directon.SOUTH)
                guard.setX(guard.getX() + 1);
        }

        return guard;
    }

    private boolean canMove(Guard guard) {
        if (guard.getDirecton() == Directon.NORTH) {
            return field.get(guard.getX() - 1).get(guard.getY()).equals(".") ||
                    field.get(guard.getX() - 1).get(guard.getY()).equals("X");
        } else if (guard.getDirecton() == Directon.EAST)
            return field.get(guard.getX()).get(guard.getY() + 1).equals(".") ||
                    field.get(guard.getX()).get(guard.getY() + 1).equals("X");
        else if (guard.getDirecton() == Directon.WEST)
            return field.get(guard.getX()).get(guard.getY() - 1).equals(".") ||
                    field.get(guard.getX()).get(guard.getY() - 1).equals("X");
        else if (guard.getDirecton() == Directon.SOUTH)
            return field.get(guard.getX() + 1).get(guard.getY()).equals(".") ||
                    field.get(guard.getX() + 1).get(guard.getY()).equals("X");
        else System.out.println("IMPOSSIBLE DIRECTION");

        return false;
    }

    public Guard getGuard() {
        return guard;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }
}
