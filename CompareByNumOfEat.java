import java.util.Comparator;

public class CompareByNumOfEat implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.numOfEat>o2.numOfEat)
            return -1;
        if (o1.numOfEat==o2.numOfEat)
            return 0;
        return 1;
    }
}