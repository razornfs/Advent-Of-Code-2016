package aoc.aoc19;

public class AoC19 {
    private static final int input = 3005290;

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        start1();
        System.out.print("Part 2: ");
        start2();
    }

    private static void start1() {
        System.out.println(part1(input));
    }

    private static void start2() {
        System.out.println(part2(input));
    }

    private static int part1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        if (n % 2 == 0) {
            return 2 * part1(n / 2) - 1;
        } else {
            return 2 * part1(n / 2) + 1;
        }
    }

    private static int part2(int n) {
        int p = 1;
        while (3 * p <= n) {
            p *= 3;
        }
        if (n == p) {
            return n;
        }
        return n - p + Math.max(n - 2 * p, 0);
    }
}
