package lotto.service;

import lotto.Lotto;
import lotto.model.Rank;
import lotto.model.RankingLotto;
import lotto.view.InputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {

    public Map<Rank, Integer> calculateStatistics(List<Lotto> userLottos, RankingLotto rankingLotto) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);

        statistics.put(Rank.FIFTH, 0);
        statistics.put(Rank.FOURTH, 0);
        statistics.put(Rank.THIRD, 0);
        statistics.put(Rank.SECOND, 0);
        statistics.put(Rank.FIRST, 0);

        for (Lotto userLotto : userLottos) {
            Rank rank = rankingLotto.match(userLotto);

            if (rank != Rank.FAIL) {
                statistics.put(rank,statistics.get(rank) + 1);
            }
        }
        return statistics;
    }

    public double calculateProfitRate(Map<Rank, Integer> statistics, int purchaseMoney) {
        long totalPrize = 0L;
        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize += rank.getPrizeMoney() * count;
        }

        double rate = ((double) totalPrize/purchaseMoney) * 100.0;
        return Math.round(rate*10.0)/10.0;
    }
}
