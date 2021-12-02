package com.gunnarahlberg.aoc2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    static int solution1, solution2;

    public static void main(String[] args) throws java.io.IOException {
        List<String> sample1 = Arrays.asList(
                "199",
                "200",
                "208",
                "210",
                "200",
                "207",
                "240",
                "269",
                "260",
                "263"
        );
        int count = sum1(toIntList(sample1));
        assert count == 7 : "t1 : " + count;

        List<String> input = Files.readAllLines(Path.of("2021/input", "Day1.txt"));
        solution1 = sum1(toIntList(input));

        count = sum2(toIntList(sample1));
        assert count == 5 : "t2=" + count;

        solution2 = sum2(toIntList(input));

        System.out.printf("%s, %s%n", solution1, solution2);
        assert solution1 == 1581 : "Day1:1";
        assert solution2 == 1617 : "Day1:2";

    }
    private static int sum1(List<Integer> input) {
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
    private static int sum2(List<Integer> input) {
        List<Integer> toIntList2 = new LinkedList<>();
        int start = 0;

        while (start + 3 <= input.size()) {
            List<Integer> integers = input.subList(start, start + 3);
            int sum = integers.stream().mapToInt(value -> value).sum();
            toIntList2.add(sum);
            start++;
        }

        return sum1(toIntList2);
    }

    private static List<Integer> toIntList(List<String> input) {
        return input.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
