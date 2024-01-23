import java.util.ArrayList;

public abstract class ConcretePiece implements Piece{
    int id;
    Player player;
    int numOfEat = 0;
    ArrayList<Position> moves = new ArrayList<>();
    public ConcretePiece(Player player, int id){
        this.id=id;
        this.player=player;
    }
    public void incEat()
    {
        numOfEat++;
    }

    public void printNumOfEat(String s)
    {
        if (numOfEat==0)
            return;
        System.out.print(s+this.id+": ");
        System.out.println(numOfEat+" kills");
    }

    public void printSquares(String s)
    {
        System.out.print(s+this.id+": ");
        System.out.println(this.getSquares()+" squares");
    }

    public int getSquares()
    {
        int sum=0;
        if(moves.size()<2)
            return 0;
        Position pos=moves.getFirst();
        for(int i=1; i<moves.size();i++)
        {
            Position pos2=moves.get(i);
            int dist=Math.abs(pos2.row-pos.row)+Math.abs(pos2.col-pos.col);
            sum+=dist;
            pos=pos2;


        }
        return sum;
     }


    public  void printMoves(String s)
    {
        if (moves.size()==1)
            return;
        System.out.print(s+this.id+":");
        System.out.print(" ["+moves.getFirst());
        for(int i=1;i<moves.size();i++) {
            Position pos=moves.get(i);
            System.out.print(", "+pos);
        }
        System.out.println("]");

    }

    public void addPosition(Position pos)
    {
        moves.add(pos);
    }
    @Override
    public Player getOwner() {
        return player;
    }
    public int getId(){
        return id;
    }

    public int getNumOfEat(){
        return numOfEat;
    }

    @Override
    public String getType() {
        return null;
    }
}
