package com.gunnarahlberg.aoc2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    static class Input {
        public int amount;
        String action;
    }

    public static void main(String[] args) throws java.io.IOException {
        long start = System.currentTimeMillis();
        List<String> inputLines = Files.readAllLines(Path.of("2021/input", "Day2.txt"));
        List<Input> input = inputLines.stream().map(Day2::parseLine).collect(Collectors.toList());
        long solution1 = sum1(input);
        long solution2 = sum2(input);
        long end = System.currentTimeMillis();
        long duration = Duration.ofMillis(end - start).toMillis();
        System.out.printf("%s, %s in %s ms %n", solution1, solution2, duration);
        assert solution1 == 2120749 : "Day2:1(" + solution1 + ")";
        assert solution2 == 2138382217 : "Day2:2(" + solution2 + ")";
    }

    private static Input parseLine(String s) {
        String[] s1 = s.split(" ");
        Input r = new Input();
        r.action = s1[0];
        r.amount = Integer.parseInt(s1[1]);
        return r;
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
