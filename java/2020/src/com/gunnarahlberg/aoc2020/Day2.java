package com.gunnarahlberg.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.gunnarahlberg.aoc2020.Utils.*;

public class Day2 {

    static class Password {
        int low, high;
        char key;
        String pass;
        boolean lowMatch, highMatch;
        boolean match2;
    }

    public static void main(String[] args) throws java.io.IOException {
        var dayInput = Files.readAllLines(Path.of("2020/input", "Day2.txt"));
        Assert.that(parse("1-3 a: abcde").low == 1, "Parse pass1");
        Assert.that(parse("1-3 a: abcde").high == 3, "Parse pass2");
        Assert.that(parse("1-3 a: abcde").key == 'a', "Parse pass3");
        Assert.that(parse("1-3 a: abcde").pass.equals("abcde"), "Parse pass4");
        Assert.that(parse("1-3 a: abcde").lowMatch == true, "Parse pass5");
        Assert.that(parse("1-3 a: abcde").highMatch == false, "Parse pass6");
        Pair<Integer, Integer> results = puzzle(dayInput);
        Assert.that(results.a == 483, "Day2:1-" + results.a);
        Assert.that(results.b == 482, "Day2:2-" + results.b);

    }

    static Pattern pattern = Pattern.compile("(\\d+)-(\\d+)\\s(\\w+):\\s(\\w+)");

    static Password parse(String input) {

        Matcher matcher = pattern.matcher(input);
        matcher.find();

        var p = new Password();

        p.low = Integer.parseInt(matcher.group(1));
        p.high = Integer.parseInt(matcher.group(2));
        p.key = matcher.group(3).charAt(0);
        p.pass = matcher.group(4);
        p.lowMatch = p.pass.charAt(p.low - 1) == p.key;
        p.highMatch = p.pass.charAt(p.high - 1) == p.key;

        return p;
    }

    public static Pair<Integer, Integer> puzzle(List<String> lines) {
        Pair<Integer, Integer> pair = new Pair<>();
        pair.a = pair.b = 0;
        for (String s : lines) {
            var p = parse(s);
            int found = stringContains(p.pass, p.key);

            if (found >= p.low && found <= p.high)
                pair.a++;

            if (xor(p.highMatch, p.lowMatch))
                pair.b++;
        }
        return pair;
    }

}
