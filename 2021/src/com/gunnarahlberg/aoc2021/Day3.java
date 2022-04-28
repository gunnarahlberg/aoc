package com.gunnarahlberg.aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day3 {
    long solution1 = 0, solution2 = 0;
    String day = getClass().getSimpleName();

    static record Input(boolean[] bits) {
        static Input from(String s) {
            boolean[] b = new boolean[s.length()];
            char[] charArray = s.toCharArray();
            for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
                char c = charArray[i];
                b[i] = c == '1';
            }
            return new Input(b);
        }

        public long toLong() {
            return Day3.toLong(bits);
        }
    }

    static long toLong(boolean[] bits) {
        long result = 0;
        for (boolean bit : bits) {
            result = result * 2;
            if (bit)
                result++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        new Day3().solve();
    }

    public void solve() throws IOException {
        long start = System.currentTimeMillis();

        List<String> inputLines = Files.readAllLines(Path.of("2021/input", day + ".txt"));
        List<Input> input = collect(inputLines);

        solution1 = sum1(input);
        solution2 = sum2(input);
        long end = System.currentTimeMillis();
        long duration = Duration.ofMillis(end - start).toMillis();
        System.out.printf("%s in %s ms: %s, %s%n", day, duration, solution1, solution2);
        assert solution1 == 1025636 : day + ":1(" + solution1 + ")";
        assert solution2 == 793873 : day + ":2(" + solution2 + ")";
    }

    private List<Input> collect(List<String> inputLines) {
        return inputLines.stream().map(Input::from).collect(Collectors.toList());
    }

    private static long sum1(List<Input> input) {
        int length = input.get(0).bits.length;
        int[] zeros = new int[length];
        int[] ones = new int[length];
        for (Input i : input) {
            for (int j = 0; j < length; j++) {
                if (i.bits[j]) {
                    ones[j]++;
                } else
                    zeros[j]++;
            }
        }
        boolean[] bits1 = new boolean[length];
        boolean[] bits2 = new boolean[length];
        for (int j = 0; j < length; j++) {
            if (zeros[j] > ones[j]) {
                bits1[j] = false;
                bits2[j] = true;
            } else {
                bits1[j] = true;
                bits2[j] = false;
            }
        }
        long g = toLong(bits1);
        long e = toLong(bits2);
        return g * e;
    }

    private static long sum2(List<Input> input) {
        return LongStream.of(0, 1).map(value -> {
            boolean oxygen = value != 1;
            return getValue(input, oxygen);
        }).reduce((left, right) -> left * right).getAsLong();
    }

    private static long getValue(List<Input> input, boolean ox) {
        int pos = 0;
        List<Input> collect = input;
        while (collect.size() > 1) {
            int zeros = 0, ones = 0;
            for (Input i : collect) {
                if (i.bits[pos]) {
                    ones++;
                } else
                    zeros++;
            }

            final int p = pos;

            boolean compareWith;
            if (ox) {
                compareWith = ones >= zeros;
            } else {
                compareWith = ones < zeros;
            }
            collect = collect.stream().filter(input1 -> input1.bits[p] == compareWith).collect(Collectors.toList());
            pos++;

        }
        return collect.get(0).toLong();
    }

}
