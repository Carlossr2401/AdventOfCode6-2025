package software.aoc.day06;

import software.aoc.day06.a.Day06ASolver;
import software.aoc.day06.b.Day06BSolver;

public class SolverFactory {

    public enum Part {
        A, B
    }

    public static Solver createSolver(Part part, String filePath) {
        InstructionReader reader = ReaderFactory.createFileReader(filePath);
        
        switch (part) {
            case A:
                return new Day06ASolver(reader);
            case B:
                return new Day06BSolver(reader);
            default:
                throw new IllegalArgumentException("Unknown part: " + part);
        }
    }
}
