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

    public static int wrap(int curr, int inc, int end) {
        int next = curr + inc;
        if (next >= end)
            return next - end;
        return next;
    }

    public static IntPair newPos(IntPair start, IntPair inc, IntPair grid) {
        // Move in a x,y coordinate using inc
        IntPair newPos = new IntPair(start.a, start.b);
        movePos(newPos, inc, grid);
        return newPos;
    }

    public static void movePos(IntPair pos, IntPair inc, IntPair grid) {
        // Move in a x,y coordinate using inc
        pos.a = wrap(pos.a, inc.a, grid.a);
        pos.b = wrap(pos.b, inc.b, grid.b);
    }

    public static class IntPair {
        int a = 0, b = 0;

        public IntPair() {
        }

        public IntPair(int a, int b) {
            this.a = a;
            this.b = b;
        }

    }

    public static void test() {
        Assert.that(Utils.newPos(new IntPair(1, 1), new IntPair(3, 2), new IntPair(8, 8)).a == 4, "newPos 1");
        Assert.that(Utils.newPos(new IntPair(1, 1), new IntPair(6, 2), new IntPair(8, 8)).a == 7, "newPos 2");
        Assert.that(Utils.newPos(new IntPair(1, 1), new IntPair(7, 2), new IntPair(8, 8)).a == 0, "newPos 3");
        Assert.that(Utils.newPos(new IntPair(1, 1), new IntPair(8, 1), new IntPair(8, 8)).a == 1, "newPos 4");
        Assert.that(Utils.newPos(new IntPair(1, 1), new IntPair(9, 1), new IntPair(8, 8)).a == 2, "newPos 5");
        Assert.that(wrap(1, 2, 4) == 3, "Wrap 2");

    }

    public static void main(String[] args) {
        test();
    }

}
