import java.util.Comparator;

public class CompareById implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.id>o2.id)
            return 1;
        if (o1.id==o2.id)
            return 0;
        return -1;
    }
}
