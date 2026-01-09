package software.aoc.day06.b;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final List<List<String>> rows;
    private final int width;

    public Grid(FileOutput fileOutput) {
        this.rows = new ArrayList<>();
        rows.add(fileOutput.dataLine1());
        rows.add(fileOutput.dataLine2());
        rows.add(fileOutput.dataLine3());
        rows.add(fileOutput.dataLine4());
        rows.add(fileOutput.dataLine5());

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
}
