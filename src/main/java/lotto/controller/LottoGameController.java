package lotto.controller;

import lotto.Lotto;
import lotto.model.Rank;
import lotto.model.RankingLotto;
import lotto.service.LottoService;
import lotto.service.StatisticsService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final StatisticsService statisticsService;

    public LottoGameController(){
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoService = new LottoService();
        this.statisticsService = new StatisticsService();
    }

    public void run() {

        //구입 금액 입력
        int purchaseMoney = inputView.readPurchaseMoney();

        //로또발급
        List<Lotto> lottos = lottoService.makeLottos(purchaseMoney);
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);

        //당첨 번호 입력
        String winningNumbersInput = inputView.readWinningNumbers();
        List<Integer> winningNumbers = WinningNumbersToList(winningNumbersInput);

        //보너스 번호 입력
        int bonusNumber = inputView.readBonusNumber(winningNumbers);


        //당첨 로또 객체 생성/비교 후 통계 계산
        RankingLotto rankingLotto = new RankingLotto(winningNumbers, bonusNumber);
        Map<Rank, Integer> statistics = statisticsService.calculateStatistics(lottos, rankingLotto);
        double profitRate = statisticsService.calculateProfitRate(statistics, purchaseMoney);

        //최종결과 출력
        outputView.printStatisticsIntro();
        outputView.printStatistics(statistics);
        outputView.printProfitRate(profitRate);
    }

    private List<Integer> WinningNumbersToList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
