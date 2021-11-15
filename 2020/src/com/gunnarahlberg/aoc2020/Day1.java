package com.gunnarahlberg.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws java.io.IOException {
        List<String> t1 = Arrays.asList("1721", "979", "366", "299", "675", "1456");
        Assert.that(puzzle1(t1) == 514579, "t1");
        var day1Input = Files.readAllLines(Path.of("2020/input", "Day1.txt"));
        Assert.that(puzzle1(day1Input) == 838624, "Day1:1");

        Assert.that(puzzle2(t1) == 241861950, "t2");
        Assert.that(puzzle2(day1Input) == 52764180, "Day1:2");
    }

    public static int puzzle1(List<String> lines) {
        for (String s1 : lines) {
            int i1 = Integer.parseInt(s1);
            for (String s2 : lines) {
                int i2 = Integer.parseInt(s2);
                if (i1 + i2 == 2020)
                    return i1 * i2;
            }
        }
        throw new IllegalArgumentException("Failed to find sum 2020");

    }

    public static long puzzle2(List<String> lines) {
        for (String s1 : lines) {
            int i1 = Integer.parseInt(s1);
            for (String s2 : lines) {
                int i2 = Integer.parseInt(s2);
                for (String s3 : lines) {
                    int i3 = Integer.parseInt(s3);
                    if (i1 + i2 + i3 == 2020)
                        return i1 * i2 * i3;
                }
            }
        }
        throw new IllegalArgumentException("Failed to find solution 2");
    }

}
