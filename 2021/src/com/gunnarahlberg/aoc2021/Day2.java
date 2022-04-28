package com.gunnarahlberg.aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    long solution1 = 0, solution2 = 0;
    String day = getClass().getSimpleName();

    static record Input(int amount, String action) {
        static Input from(String s) {
            int i = Integer.parseInt(s.split(" ")[1]);
            String s1 = s.split(" ")[0];
            return new Input(i, s1);
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        new Day2().solve();
    }

    public void solve() throws IOException {
        long start = System.currentTimeMillis();

        List<String> inputLines = Files.readAllLines(Path.of("2021/input", day + ".txt"));
        List<Input> input = inputLines.stream().map(Input::from).collect(Collectors.toList());
        solution1 = sum1(input);
        solution2 = sum2(input);
        long end = System.currentTimeMillis();
        long duration = Duration.ofMillis(end - start).toMillis();
        System.out.printf("%s in %s ms: %s, %s%n", day, duration, solution1, solution2);
        assert solution1 == 2120749 : day + ":1(" + solution1 + ")";
        assert solution2 == 2138382217 : day + ":2(" + solution2 + ")";
    }

    private static long sum1(List<Input> input) {
        long horizontal = 0, depth = 0;
        for (Input i : input) {
            switch (i.action) {
                case "forward" -> horizontal += i.amount;
                case "down" -> depth += i.amount;
                case "up" -> depth -= i.amount;
            }
        }
        return horizontal * depth;
    }

    private static long sum2(List<Input> input) {
        long horizontal = 0, depth = 0, aim = 0;
        for (Input i : input) {
            switch (i.action) {
                case "forward" -> {
                    horizontal += i.amount;
                    depth += aim * i.amount;
                }
                case "down" -> aim += i.amount;
                case "up" -> aim -= i.amount;
            }
        }
        return horizontal * depth;
    }
}
