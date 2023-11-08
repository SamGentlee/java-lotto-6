package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto.Lotto;
import lotto.Lotto.WinningLotto;
import lotto.Print.ErrorMessage;
import lotto.Print.Message;
import lotto.Result.WinningResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){
        int lottoBuyCount = getLottoBuyCount();
        printPaidLottoResult(lottoBuyCount);
        WinningLotto winningLotto = getWinningLotto();

    }

    //로또 구입 메세지 출력
    public static void printBuyLotto() {
        System.out.println(Message.INPUT_LOTTO_PRICE_MESSAGE);
    }

    //입력받은 구입 금액으로 로또 개수 구하기
    public static int getLottoBuyCount() {
        printBuyLotto();
        String inputValue = Console.readLine();
        int buyPrice = checkLottoBuyPrice(inputValue);
        return buyPrice / 1000;
    }

    //입력받은 구입 금액 유효성 검사
    public static int checkLottoBuyPrice(String inputValue) {
        try{
            int buyPrice = Integer.parseInt(inputValue);
            if(buyPrice % 1000 != 0 || buyPrice == 0){
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PRICE_ERROR);
            }
            return buyPrice;
        }catch(NumberFormatException exception){
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PRICE_ERROR);
        }
    }
    //구입한 로또 개수와 번호 출력
    public static void printPaidLottoResult(int lottoBuyCount){
        System.out.printf("\n"+Message.INFORM_LOTTO_COUNT_MESSAGE+"\n",lottoBuyCount);
        List<Lotto> paidLottoNumbers = getPaidLottoNumbers(lottoBuyCount);
        for(Lotto paidLotto : paidLottoNumbers){
            System.out.println(paidLotto.printNumbers());
        }

    }

    //구입한 로또 리스트 구하기
    public static List<Lotto> getPaidLottoNumbers(int lottoBuyCount){
        List<Lotto> paidLottoNumbers = new ArrayList<>();
        for(int i=0;i<lottoBuyCount;i++){
            paidLottoNumbers.add(getRandomLottoNumber());
        }
        return paidLottoNumbers;
    }


    //랜덤 로또 번호 구하기
    public static Lotto getRandomLottoNumber() {
         return new Lotto(Randoms.pickUniqueNumbersInRange(1,45,6));
    }

    public static void printWinningLottoNumber(){
        System.out.println("\n"+Message.INPUT_LOTTO_NUMBERS_MESSAGE);
    }

    public static Lotto getWinningLottoNumbers(){
        printWinningLottoNumber();
        String inputValue = Console.readLine();
        List<Integer> winningLottoNumbers = new ArrayList<Integer>();
        String[] values = inputValue.split(",");
        for(String value : values) {
            try{
                winningLottoNumbers.add(Integer.parseInt(value));
            }catch(NumberFormatException exception){
                throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT);
            }
        }
        return new Lotto(winningLottoNumbers);
    }

    public static void printBonusNumber(){
        System.out.println("\n"+Message.INPUT_BOUNUS_NUMBER_MESSAGE);
    }

    public static int getBonusNumber(){
        printBonusNumber();
        String inputValue = Console.readLine();
        try{
            return Integer.parseInt(inputValue);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }
    }

    public static WinningLotto getWinningLotto(){
        Lotto winnigNumbers = getWinningLottoNumbers();
        int bonusNumber = getBonusNumber();
        return new WinningLotto(winnigNumbers,bonusNumber);
    }

    public static WinningResult checkWinngResult(WinningLotto winningLotto, Lotto paidLotto){
        int numberCount = getCorrectNumberCount(winningLotto.getLotto(),paidLotto);
        boolean bonusCheck = getContainsCount(winningLotto.getBonusNumber(),paidLotto);
        WinningResult result = null;
        return result;
    }

    public static boolean getContainsCount(int number,Lotto lotto){
        if(lotto.getNumbers().contains(number)){
            return true;
        }
        return false;
    }

    private static int getCorrectNumberCount(Lotto winningLotto, Lotto paidLotto) {
        int count = 0;
        for(int number : winningLotto.getNumbers()){
            if(getContainsCount(number,paidLotto)) count++;
        }
        return count;
    }


}
