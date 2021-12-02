package com.gunnarahlberg.aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day1 {

    final String day = getClass().getSimpleName();

    public static void main(String[] args) throws IOException {
        new Day1().solve();
    }

    public void solve() throws IOException {
        long start = System.currentTimeMillis();
        List<Integer> input = Files.readAllLines(Path.of("2021/input", day + ".txt")).stream().map(Day1::parseLine).collect(Collectors.toList());
        long solution1 = sum1(input);
        long solution2 = sum2(input);
        long end = System.currentTimeMillis();
        long duration = Duration.ofMillis(end - start).toMillis();
        System.out.printf("%s in %s ms: %s, %s%n", day, duration, solution1, solution2);
        assert solution1 == 1581 : day + ":1(" + solution1 + ")";
        assert solution2 == 1618 : day + ":2(" + solution2 + ")";

    }

    private static Integer parseLine(String input) {
        return Integer.parseInt(input);
    }

    private static long sum1(List<Integer> input) {
        int last = input.get(0), count = 0;
        int i = 1, size = input.size();
        while (i < size) {
            Integer curr = input.get(i);
            if (curr > last)
                count++;
            last = curr;
            i++;
        }
        return count;
    }

    private static long sum2(List<Integer> input) {
        List<Integer> toIntList2 = new ArrayList<>();
        int start = 0;
        int size = input.size();
        while (start + 3 <= size) {
            List<Integer> integers = input.subList(start, start + 3);
            int sum = integers.stream().mapToInt(value -> value).sum();
            toIntList2.add(sum);
            start++;
        }

        return sum1(toIntList2);
    }
}
