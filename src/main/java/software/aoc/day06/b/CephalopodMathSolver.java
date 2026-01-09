package software.aoc.day06.b;

import java.util.List;

public class CephalopodMathSolver {
    private final FileOutput fileOutput;

    public CephalopodMathSolver(FileOutput fileOutput) {
        this.fileOutput = fileOutput;
    }

    public long solve() {
        Grid grid = new Grid(fileOutput);
        ProblemScanner scanner = new ProblemScanner(grid);
        List<Problem> problems = scanner.scan();

        return problems.stream()
                .mapToLong(Problem::solve)
                .sum();
    }
}
