package software.aoc.day06.b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record FileInstructionReader(String filePath) {

    public FileOutput readAllData() throws IOException {
        List<List<String>> allData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isEmpty()) {
                    continue;
                }

                List<String> parsedCharacters = line.chars() // Obtiene un IntStream de códigos de caracteres
                        .mapToObj(c -> String.valueOf((char) c)) // Convierte cada código a su String de carácter
                        .collect(Collectors.toList());

                allData.add(parsedCharacters);
            }
        }

        List<String> line1 = allData.size() > 0 ? allData.get(0) : List.of();
        List<String> line2 = allData.size() > 1 ? allData.get(1) : List.of();
        List<String> line3 = allData.size() > 2 ? allData.get(2) : List.of();
        List<String> line4 = allData.size() > 3 ? allData.get(3) : List.of();
        List<String> line5 = allData.size() > 4 ? allData.get(4) : List.of();

        return new FileOutput(line1, line2, line3, line4, line5);
    }
}