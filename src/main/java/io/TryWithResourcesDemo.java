package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TryWithResourcesDemo {
    public static void main(String[] args) throws IOException {
        String path = "src/scripts.main/resources/book_data.csv";

        File file = new File(path);
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(System.out::println);
        }
    }
}
