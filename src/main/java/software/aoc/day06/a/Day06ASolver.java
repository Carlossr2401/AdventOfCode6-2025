package software.aoc.day06.a;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import software.aoc.day06.InstructionReader;
import software.aoc.day06.Solver;

public class Day06ASolver implements Solver {
    private final InstructionReader reader;

    public Day06ASolver(InstructionReader reader) {
        this.reader = reader;
    }

    @Override
    public long solve() {
        try {
            List<String> lines = reader.readAllLines();
            if (lines.size() < 5) {
                throw new IllegalStateException("Input file must have at least 5 lines.");
            }

            // Parse first 4 lines as Integers
            List<Integer> line1 = parseIntegers(lines.get(0));
            List<Integer> line2 = parseIntegers(lines.get(1));
            List<Integer> line3 = parseIntegers(lines.get(2));
            List<Integer> line4 = parseIntegers(lines.get(3));
            
            // Parse 5th line as Strings (operators)
            List<String> operators = Arrays.stream(lines.get(4).trim().split("\\s+"))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            long finalResult = 0;
            // Assuming all integer lines have same size, using line1 size as reference
            int size = line1.size();

            for (int i = 0; i < size; i++) {
                int val1 = line1.get(i);
                int val2 = line2.get(i);
                int val3 = line3.get(i);
                int val4 = line4.get(i);
                String op = operators.get(i % operators.size());

                switch (op) {
                    case "+":
                        finalResult += val1 + val2 + val3 + val4;
                        break;
                    case "*":
                        finalResult += (long) val1 * val2 * val3 * val4;
                        break;
                    default:
                        // Maybe throw exception or ignore unknown op?
                        System.err.println("Unknown operator: " + op);
                        break;
                }
            }

            return finalResult;

        } catch (IOException e) {
            throw new RuntimeException("Error reading input data", e);
        }
    }

    private List<Integer> parseIntegers(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
