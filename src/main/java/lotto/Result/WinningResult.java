package lotto.Result;

public enum WinningResult {
    FIRST_WIN(6,false,2000000000),
    SECOND_WIN(5,true,15000000),
    THIRD_WIN(5,false,1500000),
    FOURTH_WIN(4,false,50000),
    FIFTH_WIN(3,false,5000),
    NO_WIN(0,false,0);

    private final int numberCount;
    private final boolean bonusCheck;
    private final int winningPrice;

    WinningResult(int numberCount, boolean bonusCheck, int winningPrice){
        this.numberCount=numberCount;
        this.bonusCheck=bonusCheck;
        this.winningPrice=winningPrice;
    }

    public int getNumberCount() {
        return numberCount;
    }

    public boolean getBonusCheck() {
        return bonusCheck;
    }

    public int getWinningPrice() {
        return winningPrice;
    }
}
