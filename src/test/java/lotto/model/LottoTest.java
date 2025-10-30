package lotto.model;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @DisplayName("로또의 번호의 개수가 6개가 아니면 예외 발생")
    @Test
    void createLottoSixNum(){
        assertThatThrownBy(()-> new Lotto(List.of(1,2,3,4,5,6,7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 6개여야 한다.");
    }

    @DisplayName("로또 번호에 중복된 숫자 있으면 예외 발생")
    @Test
    void createLottoDuplicate(){
        assertThatThrownBy(()-> new Lotto(List.of(1,2,3,4,4,4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 중복될 수 없다.");
    }

    @DisplayName("로또 번호에 1~45 이외의 숫자가 있으면 예외 발생")
    @Test
    void createLottoOverRange(){
        assertThatThrownBy(()-> new Lotto(List.of(1,2,33,47,4,5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

}
