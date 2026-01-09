package software.aoc.day06.a;

public class ConsoleView {
    public void showResult(long result) {
        System.out.println(result);
    }

    public void showError(String message) {
        System.err.println("Error: " + message);
    }
}
