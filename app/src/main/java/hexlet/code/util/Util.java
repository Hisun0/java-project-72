package hexlet.code.util;

import hexlet.code.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Util {
    public static String readResourceFile() throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream("schema.sql");

        if (inputStream == null) {
            throw new IOException("File \"schema.sql\" is not found!");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
