package com.gunnarahlberg.aoc2020;

public class Assert {
    static void that(int actual, int expect, String msg) {
        that(actual == expect, msg);
    }

    static void that(boolean pred, String msg) {
        if (!pred) {
            System.err.println("Failed :" + msg);
        } else {
            //System.out.println("P");
        }
    }
}
