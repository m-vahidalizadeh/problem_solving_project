package base;

public class Pair {

    public Integer x;
    public Integer y;

    public Pair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Pair p = (Pair) o;
        if (p.x == this.x && p.y == this.y) {
            return true;
        }
        return false;
    }

}
