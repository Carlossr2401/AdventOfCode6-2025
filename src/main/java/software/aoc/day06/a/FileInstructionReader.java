package software.aoc.day06.a;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record FileInstructionReader(String filePath) {

    public FileOutput readAllData() throws IOException {
        List<List<Object>> allData = new ArrayList<>();
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (lineNumber < 4) {
                    List<Integer> parsedInts = Arrays.stream(line.split("\\s+"))
                            .filter(s -> !s.isEmpty())
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

                    allData.add((List<Object>) (List<?>) parsedInts);

                } else if (lineNumber == 4) {
                    List<String> parsedStrings = Arrays.stream(line.split("\\s+"))
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());

                    allData.add((List<Object>) (List<?>) parsedStrings);
                }

                lineNumber++;
            }
        }

        List<Integer> line1 = allData.size() > 0 ? (List<Integer>)(List<?>)allData.get(0) : List.of();
        List<Integer> line2 = allData.size() > 1 ? (List<Integer>)(List<?>)allData.get(1) : List.of();
        List<Integer> line3 = allData.size() > 2 ? (List<Integer>)(List<?>)allData.get(2) : List.of();
        List<Integer> line4 = allData.size() > 3 ? (List<Integer>)(List<?>)allData.get(3) : List.of();
        List<String> line5 = allData.size() > 4 ? (List<String>)(List<?>)allData.get(4) : List.of();

        return new FileOutput(line1, line2, line3, line4, line5);
    }
}