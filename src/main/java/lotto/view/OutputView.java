package lotto.view;

import lotto.Lotto;

import java.util.List;

public class OutputView {

    public void printPurchaseCount(int count){
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getSortedNumbers());
        }
    }
}
