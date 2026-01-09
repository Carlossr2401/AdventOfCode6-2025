package software.aoc.day06.b;

import java.util.function.LongBinaryOperator;

public enum Operator {
    PLUS("+", Long::sum),
    MULTIPLY("*", (a, b) -> a * b);

    private final String symbol;
    private final LongBinaryOperator operation;

    Operator(String symbol, LongBinaryOperator operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public static Operator fromSymbol(String symbol) {
        for (Operator op : values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operator symbol: " + symbol);
    }

    public long apply(long a, long b) {
        return operation.applyAsLong(a, b);
    }
}
