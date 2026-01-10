package software.aoc.day06;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    private final List<List<String>> rows;
    private final int width;

    // Constructor adapted to take List<String> (lines) directly
    public Grid(List<String> fileLines) {
        this.rows = fileLines.stream()
                .map(line -> line.chars()
                        .mapToObj(c -> String.valueOf((char) c))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        this.width = rows.stream().mapToInt(List::size).max().orElse(0);
    }

    public String getChar(int row, int col) {
        if (row >= 0 && row < rows.size()) {
            List<String> line = rows.get(row);
            if (col >= 0 && col < line.size()) {
                return line.get(col);
            }
        }
        return " ";
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return rows.size();
    }
}
