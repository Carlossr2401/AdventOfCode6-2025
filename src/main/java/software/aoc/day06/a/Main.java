package software.aoc.day06.a;

import java.io.IOException;

public class Main {
    static void main() {
        try {
            // Inyección de la implementación concreta de lectura
            InstructionReader reader = new FileInstructionReader("src/main/resources/input");
            
            Controller controller = new Controller(reader);
            long result = controller.run();
            
            // Main actúa como la Vista simplificada
            System.out.println(result);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
