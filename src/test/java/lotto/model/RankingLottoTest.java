package lotto.model;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RankingLottoTest {

    @DisplayName("당첨 번호가 6개가 아니면 예외 발생")
    @Test
    void createRankingLottoIsNotSix(){
        assertThatThrownBy(() -> new RankingLotto(List.of(1, 2, 3, 4, 5), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨번호는의 개수는 총 6개여야 한다.");
    }

    @DisplayName("생성 시 보너스 번호가 1~45범위를 벗어나면 예외발생")
    @Test
    void createRankingLottoWithBounusRangeOutOfRange(){
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        assertThatThrownBy(() -> new RankingLotto(numbers, 48))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 한다.");
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복 시 예외발생")
    @Test
    void createRankingLottoWithDuplicationBonusNumber(){
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        assertThatThrownBy(()->new RankingLotto(numbers,6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복 될 수 없다.");
    }

    @DisplayName("구매한 로또와 비교하여 등수 반환")
    @ParameterizedTest
    @MethodSource("provideLottoAndExpectRank")
    void matchLottoToRank(Lotto userLotto, Rank expectedRank){
        List<Integer> winNumber = List.of(1,2,3,4,5,6);
        int bonusNumber = 7;
        RankingLotto rankingLotto = new RankingLotto(winNumber, bonusNumber);

        Rank result = rankingLotto.match(userLotto);
        assertThat(result).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideLottoAndExpectedRank(){
        return Stream.of(
                Arguments.of(new Lotto(List.of(1,2,3,4,5,6)), Rank.FIRST),
                Arguments.of(new Lotto(List.of(1,2,3,4,5,7)), Rank.SECOND),
                Arguments.of(new Lotto(List.of(1,2,3,4,5,8)), Rank.THIRD),
                Arguments.of(new Lotto(List.of(1,2,3,4,8,9)), Rank.FOURTH),
                Arguments.of(new Lotto(List.of(1,2,3,8,9,10)), Rank.FIFTH),
                Arguments.of(new Lotto(List.of(1,2,8,9,10,11)), Rank.FAIL),
                Arguments.of(new Lotto(List.of(1,8,9,10,11,12)), Rank.FAIL),
                Arguments.of(new Lotto(List.of(8,9,10,11,12,13)), Rank.FAIL)

        );
    }
}
