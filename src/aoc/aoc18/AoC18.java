package aoc.aoc18;

import java.util.Arrays;

public class AoC18 {

    private static final char[][] trapPatterns = {{'^', '^', '.'},
                                                  {'.', '^', '^'},
                                                  {'^', '.', '.'},
                                                  {'.', '.', '^'}};

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        start(40);
    }

    private static void part2() {
        start(400000);
    }

    private static void start(int rowAmount) {
        StringBuilder prevRow;
        StringBuilder nextRow = new StringBuilder(
                "^.^^^.^..^....^^....^^^^.^^.^...^^.^.^^.^^.^^..^.^...^.^..^.^^.^..^.....^^^.^.^^^..^^...^^^...^...^.");
        int count = 0;
        for (int i = 0; i < rowAmount; i++) {
            prevRow = nextRow;
            nextRow = getNewRow(prevRow);
            count += countChars('.', prevRow);
        }
        System.out.println(count);
    }

    private static StringBuilder getNewRow(StringBuilder lastRow) {
        StringBuilder newRow = new StringBuilder();
        for (int j = 0; j < lastRow.length(); j++) {
            if (isTrap(lastRow, j)) {
                newRow.append('^');
            } else {
                newRow.append('.');
            }
        }
        return newRow;
    }

    private static boolean isTrap(StringBuilder lastRow, int index) {
        return matchesTrapPattern(index == 0 ? '.' : lastRow.charAt(index - 1),
                                  lastRow.charAt(index),
                                  index == lastRow.length() - 1 ? '.' : lastRow.charAt(index + 1));
    }

    private static boolean matchesTrapPattern(char... T) {
        if (T.length != 3) {
            return false;
        }
        for (char[] trapPattern : trapPatterns) {
            if (Arrays.equals(trapPattern, T)) {
                return true;
            }
        }
        return false;
    }

    private static int countChars(char c, StringBuilder s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (c == s.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
