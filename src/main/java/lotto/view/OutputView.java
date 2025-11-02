package lotto.view;

import lotto.Lotto;
import lotto.model.Rank;

import java.util.List;
import java.util.Map;

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

    public void printStatisticsIntro(){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void printStatistics(Map<Rank, Integer> statistics){
        System.out.printf("3개 일치 (5,000원) - %d개%n", statistics.get(Rank.FIFTH));
        System.out.printf("4개 일치 (50,000원) - %d개%n", statistics.get(Rank.FOURTH));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", statistics.get(Rank.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", statistics.get(Rank.SECOND));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", statistics.get(Rank.FIRST));
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}
