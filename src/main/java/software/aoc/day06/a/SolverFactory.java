package software.aoc.day06.a;

public class SolverFactory {

    /**
     * Factory Method to create the appropriate Solver.
     * Encapsulates the logic of which solver to instantiate.
     */
    public static Solver createSolver(FileOutput data) {
        // En un escenario más complejo, aquí podríamos decidir qué implementación de Solver devolver
        // basándonos en alguna propiedad de 'data' o configuración externa.
        // Por ahora, devolvemos la implementación estándar para la Parte A.
        return new OperationProcessor(data);
    }
}
