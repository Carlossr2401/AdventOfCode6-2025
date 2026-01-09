package software.aoc.day06.b;


public class NumberParser {
    private final Grid grid;

    public NumberParser(Grid grid) {
        this.grid = grid;
    }

    public long extractNumber(int col) {
        StringBuilder sb = new StringBuilder();
        // Rows 0 to 3 are potential digits
        for (int row = 0; row < 4; row++) {
             String c = grid.getChar(row, col);
             if (c != null && !c.trim().isEmpty() && Character.isDigit(c.trim().charAt(0))) {
                 sb.append(c.trim());
             }
        }
        
        if (sb.length() > 0) {
            return Long.parseLong(sb.toString());
        }
        return -1;
    }
}
