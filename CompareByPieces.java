import java.util.Comparator;

public class CompareByPieces implements Comparator<Position> {
    @Override
    public int compare(Position p1, Position p2) {
        if (p1.pieces.size()>p2.pieces.size())
            return -1;
        if (p1.pieces.size()==p2.pieces.size())
            return 0;
        return +1;
    }
}



