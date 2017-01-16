package aoc.aoc16;

public class AoC16 {
    private static final StringBuilder input = new StringBuilder("01111001100111011");

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        start(272);
        System.out.print("Part 2: ");
        start(35651584);
    }

    private static void start(int size) {
        System.out.println(checkSum(new StringBuilder(fill(size).substring(0, size))));
    }

    private static String fill(int size) {
        while (input.length() < size) {
            StringBuilder b = new StringBuilder();
            for (int i = input.length() - 1; i >= 0; i--) {
                b.append((input.charAt(i) == '0') ? '1' : '0');
            }
            input.append('0').append(b);
        }
        return input.toString();
    }

    private static StringBuilder checkSum(StringBuilder input) {
        if (input.length() % 2 == 1) {
            return input;
        }
        StringBuilder checkSum = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            checkSum.append((input.charAt(i) == input.charAt(i + 1)) ? '1' : '0');
        }
        return checkSum(checkSum);
    }
}
