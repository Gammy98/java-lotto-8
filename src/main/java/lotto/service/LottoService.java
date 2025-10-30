package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//로또 발급을 당담하는 클래스(controller에서 다하기엔 부담)
public class LottoService {

    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    public List<Lotto> makeLottos(int purchaseMoney){
        int count = calculateLottoCount(purchaseMoney);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(pickLottoNums());
        }
        return lottos;
    }

    public int calculateLottoCount(int purchaseMoney) {
        return purchaseMoney / LOTTO_PRICE;
    }

    private Lotto pickLottoNums() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                MIN_NUMBER,
                MAX_NUMBER,
                LOTTO_SIZE
        );
        return new Lotto(numbers);
    }
}
