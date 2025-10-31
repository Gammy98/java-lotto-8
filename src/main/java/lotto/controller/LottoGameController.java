package lotto.controller;

import lotto.Lotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoGameController(){
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoService = new LottoService();
    }

    public void run() {
        int purchaseMoney = inputView.readPurchaseMoney();
        List<Lotto> lottos = lottoService.makeLottos(purchaseMoney);
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);

        String winningNumbersInput = inputView.readWinningNumbers();

        List<Integer> winningNumbers = WinningNumbersToList(winningNumbersInput);
        int bonusNumber = inputView.readBonusNumber(winningNumbers);


        //TODO: 통계계산

    }

    private List<Integer> WinningNumbersToList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
