import java.util.ArrayList;
import java.util.Objects;

public class GameLogic implements PlayableLogic{
    ConcretePlayer blackPlayer,whitePlayer;

    PrintPartTwo printPartTwo=null;
    ArrayList<ConcretePiece> whiteTools=new ArrayList<ConcretePiece>();
    ArrayList<ConcretePiece> blackTools=new ArrayList<ConcretePiece>();
    ArrayList<ConcretePiece> allTools=new ArrayList<ConcretePiece>();

    ConcretePiece king;
    boolean firstPlayerTurn = false;


    boolean gameFinished=false;

    Position[][] positions=new Position[11][11];

    public Position findPosition(Position pos)
    {
        int x=pos.col;
        int y=pos.row;
        if (x>getBoardSize() || x<0)
            return null;
        if (y>getBoardSize() || y<0)
            return null;
        return positions[x][y];
    }
    public Position findPosition(int col, int row)
    {
        int x=col;
        int y=row;
        if (x>=getBoardSize() || x<0)
            return null;
        if (y>=getBoardSize() || y<0)
            return null;
        return positions[x][y];
    }
   public GameLogic()
   {
       blackPlayer=new ConcretePlayer(false);
       whitePlayer=new ConcretePlayer(true);

       reset();
      printPartTwo=new PrintPartTwo(whiteTools,blackTools,allTools,positions);


   }
   private void IsKingInCorner( Position b)
   {
       Position pos= findPosition(b);
       Piece piece=pos.getPiece();
       if (piece==null)
           return;
       if (piece.getType()=="♜")
       {
           if ((pos.col==0 && pos.row==0) || (pos.col==10 && pos.row==0) || (pos.col==0 && pos.row==10) || (pos.col==10 && pos.row==10)){
               gameFinished=true;
               blackPlayer.win();
               printPartTwo.printAll(true);





           }
       }
   }
    @Override
    public boolean move(Position a, Position b) {
       //

        if(isEmpty(b)&&isStright(a,b)&&noSkipping(a,b)&&isPlayerTurn(a)) {

            Position pos1 = findPosition(a);
            Position pos2 = findPosition(b);
            Piece tempPiece = pos1.getPiece();
            pos2.setPiece(tempPiece);
            pos1.removePiece();
            firstPlayerTurn=!firstPlayerTurn;
            b.setPiece(tempPiece);
            eat(b);
            ConcretePiece cp=(ConcretePiece)(b.getPiece());
            cp.addPosition(b);
            IsKingInCorner(b);


            return true;
        }
        return false;

    }

    private void eatKing(Position kingPosition, Player pos2Player,Position b)
    {
        Position left= findPosition(kingPosition.col-1,kingPosition.row);
        Position right= findPosition(kingPosition.col+1,kingPosition.row);
        Position up= findPosition(kingPosition.col,kingPosition.row-1);
        Position down= findPosition(kingPosition.col,kingPosition.row+1);
        boolean b1= (left==null) ||  ((left.getPiece()!=null) &&  !left.getPiece().getOwner().isPlayerOne());
        boolean b2= (right==null) ||  ((right.getPiece()!=null) &&  !right.getPiece().getOwner().isPlayerOne());
        boolean b3= (up==null) ||  ((up.getPiece()!=null) &&  !up.getPiece().getOwner().isPlayerOne());
        boolean b4= (down==null) ||  ((down.getPiece()!=null) &&  !down.getPiece().getOwner().isPlayerOne());
        if(b1&&b2&&b3&&b4)
        {
           // ((ConcretePlayer)kingPosition.getPiece().getOwner()).win();
            whitePlayer.win();
            ConcretePiece cp=(ConcretePiece)b.getPiece();
            cp.moves.add(b);
//            cp.incEat();
            printPartTwo.printAll(false);


            gameFinished=true;
            reset();

        }
    }


    private void checkPos2( Player pos2Player,Position nextPos ,Position nextNextPos,Position b)
    {
        if (nextPos==null)
            return;
        if (nextNextPos==null)
        {
            Piece nextPosPiece = nextPos.getPiece();
            if (nextPosPiece==null)
                return;
            if (pos2Player!=nextPosPiece.getOwner())
            {
                if (nextPosPiece.getType()=="♜")
                    eatKing(nextPos,pos2Player,b);
                else
                {
                    nextPos.removePiece();
                    ConcretePiece cp=(ConcretePiece)(b.getPiece());
                    cp.incEat();

                }

            }
            return;
        }
        Piece nextPosPiece = nextPos.getPiece();
        if (nextPosPiece!= null) {
            Player nextPosPlayer = nextPosPiece.getOwner();
            Piece nextNextPosPiece = nextNextPos.getPiece();
            if (nextNextPosPiece!=null){
                Player nextNextPosPlayer = nextNextPosPiece.getOwner();
                if (pos2Player!=nextPosPlayer && nextPosPlayer!=nextNextPosPlayer)
                {
                    if (nextPosPiece.getType()=="♜")
                        eatKing(nextPos, pos2Player,b);
                    else {
                        nextPos.removePiece();
                        ConcretePiece cp = (ConcretePiece)(b.getPiece());
                        cp.incEat();
                    }
                }
            }
        }

    }

    private void eat(Position b){
        if (b.getPiece()!= null && b.getPiece().getType()=="♜")
            return;
        Position pos2 = findPosition(b);
        Piece pos2Piece = pos2.getPiece();
        Player pos2Player = pos2Piece.getOwner();
        //check right
        Position rightPos = findPosition(pos2.col+1, pos2.row);
        Position rightRightPos = findPosition(pos2.col+2, pos2.row);
        checkPos2(pos2Player,rightPos,rightRightPos,b);
        //check left
        Position leftPos = findPosition(pos2.col-1, pos2.row);
        Position leftLeftPos = findPosition(pos2.col-2, pos2.row);
        checkPos2(pos2Player,leftPos,leftLeftPos,b);
        //check up
        Position upPos = findPosition(pos2.col, pos2.row-1);
        Position upUpPos = findPosition(pos2.col, pos2.row-2);
        checkPos2(pos2Player,upPos,upUpPos,b);
        //check down
        Position downPos = findPosition(pos2.col, pos2.row+1);
        Position downDownPos = findPosition(pos2.col, pos2.row+2);
        checkPos2(pos2Player,downPos,downDownPos,b);
    }
    private boolean isPlayerTurn (Position a){
        Position pos1 = findPosition(a);
        Piece piece1=pos1.getPiece();
        Player player=piece1.getOwner();
        return (player.isPlayerOne() && !isSecondPlayerTurn()) || (!player.isPlayerOne() && isSecondPlayerTurn());
    }
    private boolean isEmpty(Position b){
        Position pos2= findPosition(b);
        if(pos2.getPiece()==null)
            return true;
        return false;
    }
    private boolean isStright(Position a, Position b){
        Position pos1= findPosition(a);
        Position pos2= findPosition(b);
        if(pos1.col ==pos2.col || pos1.row ==pos2.row)
            return true;
        return false;
    }

    private boolean noSkipping (Position a, Position b)
    {
        Position pos1= findPosition(a);
        Position pos2= findPosition(b);
        if(pos1.col ==pos2.col)
        {
            if(pos1.row >pos2.row)
            {
                //up
                int diff_row = Math.abs(pos1.row - pos2.row);
                for (int i = 0; i < diff_row; i++) {

                    Position pos3 = positions[pos1.col ][pos1.row- (i + 1)];
                    if (pos3.getPiece() != null) {
                        return false;
                    }

                }
            }
            else {
                //down
                int diff_row = Math.abs(pos2.row - pos1.row);
                for (int i = 0; i < diff_row; i++) {

                    Position pos3 = positions[pos1.col][pos1.row + (i + 1)];
                    if (pos3.getPiece() != null) {
                        return false;
                    }

                }
            }

        }

        if(pos1.row ==pos2.row)
        {
            if(pos1.col>pos2.col)
            {
                //left
                int dif_col= Math.abs(pos1.col -pos2.col);
                for(int i=0; i<dif_col;i++)
                {
                    Position pos4= positions[pos1.col -(i+1)][pos1.row];
                    if(pos4.getPiece()!=null)
                        return false;
                }

            }
            else {
                //right
                int dif_col = Math.abs(pos1.col - pos2.col);
                for (int i = 0; i < dif_col; i++) {
                    Position pos4 = positions[pos1.col + (i + 1)][pos1.row];
                    if (pos4.getPiece() != null)
                        return false;
                }
            }
        }
        return true;
    }

    public void print() {
//        for (int i = 0; i < 11; i++) {
//            for (int j= 0; j< 11; j++){
//                if(positions[j][i].getPiece()==null) {
//                    System.out.print(" *");
//                }
//                else{
//                    ConcretePiece cp=(ConcretePiece)(positions[j][i].getPiece());
//                    System.out.print(" "+cp.getId());
//                }
//            }
//            System.out.println();
//
//
//        }
    }


    @Override
    public Piece getPieceAtPosition(Position position) {
        Position myPosition= findPosition(position);
       return  myPosition.getPiece();
    }

    @Override
    public Player getFirstPlayer() {
        return blackPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return whitePlayer;
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !firstPlayerTurn;
    }

    @Override
    public void reset() {

        king=new King(whitePlayer,7);
        whiteTools=new ArrayList<>();
        blackTools=new ArrayList<>();
        allTools=new ArrayList<>();
        positions=new Position[11][11];
        for(int i=0;i<12;i++) {
            if (i<=5) {
                Pawn wp=new Pawn(whitePlayer, i + 1);
                whiteTools.add(wp);
                allTools.add(wp);
            }
            else {
                Pawn wp2=new Pawn(whitePlayer, i + 2);
                whiteTools.add(wp2);
                allTools.add(wp2);
            }
        }
        whiteTools.add(king);
//        blackTools.add(king);
         allTools.add(king);

        for(int i=0;i<24;i++) {

            Pawn wp3=new Pawn(blackPlayer, i + 1);
            blackTools.add(wp3);
            allTools.add(wp3);
        }
        int counter=0;
        for(int i=0;i<11;i++)
            for (int j=0;j<11;j++)
            {
                positions[i][j]=new Position(i,j);
            }
        for(int i=0;i<5;i++) {
            positions[i + 3][0].setPiece(blackTools.get(i),i+3,0);
        }
        positions[5][1].setPiece(blackTools.get(5),5,1);
        positions[0][3].setPiece(blackTools.get(6),0,3);
        positions[10][3].setPiece(blackTools.get(7),10,3);
        positions[0][4].setPiece(blackTools.get(8),0,4);
        positions[10][4].setPiece(blackTools.get(9),10,4);
        positions[0][5].setPiece(blackTools.get(10),0,5);
        positions[1][5].setPiece(blackTools.get(11),1,5);
        positions[9][5].setPiece(blackTools.get(12),9,5);
        positions[10][5].setPiece(blackTools.get(13),10,5);
        positions[0][6].setPiece(blackTools.get(14),0,6);
        positions[10][6].setPiece(blackTools.get(15),10,6);
        positions[0][7].setPiece(blackTools.get(16),0,7);
        positions[10][7].setPiece(blackTools.get(17),10,7);
        positions[5][9].setPiece(blackTools.get(18),5,9);
        for(int i=0;i<5;i++) {
            positions[i + 3][10].setPiece(blackTools.get(i+19),i+3,10);
        }
        positions[5][3].setPiece(whiteTools.get(0),5,3);
        for(int i=1;i<4;i++) {
            positions[i + 3][4].setPiece(whiteTools.get(i),i+3,4);
        }
        positions[3][5].setPiece(whiteTools.get(4),3,5);
        positions[4][5].setPiece(whiteTools.get(5),4,5);
        positions[6][5].setPiece(whiteTools.get(6),6,5);
        positions[7][5].setPiece(whiteTools.get(7),7,5);
        positions[5][5].setPiece(king,5,5);
        positions[4][6].setPiece(whiteTools.get(8),4,6);
        positions[5][6].setPiece(whiteTools.get(9),5,6);
        positions[6][6].setPiece(whiteTools.get(10),6,6);
        positions[5][7].setPiece(whiteTools.get(11),5,7);

        firstPlayerTurn = false;
        gameFinished=false;
        print();
    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return 11;
    }
}
