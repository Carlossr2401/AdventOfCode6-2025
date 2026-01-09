package software.aoc.day06.b;

import java.io.IOException;
import java.util.List;

public class CephalopodMathSolver implements Solver {
    private final InstructionReader reader;

    public CephalopodMathSolver(InstructionReader reader) {
        this.reader = reader;
    }

    @Override
    public long solve() {
        try {
            FileOutput fileOutput = reader.readAllData();
            Grid grid = new Grid(fileOutput);
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
