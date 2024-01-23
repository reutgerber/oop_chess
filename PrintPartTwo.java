import java.util.ArrayList;


public class PrintPartTwo {
    ArrayList<ConcretePiece> whiteTools=new ArrayList<>();
    ArrayList<ConcretePiece> blackTools=new ArrayList<>();

    ArrayList<ConcretePiece> allTools=new ArrayList<>();
    ArrayList<Position> positions=new ArrayList<>();


    public PrintPartTwo( ArrayList<ConcretePiece> whiteTools , ArrayList<ConcretePiece> blackTools, ArrayList<ConcretePiece> allTools,Position[][] positions)
    {
        this.whiteTools=whiteTools;
        this.blackTools=blackTools;
        this.allTools=allTools;
        for(int i=0;i<11;i++)
            for(int j=0;j<11;j++)
                this.positions.add(positions[i][j]);


    }
    public void printAll(boolean whiteWin)
    {
        printSortByNumberOfSteps(whiteWin);
        printSortByNumOfEat(whiteWin);
        printSortSquares(whiteWin);
        printSortPieces();
    }

    private void printStars()
    {
        for(int i=0;i<75;i++)
            System.out.print("*");
        System.out.println();
    }
    public void printSortByNumberOfSteps(boolean WhiteWins)
    {
        this.whiteTools.sort(new CompareByNumberOfSteps().thenComparing(new CompareById()));
        this.blackTools.sort(new CompareByNumberOfSteps().thenComparing(new CompareById()));
        if (WhiteWins) {
            printWhiteMoves();
            printBlackMoves();
        }
        else {
            printBlackMoves();
            printWhiteMoves();
        }

        printStars();
    }
    public void printWhiteMoves(){
        for( ConcretePiece cp: this.whiteTools)
            cp.printMoves("D");
    }
    public void printBlackMoves(){
        for( ConcretePiece cp: this.blackTools)
            cp.printMoves("A");
    }

//    public void printWhiteEat(){
//        for( ConcretePiece cp: this.whiteTools)
//            cp.printNumOfEat("D");
//    }
//
//    public void printBlackEat(){
//        for( ConcretePiece cp: this.blackTools)
//            cp.printNumOfEat("A");
//    }

    public void printAllToolsEat(){
        for( ConcretePiece cp: this.allTools)
            if (cp.getOwner().isPlayerOne()) {
                cp.printNumOfEat("D");
            }
        else{
                cp.printNumOfEat("A");
            }
    }

    public void printSortByNumOfEat(boolean whiteWins)
    {
        CompareByWinner compareByWinner=new CompareByWinner(whiteWins);
        this.allTools.sort(new CompareByNumOfEat().thenComparing(new CompareById()).thenComparing( compareByWinner));
        this.printAllToolsEat();

        printStars();
    }

    public void printSortSquares(boolean whiteWins)
    {
        CompareByWinner compareByWinner=new CompareByWinner(whiteWins);
        this.allTools.sort(new CompareBySquares().thenComparing(new CompareById()).thenComparing(compareByWinner));
        this.printAllToolsSquares();

        printStars();
    }

    public void printSortPieces()
    {

        this.positions.sort(new CompareByPieces().thenComparing(new CompareByX()).thenComparing(new CompareByY()));
        printPieces();
        printStars();


    }

    public void printPieces()
    {
        for(Position pos: this.positions)
            pos.Print();
    }


    public void printAllToolsSquares(){
        for( ConcretePiece cp: this.allTools)
            if(cp.getSquares()>0) {
                if (cp.getOwner().isPlayerOne()) {
                    cp.printSquares("D");
                } else {
                    cp.printSquares("A");
                }
            }
    }


}
