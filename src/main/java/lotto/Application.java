package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {

    }

    public static int getLottoCount(int input) {
        if(input%1000 != 0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입려하여야 합니다.");
        }
        return input/1000;
    }

    public static List<Integer> getRandomLottoNumber() {
        return Randoms.pickUniqueNumbersInRange(1,45,6);
    }

    public static Lotto inputWinningLottoNumbers(){
        String inputValue = Console.readLine();
        List<Integer> winningLottoNumbers = new ArrayList<Integer>();
        String[] values = inputValue.split(",");
        Arrays.sort(values);
        for(String value : values) {
            int lottoNumber = Integer.parseInt(value);
            if(!checkLottoNumberRange(lottoNumber))
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            if(!checkLottoNumberDuplicate(lottoNumber, winningLottoNumbers))
                throw new IllegalArgumentException("[ERROR] 로또 번호는 서로 다른 숫자여야 합니다.");
            winningLottoNumbers.add(lottoNumber);
        }
        return new Lotto(winningLottoNumbers);
    }

    public static boolean checkLottoNumberDuplicate(int lottoNumber, List<Integer> lottoNumbers){
        if(lottoNumbers.contains(lottoNumber)) return false;
        return true;
    }

    public static boolean checkLottoNumberRange(int lottoNumber){
        if(lottoNumber < 1 || lottoNumber > 45) return false;
        return true;
    }

    //랜덤 로또 번호들만 있는 리스트 만들기
    public static List<List<Integer>> getRandomLottos(List<List<Integer>> randomLottos, List<Integer> lottoNumbers){
            randomLottos.add(lottoNumbers);
            for(List<Integer> lottos : randomLottos){
                System.out.println(lottos);
            }
            return randomLottos;
    }




    public static int inputBonusNumber(Lotto winningLottoNumbers){
        String inputValue = Console.readLine();
        int bonusNumber = Integer.parseInt(inputValue);
        if(!checkLottoNumberRange(bonusNumber))
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        if(!checkLottoNumberDuplicate(bonusNumber,winningLottoNumbers.getNumbers()))
            throw new IllegalArgumentException("해당 보너스 번호가 당첨 로또 번호 중에 있습니다. 다른 번호를 입력해주세요");
        return bonusNumber;
    }
}
