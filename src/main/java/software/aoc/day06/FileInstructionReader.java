package software.aoc.day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInstructionReader implements InstructionReader {
    private final String filePath;

    public FileInstructionReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // We keep empty lines if they are important, or trim if needed.
                // For AoC usually whitespace matters, but empty lines might be separators.
                // Looking at previous custom readers, they ignored empty lines.
                // But for a generic reader, generally we should read everything.
                // However, preserving previous behavior (ignore empty lines) might be safer for logic adaptation.
                // Let's trim and ignore empty for now as per previous logic which did `line.isEmpty()` checks.
                // Actually, the previous `b` reader did `if (line.isEmpty()) continue;`.
                
               // line = line.trim(); // Removed trim to preserve column structure for Part B Grid?
               // Part B `Grid` logic depends on column indices. Trimming might shift columns if there is leading whitespace.
               // Day 6 is usually textual.
               // Let's assume raw lines are better, but skip empty ones if that was the "parsing logic".
               if (!line.isEmpty()) {
                   lines.add(line);
               }
            }
        }
        return lines;
    }
}
