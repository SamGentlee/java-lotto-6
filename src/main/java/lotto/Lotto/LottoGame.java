package lotto.Lotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Input.Input;
import lotto.Print.Print;
import lotto.Result.Result;
import lotto.Result.WinningResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoGame {
    public static void run(){
//        int lottoBuyCount = 8;
        int lottoBuyCount = Input.getLottoBuyCount();
        List<Lotto> paidLottos = Print.printPaidLottoResult(lottoBuyCount);
//        List<Lotto> paidLottos = new ArrayList<>();
//        paidLottos.add(new Lotto(List.of(8, 21, 23, 41, 42, 43)));
//        paidLottos.add(new Lotto(List.of(3, 5, 11, 16, 32, 38)));
//        paidLottos.add(new Lotto(List.of(7, 11, 16, 35, 36, 44)));
//        paidLottos.add(new Lotto(List.of(1, 8, 11, 31, 41, 42)));
//        paidLottos.add(new Lotto(List.of(13, 14, 16, 38, 42, 45)));
//        paidLottos.add(new Lotto(List.of(7, 11, 30, 40, 42, 43)));
//        paidLottos.add(new Lotto(List.of(2, 13, 22, 32, 38, 45)));
//        paidLottos.add(new Lotto(List.of(1, 3, 5, 14, 22, 45)));

        WinningLotto winningLotto = LottoGame.getWinningLotto();
        List<WinningResult> winningResults = Result.calcWinningLottoResult(winningLotto,paidLottos);
        Map<WinningResult,Integer> winningResultCountMap = Result.getWinningResultCountMap(winningResults);
        Print.printWinningResult(winningResultCountMap,lottoBuyCount);
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

    public static WinningLotto getWinningLotto(){
        Lotto winnigNumbers = Input.getWinningLottoNumbers();
        int bonusNumber = Input.getBonusNumber();
        return new WinningLotto(winnigNumbers,bonusNumber);
    }


}
