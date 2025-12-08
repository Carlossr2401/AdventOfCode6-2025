package software.aoc.day06.b;

import java.util.ArrayList;
import java.util.List;

public record OperationProcessor(FileOutput data) {

    public long calculateGrandTotal() {
        List<List<String>> matrix = new ArrayList<>();
        matrix.add(data.dataLine1());
        matrix.add(data.dataLine2());
        matrix.add(data.dataLine3());
        matrix.add(data.dataLine4());
        matrix.add(data.dataLine5());

        int maxColumns = matrix.stream().mapToInt(List::size).max().orElse(0);

        long grandTotal = 0;
        int currentColumn = maxColumns - 1; // Empezamos a la derecha

        while (currentColumn >= 0) {

            String operator = getChar(matrix, 4, currentColumn); // Operador está en la Fila 4 (índice 4)

            if (operator.equals("+") || operator.equals("*")) {

                int endCol = currentColumn - 1;
                int startCol = endCol;

                while (startCol >= 0) {
                    String charAtBottom = getChar(matrix, 4, startCol);

                    if (!charAtBottom.equals(" ")) {
                        startCol--;
                    } else {
                        break;
                    }
                }

                long problemResult = solveIndividualProblem(matrix, startCol + 1, endCol, operator);
                grandTotal += problemResult;

                currentColumn = startCol;

            } else {
                currentColumn--;
            }
        }

        return grandTotal;
    }

    private String getChar(List<List<String>> matrix, int row, int col) {
        if (row < matrix.size() && col < matrix.get(row).size()) {
            return matrix.get(row).get(col);
        }
        return " ";
    }

    private long solveIndividualProblem(List<List<String>> matrix, int startCol, int endCol, String operator) {
        List<Long> numbers = new ArrayList<>();

        for (int col = startCol; col <= endCol; col++) {
            StringBuilder numStr = new StringBuilder();

            for (int row = 0; row < 4; row++) {
                String c = getChar(matrix, row, col);
                if (!c.trim().isEmpty()) {
                    numStr.append(c);
                }
            }

            if (numStr.length() > 0) {
                try {
                    numbers.add(Long.parseLong(numStr.toString()));
                } catch (NumberFormatException e) {
                }
            }
        }

        if (numbers.isEmpty()) return 0;

        if (operator.equals("+")) {
            return numbers.stream().mapToLong(Long::longValue).sum();
        } else if (operator.equals("*")) {
            return numbers.stream().reduce(1L, (a, b) -> a * b);
        }

        return 0;
    }
}