package day6;

public class Guard {

    private Integer x;
    private Integer y;
    private Directon directon;
    private Integer nextX;
    private Integer nextY;

    public Guard(Integer x, Integer y, Directon directon) {
        //starting position
        this.x = x;
        this.y = y;
        this.directon = directon;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Directon getDirecton() {
        return directon;
    }

    public void setDirecton(Directon directon) {
        this.directon = directon;
    }

    public Integer getNextX() {
        return nextX;
    }

    public void setNextX(Integer nextX) {
        this.nextX = nextX;
    }

    public Integer getNextY() {
        return nextY;
    }

    public void setNextY(Integer nextY) {
        this.nextY = nextY;
    }
}
