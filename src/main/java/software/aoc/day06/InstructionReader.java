package software.aoc.day06;

import java.io.IOException;
import java.util.List;

public interface InstructionReader {
    List<String> readAllLines() throws IOException;
}
