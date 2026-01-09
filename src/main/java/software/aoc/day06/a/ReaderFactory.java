package software.aoc.day06.a;

public class ReaderFactory {
    public static InstructionReader createFileReader(String filePath) {
        return new FileInstructionReader(filePath);
    }
}
