package com.gunnarahlberg.aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {



    void run() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("2020/input/" + this.getClass().getSimpleName() + ".txt"));
        Pattern compile = Pattern.compile("mem\\[(\\d+)]\s= (\\d+)");
        HashMap<Integer, boolean[]> data = new HashMap<>();
        String maskString = null;
        final String MASKPREFIX = "mask = ";
        for (String line : input) {
            if (line.startsWith(MASKPREFIX)) {
                maskString = line.substring(MASKPREFIX.length());
            } else {
                Matcher matcher = compile.matcher(line);
                matcher.find();
                int memoryPosition = Integer.parseInt(matcher.group(1));
                int decValue = Integer.parseInt(matcher.group(2));
                String binary = toBinary(decValue);
                boolean[] memory = data.computeIfAbsent(memoryPosition, unused -> new boolean[36]);
                for (int i = 0; i < 36; i++) {
                    boolean newValue;
                    char maskAtPos = maskString.charAt(i);
                    if (maskAtPos != 'X') {
                        newValue = maskAtPos == '1';
                    } else {
                        newValue = binary.charAt(i) == '1';
                    }
                    memory[i] = newValue;
                }
            }
        }
        long sum = 0;
        for (boolean[] memory : data.values()) {
            sum += longValue(memory);
        }
        System.out.println(sum);
        String format = new DecimalFormat("#0").format(sum);
        String expect = "8570568288597";
        Assert.that(format.equals(expect), "Should be " + expect + "  but was " + format);

    }

    private long longValue(boolean[] arr) {
        long longValue = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                int len = arr.length - 1 - i;
                longValue += (long) Math.pow(2, len);
            }
        }
        return longValue;
    }

    public String toBinary(int n) {
        String binary = "";
        while (n > 0) {
            int rem = n % 2;
            binary = rem + binary;
            n = n / 2;
        }
        return String.format("%36s", binary).replace(' ', '0');
    }

    public static void main(String[] args) throws IOException {
        new Day14().run();
    }
}
