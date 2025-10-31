package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.Validator;

public class InputView {

    public int readPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        while (true) {
            try {
                String input = Console.readLine();
                Validator.validatePurchaseMoney(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readWinningNumbers(){
            System.out.println();
            System.out.println("당첨 번호를 입력해 주세요.");
            while (true){
                try {
                    String input = Console.readLine();
                    Validator.validateWinningNumbers(input);
                    return input;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
    }


    // TODO : 보너스 번호 입력
}
