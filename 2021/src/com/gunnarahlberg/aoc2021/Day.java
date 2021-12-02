package com.gunnarahlberg.aoc2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day {
    static int solution1, solution2;

    public static void main(String[] args) throws java.io.IOException {
        List<String> sample1 = Arrays.asList(
        );
        List<String> input = Files.readAllLines(Path.of("2021/input", "Day?.txt"));

        solution1 = sum1(input);
        solution2 = sum2(input);
        System.out.printf("%s, %s%n",solution1, solution2);
        assert solution1 == 0 : "Day1:2=" + solution2;
        assert solution2 == 0 : "Day1:2=" + solution2;
    }

    private static int sum1(List<String> input) {
        return 0;
    }
    private static int sum2(List<String> input) {
        return 0;
    }


}
