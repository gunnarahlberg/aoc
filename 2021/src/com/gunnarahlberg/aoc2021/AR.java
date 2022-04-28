package com.gunnarahlberg.aoc2021;

public record AR(String s1, int s2) {
    boolean fun() {
        return s1.equals("soo");
    }
}
