package lotto.util;

import lotto.Lotto;

public class Validator {

    private static final int LOTTO_PRICE = 1000;

    public static void validatePurchaseMoney(String input) {
        validateIsNumber(input);

        int money = Integer.parseInt(input);
        validateIsPositive(money);
        validateIsDivisible(money);
    }

    //숫자 검증
    private static void validateIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 한다.");
        }
    }

    //1000 이상인지 검증
    private static void validateIsPositive(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상이어야 한다.");
        }
    }

    //1000원단위 인지 검증
    private static void validateIsDivisible(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 한다.");
        }
    }

    public static void validateWinningNumbers(String input){
        //TODO : 테스트후 개발
    }


}
