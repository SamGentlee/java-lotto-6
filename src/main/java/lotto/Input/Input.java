package lotto.Input;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto.Lotto;
import lotto.Print.ErrorMessage;
import lotto.Print.Print;
import lotto.Validator.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Input {
    //입력받은 구입 금액으로 로또 개수 구하기
    public static int getLottoBuyCount() {
        Print.printBuyLotto();
        String inputValue = Console.readLine();
        int buyPrice = 0;
        try{
            buyPrice = Validator.checkLottoBuyPrice(inputValue);
        }catch(IllegalArgumentException e){
            System.out.println(ErrorMessage.INVALID_NUMBER_FORMAT);
        }
        return buyPrice / 1000;
    }

    public static Lotto getWinningLottoNumbers(){
        Print.printWinningLottoNumber();
        String inputValue = Console.readLine();
        List<Integer> winningLottoNumbers = new ArrayList<Integer>();
        String[] values = inputValue.split(",");
        for(String value : values) {
            try{
                winningLottoNumbers.add(Integer.parseInt(value));
            }catch(NumberFormatException exception){
                System.out.println(ErrorMessage.INVALID_NUMBER_FORMAT);
                throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT);
            }
        }
        //Collections.sort(winningLottoNumbers);
        return new Lotto(winningLottoNumbers);
    }

    public static int getBonusNumber(){
        Print.printBonusNumber();
        String inputValue = Console.readLine();
        try{
            return Integer.parseInt(inputValue);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }
    }

}
