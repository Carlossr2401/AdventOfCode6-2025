package software.aoc.day06.a;

public class Main {
    static void main() {
        ConsoleView view = new ConsoleView();
        FileInstructionReader reader = new FileInstructionReader("src/main/resources/input");
        
        Controller controller = new Controller(view, reader);
        controller.run();
    }
}
