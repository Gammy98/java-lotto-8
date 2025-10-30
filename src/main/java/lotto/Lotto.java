package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 한다.");
        }

        Set<Integer> checkDuplicationNums = new HashSet<>(numbers);
        if (checkDuplicationNums.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없다.");
        }

        for (Integer num : numbers){
            if (num < 1 || num > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    public List<Integer> getSortedNumbers(){
        return numbers.stream()
                .sorted()
                .toList();
    }
}
