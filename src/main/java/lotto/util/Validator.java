package lotto.util;

import lotto.Lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //당첨번호 검증 메서드
    public static void validateWinningNumbers(String input){

        validateInput(input);
        String[] lottoNumber = input.split(",");
        validateWinningNumberIsSix(lottoNumber);
        List<Integer> numbers = convertValidateNumbers(lottoNumber);
        validateNoDuplicate(numbers);

    }

    private static void validateWinningNumberIsSix(String[] numbers){
        if (numbers.length != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 한다.");
        }
    }

    private static List<Integer> convertValidateNumbers(String[] lottoNumbers){
        List<Integer> numbers = new ArrayList<>();
        for (String nums : lottoNumbers) {
            int number = validateLottoIsNotNumber(nums);
            validateNumberRange(number);
            numbers.add(number);
        }
        return numbers;
    }

    private static int validateLottoIsNotNumber(String numbers) {
        try {
            return Integer.parseInt(numbers.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 한다.");
        }
    }

    private static void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 당첨번호는 1부터 45사이의 숫자여야 한다.");
        }
    }

    private static void validateNoDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복이 있으면 안됩니다.");
        }
    }

    private static void validateInput(String input) {

        if (input.contains(" ")){
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분해야 한다.");
        }

        if(!input.contains(",")){
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉽표로 구분해야 한다.");
        }

        if (input.startsWith(",") || input.endsWith(",")){
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 시작하거나 끝날 수 없다.");
        }
    }

    public static void validateBonusNumber(String input, List<Integer> winningNumbers){
        int bonusNumber = validateBonusIsNumber(input);
        validateBonusOutOfRange(bonusNumber);
        validateBonusDuplication(bonusNumber,winningNumbers
        );
    }

    private static int validateBonusIsNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 한다.");
        }
    }

    private static void validateBonusOutOfRange(int number){
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 한다.");
        }
    }

    private static void validateBonusDuplication(int bonusNumber, List<Integer> winningNumbers){
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없다.");
        }
    }



}
