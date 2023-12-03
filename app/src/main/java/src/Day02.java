package src;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Day02 {
    static final Pattern PATTERN = Pattern.compile("(\\d+) ([a-z]+)");
    static final int MAX_RED = 12;
    static final int MAX_GREEN = 13;
    static final int MAX_BLUE = 14;
    static final Map<String, Integer> map = Map.of("red", MAX_RED, "blue", MAX_BLUE, "green", MAX_GREEN);

    public static void main(String[] args) {
        var sum = Tools.process("day02.txt", s ->
                s.mapToInt(Day02::process2).sum());
        System.out.println(sum);
    }

    static int process1(String line) {
        var parts1 = line.split(":");
        var gameNo = parseInt(parts1[0].split(" ")[1]);
        var games = parts1[1].split(";");
        for (String game : games) {
            var colors = game.trim().split(",");
            for (var color : colors) {
                var m = PATTERN.matcher(color.trim());
                if (m.find()) {
                    var n = m.group(1);
                    var c = m.group(2);
                    var max = map.get(c);
                    if (parseInt(n) > max) {
                        return 0;
                    }
                }
            }
        }
        return gameNo;
    }

    static int process2(String line) {
        Map<String, Integer> maxMap = new HashMap<>();
        var parts1 = line.split(":");
        var gameNo = parseInt(parts1[0].split(" ")[1]);
        var games = parts1[1].split(";");
        for (String game : games) {
            var colors = game.trim().split(",");
            for (var color : colors) {
                var m = PATTERN.matcher(color.trim());
                if (m.find()) {
                    var n = Integer.parseInt(m.group(1));
                    var c = m.group(2);
                    maxMap.compute(c, (k, v) -> v == null ? n : Math.max(v, n));
                }
            }
        }
        return maxMap.values().stream().mapToInt(Integer::intValue).reduce(1, (a, b) -> a * b);
    }
}
