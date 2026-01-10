package software.aoc.day06;

public class Main {
    
    // Standard entry point
    public static void main(String[] args) {
        // Defaulting to Part B as it's the advanced one, or running both?
        // Let's run both for demonstration.
        System.out.println("Running Day 6 Solutions...");
        
        String inputPath = "src/main/resources/input";
        
        try {
            System.out.println("--- Part A ---");
            Solver solverA = SolverFactory.createSolver(SolverFactory.Part.A, inputPath);
            System.out.println("Result A: " + solverA.solve());
            
            System.out.println("\n--- Part B ---");
            Solver solverB = SolverFactory.createSolver(SolverFactory.Part.B, inputPath);
            System.out.println("Result B: " + solverB.solve());
            
        } catch (Exception e) {
            System.err.println("Error running solvers: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Previous entry point signature compatibility
    static void main() {
        main(new String[]{});
    }
}
