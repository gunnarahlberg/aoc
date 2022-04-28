package com.gunnarahlberg.aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    long solution1 = 0, solution2 = 0;
    String day = getClass().getSimpleName();

    static record Input(NumbersCalled numbersCalled, List<Board> boards) { }

    static class NumbersCalled {
        private List<Integer> list;
        static NumbersCalled from(String s) {
            NumbersCalled numbersCalled = new NumbersCalled();
            numbersCalled.list = Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            return numbersCalled;
        }
    }

    public static void main(String[] args) throws IOException {
        new Day4().solve();
    }

    public void solve() throws IOException {
        long start = System.currentTimeMillis();

        List<String> inputLines = Files.readAllLines(Path.of("2021/input", day + ".txt"));
        List<String> sample = Arrays.asList(
                "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
                "",
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                " 6 10  3 18  5",
                " 1 12 20 15 19",
                "",
                " 3 15  0  2 22",
                " 9 18 13 17  5",
                "19  8  7 25 23",
                "20 11 10 24  4",
                "14 21 16 12  6",
                "",
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                " 2  0 12  3  7");

       long l = sum2(collect(sample));
        assert 1924 == l;
        Input input = collect(inputLines);

        solution1 = sum1(input);
        solution2 = sum2(input);
        long end = System.currentTimeMillis();
        long duration = Duration.ofMillis(end - start).toMillis();
        System.out.printf("%s in %s ms: %s, %s%n", day, duration, solution1, solution2);
        assert solution1 == 8580 : day + ":1(" + solution1 + ")";
        assert 270 < solution2;
        assert 10292 > solution2;
        assert solution2 == 0 : day + ":2(" + solution2 + ")";
    }
    
    static boolean isBingo(Board b) {
        List<Integer> rows = b.rows;

        for (int j = 0; j < 5; j++) {
            int i = rows.get(j);
            int i1 = rows.get(j+5);
            int i2 = rows.get(j+10);
            int i3 = rows.get(j+15);
            int i4 = rows.get(j+20);
            if (i == -1 && i1 == -1 && i2 == -1 && i3 == -1 && i4 == -1) {
                return true;
            }
        }
        for (int j = 0; j < 20; j+=5) {
            int i = rows.get(j);
            int i1 = rows.get(j+1);
            int i2 = rows.get(j+2);
            int i3 = rows.get(j+3);
            int i4 = rows.get(j+4);
            if (i == -1 && i1 == -1 && i2 == -1 && i3 == -1 && i4 == -1) {
                return true;
            }
        }
        return false;
    }

    static void mark(Board b, int number) {
        int i = b.rows.indexOf(number);
        if(i == -1 )
            return;
        b.rows.set(i, -1);
    }

    static class Board {
        public long sum;
        List<Integer> rows = new ArrayList<>();
        boolean bingo;

    }

    private Input collect(List<String> inputLines) {
        String s = inputLines.get(0);
        NumbersCalled numbers = NumbersCalled.from(s);
        inputLines = inputLines.subList(1, inputLines.size());
        Iterator<String> iterator = inputLines.iterator();

        iterator.next();
        List<Board> boards = new ArrayList<>();
        while (iterator.hasNext()) {
            var board = new Board();
            board.rows = new ArrayList<>(25);
            for (int y = 0; y < 5; y++) {
                String next = iterator.next();
                Arrays.stream(next.trim().split("\\s+")).map(Integer::parseInt).forEach(board.rows::add);
            }

            boards.add(board);
            if (iterator.hasNext())
                iterator.next();
        }
        return new Input(numbers, boards);
    }

    private static long sum1(Input input) {
        for (int i : input.numbersCalled.list) {
            for (Board b : input.boards) {
                Day4.mark(b, i);
                b.bingo = Day4.isBingo(b);
                if (b.bingo) {
                    return sum(b) * i;
                }
            }
        }
        return 0;
    }

    private static long sum(Board b) {
        Stream<Integer> stream = b.rows.stream();
        return stream.mapToInt(Integer::intValue).filter(value -> value > 0).sum();
    }

    private static long sum2(Input input) {
        List<Board> l = new LinkedList<>(input.boards);
        int bingoed = 0;
        int boards = input.boards.size();
        for (int number : input.numbersCalled.list) {
            for (Board b : l) {
                Day4.mark(b, number);
                if(isBingo(b) && b.bingo == false) {
                    bingoed++;
                    b.bingo=true;
                }
                b.sum = sum(b);
                if (bingoed == boards) {
                    return b.sum * number;
                }
            }
        }
        Board first = input.boards.stream().filter(b -> b.bingo == false).findFirst().get();
        return first.sum*input.numbersCalled.list.get(100);
    }
}
