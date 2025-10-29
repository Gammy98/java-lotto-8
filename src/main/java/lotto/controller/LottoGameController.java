package lotto.controller;

import lotto.view.InputView;

public class LottoGameController {

    private final InputView inputView;

    public LottoGameController(){
        this.inputView = new InputView();
    }

    public void run(){
        int purchaseMoney = inputView.readPurchaseMoney();
    }
}
