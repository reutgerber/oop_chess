import java.util.Comparator;

public class CompareByNumberOfSteps implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.moves.size()>o2.moves.size())
            return 1;
        if (o1.moves.size()==o2.moves.size())
            return 0;
        return -1;
    }
}
