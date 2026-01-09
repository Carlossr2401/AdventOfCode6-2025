package software.aoc.day06.b;

import java.util.List;

public record Problem(List<Long> numbers, Operator operator) {
    public long solve() {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        // Initial value depends on the operator slightly differently if we treat it as a reduction
        // But for * and +, we can just reduce.
        // However, standard reduction needs an identity or correct handling of first element.
        // Given the problem description: "4 + 431 + 623", it's a simple reduction.
        
        return numbers.stream()
                .reduce(operator::apply)
                .orElse(0L);
    }
}
