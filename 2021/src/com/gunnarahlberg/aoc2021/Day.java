package com.gunnarahlberg.aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day {
    long solution1 = 0, solution2 = 0;
    String day = getClass().getSimpleName();

    static class Input {
    }

    public static void main(String[] args) throws java.io.IOException {
        new Day2().solve();
    }

    public void solve() throws IOException {
        long start = System.currentTimeMillis();
        List<String> inputLines = Files.readAllLines(Path.of("2021/input", day + ".txt"));
        List<Input> input = inputLines.stream().map(s -> parseLine(s)).collect(Collectors.toList());
        solution1 = sum1(input);
        solution2 = sum2(input);
        long end = System.currentTimeMillis();
        long duration = Duration.ofMillis(end - start).toMillis();
        System.out.printf("%s in %s ms: %s, %s%n", day, duration, solution1, solution2);
        assert solution1 == 0 : day + ":1(" + solution1 + ")";
        assert solution2 == 0 : day + ":2(" + solution2 + ")";
    }

    private static Input parseLine(String s) {
        Input input = new Input();
        return input;
    }

    private static long sum1(List<Input> input) {
        return 0;
    }

    private static long sum2(List<Input> input) {
        return 0;
    }

}
