package software.aoc.day06.b;

import java.util.List;

public record Problem(List<Long> numbers, Operator operator) {
    public long solve() {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        return numbers.stream()
                .reduce(operator::apply)
                .orElse(0L);
    }
}
