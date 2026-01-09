package software.aoc.day06.a;

import java.util.List;

public record OperationProcessor(FileOutput data) implements Solver {

    @Override
    public long solve() {

        List<Integer> line1 = data.dataLine1();
        List<Integer> line2 = data.dataLine2();
        List<Integer> line3 = data.dataLine3();
        List<Integer> line4 = data.dataLine4();
        List<String> operators = data.dataLine5();

        long finalResult = 0;

        int size = line1.size();

        for (int i = 0; i < size; i++) {

            int val1 = line1.get(i);
            int val2 = line2.get(i);
            int val3 = line3.get(i);
            int val4 = line4.get(i);
            String op = operators.get(i % operators.size());

            switch (op) {
                case "+":
                    finalResult += val1 + val2 + val3 + val4;
                    break;
                case "*":
                    finalResult += (long) val1 * val2 * val3 * val4;
            }

        }

        return finalResult;
    }
}