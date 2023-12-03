package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

public class Tools {
    public static <T> T process(String name, Function<Stream<String>, T> op) {
        try (var s = Files.lines(Path.of("data/%s".formatted(name)))) {
            return op.apply(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
