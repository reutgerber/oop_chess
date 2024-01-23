
import java.util.Comparator;

public class CompareByX implements Comparator<Position> {
    @Override
    public int compare(Position p1, Position p2) {
        if (p1.col>p2.col)
            return 1;
        if (p1.col==p2.col)
            return 0;
        return -1;
    }
}

