package lotto.model;


import lotto.Lotto;
import lotto.service.LottoService;

import java.util.*;

public class RankingLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public RankingLotto(List<Integer> winningNumbers, int bonusNumber){
        this.winningLotto = new Lotto(winningNumbers);

        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplication(winningNumbers,bonusNumber);

        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 한다.");
        }
    }

    private void validateBonusNumberDuplication(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없다.");
        }
    }


    public Rank match(Lotto userLotto){
        int matchCount = userLotto.countMatchingNumbers(this.winningLotto);
        boolean matchBonus = userLotto.contains(this.bonusNumber);
        return Rank.checkingRank(matchCount,matchBonus);
    }


}

