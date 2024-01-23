public class ConcretePlayer implements Player{
    Boolean b=false;
    int countWin=0;


    public ConcretePlayer(Boolean b)
    {
        this.b=b;
    }
    public void win()
    {
        countWin++;
    }

    @Override
    public boolean isPlayerOne() {
        return b;
    }

    @Override
    public int getWins() {

        return countWin;
    }
}
