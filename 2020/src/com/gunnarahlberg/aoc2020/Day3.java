package com.gunnarahlberg.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws java.io.IOException {
        var dayInput = Files.readAllLines(Path.of("2020/input", "Day3.txt"));
        Pair<Integer, Long> results = puzzle(dayInput);

        Assert.that(results.a == 169, "Day3:1-" + results.a);
        Assert.that(results.b == 7560370818L, "Day3:2-" + results.b);
    }

    static boolean tree(char c) {
        return c == '#';
    }

    static boolean open(char c) {
        return c == '.';
    }

    public static Pair<Integer, Long> puzzle(List<String> lines) {
        Pair<Integer, Long> pair = new Pair<>();
        pair.a = 0;
        pair.b = 0L;
        for (String s : lines) {
        }
        return pair;
    }

}
