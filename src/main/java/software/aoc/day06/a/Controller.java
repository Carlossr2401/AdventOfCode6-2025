package software.aoc.day06.a;

import java.io.IOException;

public class Controller {
    private final ConsoleView view;
    private final FileInstructionReader reader;

    public Controller(ConsoleView view, FileInstructionReader reader) {
        this.view = view;
        this.reader = reader;
    }

    public void run() {
        try {
            FileOutput data = reader.readAllData();
            Solver solver = SolverFactory.createSolver(data);
            long result = solver.solve();
            view.showResult(result);
        } catch (IOException e) {
            view.showError("Failed to read input: " + e.getMessage());
        }
    }
}
