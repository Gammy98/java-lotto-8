package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidatorTest {

    @DisplayName("올바른 구입 금액일 경우 예외발생하지 않는다")
    @Test
    void validatePurchaseMoneySuccess(){
        assertThatCode(() -> Validator.validatePurchaseMoney("2000"))
                .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외발생")
    @Test
    void validatePurchaseMoneyIsNotNumber(){
        assertThatThrownBy(() -> Validator.validatePurchaseMoney("10a0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 숫자여야 한다.");
    }

    @DisplayName("구입 금액이 1000미만이면 예외발생")
    @Test
    void validatePurchaseMoneyUnder1000(){
        assertThatThrownBy(() -> Validator.validatePurchaseMoney("500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1000원 이상이어야 한다.");
    }

    @DisplayName("구입 금액이 1000단위가 아니면 예외발생")
    @Test
    void validatePurchaseMoneyNotDivideBy1000(){
        assertThatThrownBy(() -> Validator.validatePurchaseMoney("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1000원 단위여야 한다.");
    }


}
