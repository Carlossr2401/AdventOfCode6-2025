package software.aoc.day06.b;

import java.io.IOException;

public class Main {
    static void main() throws IOException {
        Solver solver = SolverFactory.createSolver("src/main/resources/input");
        System.out.println(solver.solve());
    }
}
