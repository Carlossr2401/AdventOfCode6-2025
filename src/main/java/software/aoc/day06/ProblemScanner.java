package software.aoc.day06;

import java.util.ArrayList;
import java.util.List;

public class ProblemScanner {
    private final Grid grid;
    private final NumberParser numberParser;

    public ProblemScanner(Grid grid) {
        this.grid = grid;
        this.numberParser = new NumberParser(grid);
    }

    public List<Problem> scan() {
        List<Problem> problems = new ArrayList<>();
        int width = grid.getWidth();
        
        List<Integer> currentBatchCols = new ArrayList<>();
        
        // Scan Right to Left
        for (int col = width - 1; col >= -1; col--) { // -1 to force finish last batch
            boolean isEmptyCol = isColumnEmpty(col);
            
            if (isEmptyCol) {
                if (!currentBatchCols.isEmpty()) {
                    problems.add(createProblem(currentBatchCols));
                    currentBatchCols.clear();
                }
            } else {
                currentBatchCols.add(col);
            }
        }
        
        return problems;
    }
    
    private boolean isColumnEmpty(int col) {
        if (col < 0) return true;
        
        // Assuming 5 rows total as per Day06 logic (0-3 numbers, 4 operator)
        int height = grid.getHeight();
        for (int row = 0; row < height; row++) { 
            String c = grid.getChar(row, col);
            if (!c.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private Problem createProblem(List<Integer> cols) {
        // Columns are already in order R -> L (collected that way)
        List<Long> numbers = new ArrayList<>();
        Operator operator = null;
        
        for (int col : cols) {
            // Extract number
            long val = numberParser.extractNumber(col);
            if (val != -1) {
                numbers.add(val);
            }
            
            // Look for operator in this column (Row 4)
            String potentialOp = grid.getChar(4, col).trim();
            if (!potentialOp.isEmpty()) {
                 try {
                     operator = Operator.fromSymbol(potentialOp);
                 } catch (IllegalArgumentException e) {
                     // Ignore if not a valid operator
                 }
            }
        }
        
        if (operator == null) {
            throw new IllegalStateException("No operator found for problem at cols: " + cols);
        }
        
        return new Problem(numbers, operator);
    }
}
