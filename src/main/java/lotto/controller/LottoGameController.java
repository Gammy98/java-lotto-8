package lotto.controller;

import lotto.Lotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoGameController(){
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoService = new LottoService();
    }

    public void run(){
        int purchaseMoney = inputView.readPurchaseMoney();

        List<Lotto> lottos = lottoService.makeLottos(purchaseMoney);
        int lottoCount = lottos.size();

        outputView.printPurchaseCount(lottoCount);
        outputView.printLottos(lottos);

        String winningNumbers = inputView.readWinningNumbers();

        //TODO: 보너스번호 입력
    }
}
