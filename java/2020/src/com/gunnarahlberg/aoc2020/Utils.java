package com.gunnarahlberg.aoc2020;

public class Utils {
    public static int stringContains(String s, char c) {
        int found = 0;
        for (char c1 : s.toCharArray()) {
            if (c1 == c)
                found++;
        }
        return found;
    }

    public static boolean xor(boolean a, boolean b) {
        return (a && !b) || (!a && b);
    }

}
