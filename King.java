public class King extends ConcretePiece{


    public King(Player player, int id) {
        super(player,id);
    }
    public  void printMoves(String s)
    {
        System.out.print("K"+this.id+":");
        System.out.print(" ["+moves.getFirst());
        for(int i=1;i<moves.size();i++) {
            Position pos=moves.get(i);
            System.out.print(", "+pos);
        }
        System.out.println("]");

    }
    public void printSquares(String s)
    {
        System.out.print("K"+this.id+": ");
        System.out.println(this.getSquares()+" squares");
    }


    @Override
    public String getType() {
        return "♜";
    }
}
