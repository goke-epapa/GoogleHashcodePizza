import java.util.ArrayList;

public class Pizza {
    int R;
    int C;
    int L;
    int H;
    ArrayList<ArrayList<Character>> grid = new ArrayList<>();

    @Override
    public String toString() {
        return "Pizza{" +
                "R=" + R +
                ", C=" + C +
                ", L=" + L +
                ", H=" + H +
                ", grid=" + grid +
                '}';
    }
}
