import java.util.Comparator;

public class CompareByWinner implements Comparator<ConcretePiece> {
    boolean whiteWin=false;
    public CompareByWinner(boolean whiteWin)
    {
        this.whiteWin=whiteWin;
    }
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (this.whiteWin == true) {
            if (o1.player.isPlayerOne() == true && o2.player.isPlayerOne() == false)
                return 1;
            if (o1.player.isPlayerOne() == false && o2.player.isPlayerOne() == true)
                return -1;
            return 0;
        } else
        {
            if (o1.player.isPlayerOne() == true && o2.player.isPlayerOne() == false)
                return -1;
            if (o1.player.isPlayerOne() == false && o2.player.isPlayerOne() == true)
                return 1;
            return 0;
        }

    }



}