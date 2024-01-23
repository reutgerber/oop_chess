public class Pawn extends ConcretePiece{


    public Pawn(Player player, int id){
        super(player,id);
    }



    @Override
    public String getType() {
        return "♟";
    }
}
