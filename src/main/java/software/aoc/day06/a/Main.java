package software.aoc.day06.a;

import java.io.IOException;

public class Main {
    static void main() throws IOException {
        FileInstructionReader reader = new FileInstructionReader("src/main/resources/input");
        FileOutput data = reader.readAllData();

        System.out.println(new OperationProcessor(data).calculateResult());
    }
}
