/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package src;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Day01 {
    private static final Map<String, String> map = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public static void main(String[] args) throws Exception{
        try (var s = Files.lines(Path.of("data/day01.txt"))) {
            var sum = s.mapToInt(Day01::process).sum();
            System.out.println(sum);
        }
    }

    private static int process(String s) {
        try {
            return process1(s);
        } catch (Exception e) {
            System.out.println(s);
            throw e;
        }
    }

    private static int process1(String line) {
        String firstDigit = null;
        String lastDigit = null;
        int i = 0;
        int j = line.length() - 1;
        while (i <= j) {
            if (firstDigit == null) {
                int pos = i;
                firstDigit = map.keySet().stream()
                        .filter(k -> k.equals(line.substring(pos, Math.min(pos + k.length(), line.length()))))
                        .map(map::get)
                        .findAny()
                        .orElse(null);
            }
            if (lastDigit == null) {
                int pos = j;
                lastDigit = map.keySet().stream()
                        .filter(k -> k.equals(line.substring(pos, Math.min(pos + k.length(), line.length()))))
                        .map(map::get)
                        .findAny()
                        .orElse(null);
            }

            if (firstDigit == null && Character.isDigit(line.charAt(i))) {
                firstDigit = String.valueOf(line.charAt(i));
            }
            if (lastDigit == null && Character.isDigit(line.charAt(j))) {
                lastDigit = String.valueOf(line.charAt(j));
            }

            if (firstDigit != null && lastDigit != null) {
                return Integer.parseInt(firstDigit + lastDigit);
            }

            if (firstDigit == null) i += 1;
            if (lastDigit == null) j -= 1;
        }
        throw new RuntimeException();
    }
}
