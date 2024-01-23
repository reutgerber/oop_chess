import java.util.ArrayList;

public class Position {
    int col, row;
    ArrayList<Piece> pieces = new ArrayList<>();

    @Override
    public String toString() {
        return "(" + col + ", " + row + ")";
    }

    Piece piece=null;

    boolean isInPieces(Piece piece)
    {
        boolean found=false;
        for(Piece p : pieces) {
            if ( ((ConcretePiece)piece).id == ((ConcretePiece)p).id && piece.getOwner()==p.getOwner())
            {
                found=true;
                break;
            }
        }
        return found;
    }

    Piece getPiece()
    {
        return piece;
    }
    public void setPiece(Piece piece)
    {

        this.piece=piece;
        if (!isInPieces(piece))
            pieces.add(piece);

    }
    public void setPiece(Piece piece,int x,int y)
    {
        this.piece=piece;
        ((ConcretePiece)this.piece).addPosition(new Position(x,y));
        if (!isInPieces(piece))
            pieces.add(piece);

    }

    public void Print()
    {
        if (pieces.size()>1) {
            System.out.println("(" + col + ", " + row + ")" + pieces.size()+" pieces");
        }
    }

    public void removePiece(){
        this.piece=null;
    }
    public Position(int col,int row)
    {
        this.col =col;
        this.row = row;
    }
}
