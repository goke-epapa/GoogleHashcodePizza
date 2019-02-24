import java.util.ArrayList;

public class Slice {

    public Slice(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    Point p1;
    Point p2;
    ArrayList<ArrayList<Character>> grid = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("%d %d %d %d", p1.r, p1.c, p2.r, p2.c);
    }
}
