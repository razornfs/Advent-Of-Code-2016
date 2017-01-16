package aoc.aoc3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AoC3 {
    private static final List<int[]> input = new ArrayList<>();

    static {
        try {
            Files.readAllLines(Paths.get("./src/aoc/aoc3/input"))
                 .forEach(s -> input.add(Arrays.stream(s.trim().split("\\s+"))
                                               .mapToInt(Integer::parseInt)
                                               .toArray()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        int count = 0;
        for (int[] i : input) {
            if (isTriangle(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isTriangle(int... sides) {
        if (sides.length != 3) {
            throw new IllegalArgumentException("A triangle must have 3 sides");
        }
        int[] sorted = sides.clone();
        Arrays.sort(sorted);
        return (sorted[2] < sorted[1] + sorted[0]);
    }

    private static void part2() {
        int count = 0;
        for (int i = 2; i < input.size(); i += 3) {
            int[] a = input.get(i - 2);
            int[] b = input.get(i - 1);
            int[] c = input.get(i);
            count += countVertical(a, b, c);
        }
        System.out.println(count);
    }

    private static int countVertical(int[] a, int[] b, int[] c) {
        int ret = 0;
        for (int i = 0; i < 3; i++) {
            if (isTriangle(a[i], b[i], c[i])) {
                ret++;
            }
        }
        return ret;
    }
}
