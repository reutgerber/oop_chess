import java.util.Comparator;

public class CompareBySquares implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.getSquares()>o2.getSquares())
            return -1;
        if (o1.getSquares()==o2.getSquares())
            return 0;
        return 1;
    }
}
