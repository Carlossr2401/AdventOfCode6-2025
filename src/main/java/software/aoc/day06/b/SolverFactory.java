package software.aoc.day06.b;

public class SolverFactory {
    public static Solver createSolver(String filePath) {
        InstructionReader reader = ReaderFactory.createFileReader(filePath);
        return new CephalopodMathSolver(reader);
    }
}
