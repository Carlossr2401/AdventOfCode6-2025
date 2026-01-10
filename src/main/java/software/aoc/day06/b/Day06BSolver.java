package software.aoc.day06.b;

import java.io.IOException;
import java.util.List;


import software.aoc.day06.InstructionReader;


import software.aoc.day06.Solver;

public class Day06BSolver implements Solver {
    private final InstructionReader reader;

    public Day06BSolver(InstructionReader reader) {
        this.reader = reader;
    }

    @Override
    public long solve() {
        try {
            List<String> lines = reader.readAllLines();
            Grid grid = new Grid(lines);
            ProblemScanner scanner = new ProblemScanner(grid);
            List<Problem> problems = scanner.scan();

            return problems.stream()
                    .mapToLong(Problem::solve)
                    .sum();
        } catch (IOException e) {
            throw new RuntimeException("Error scanning/reading data", e);
        }
    }
}
