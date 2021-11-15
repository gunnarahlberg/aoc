package com.gunnarahlberg.aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    static Pattern pattern = Pattern.compile("(\\d+)-(\\d+)\\s(\\w+):\\s(\\w+)");

    static Password from(String l) {
        Password p = new Password();
        Matcher mr = pattern.matcher(l);
        if (!mr.find())
            throw new IllegalArgumentException("Bad record " + l);
        p.low = Integer.parseInt(mr.group(1));
        p.high = Integer.parseInt(mr.group(2));
        p.key = mr.group(3).toCharArray()[0];
        p.pass = mr.group(4).toCharArray();
        return p;
    }

    static class Password {
        int low, high;
        char key;
        char[] pass;
    }

    public static void main(String[] args) throws Exception {
        new Day2().run();
    }
    public void run() throws IOException {
        int result1 = 0, result2 = 0;

        for (String s : Files.readAllLines(Paths.get("2020/input/"+this.getClass().getSimpleName() +".txt"))) {
            Password password = from(s);
            int found = 0;
            char key = password.key;
            for (char c : password.pass) {
                if (key == c)
                    found++;
            }

            int low = password.low;
            int high = password.high;

            if (found >= low && found <= high)
                result1++;

            boolean p1 = password.pass[--low] == key;
            boolean p2 = password.pass[--high] == key;
            if (p1 != p2)
                result2 += 1;

        }
        System.out.printf("%s, %s%n", result1, result2);

    }
}
