
import java.util.Comparator;

public class CompareByY implements Comparator<Position> {
    @Override
    public int compare(Position p1, Position p2) {
        if (p1.row>p2.row)
            return 1;
        if (p1.row==p2.row)
            return 0;
        return -1;
    }
}

