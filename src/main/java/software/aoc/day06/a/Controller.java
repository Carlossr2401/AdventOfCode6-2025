package software.aoc.day06.a;

import java.io.IOException;

public class Controller {
    private final InstructionReader reader;

    public Controller(InstructionReader reader) {
        this.reader = reader;
    }

    public long run() throws IOException {
        FileOutput data = reader.readAllData();
        Solver solver = SolverFactory.createSolver(data);
        return solver.solve();
    }
}
