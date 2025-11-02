package lotto.model;

import java.util.Arrays;

public enum Rank {

    FIRST(6,2000000000L),
    SECOND(5,30000000L),
    THIRD(5,1500000L),
    FOURTH(4,50000L),
    FIFTH(3,5000L),
    FAIL(0,0L);

    //enum의 각 상수가 내부에 저장할 멤버변수
    private final int matchCount;
    private final long prizeMoney;

    //생성자
    Rank(int matchCount, long prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public int getMatchCount(){
        return matchCount;
    }

    public long getPrizeMoney(){
        return prizeMoney;
    }


    public static Rank checkingRank(int matchCount, boolean matchBonus) {
        if (matchCount == THIRD.matchCount) {
            return matchBonus ? SECOND: THIRD;
        }

        if (matchCount == FIRST.matchCount) {
            return FIRST;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.getMatchCount() == matchCount)
                .filter(rank -> rank != SECOND && rank != THIRD)
                .findFirst()
                .orElse(FAIL);
    }

}
