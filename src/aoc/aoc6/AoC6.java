package aoc.aoc6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC6 {

    private static Scanner scanner;
    private static int[][] count;

    static {
        File file = new File("./src/aoc/aoc6/input");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        count = new int[8][26];
        while (scanner.hasNextLine()) {
            String cur = scanner.nextLine();
            for (int i = 0; i < cur.length(); i++) {
                count[i][cur.charAt(i) - 'a']++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        String result1 = "";
        for (int i = 0; i < 8; i++) {
            int max = 0;
            char maxChar = '0';
            for (int j = 0; j < 26; j++) {
                if (count[i][j] > max) {
                    max = count[i][j];
                    maxChar = (char) (j + 'a');
                }
            }
            result1 += maxChar;
        }
        System.out.println(result1);
    }

    private static void part2() {
        String result2 = "";
        for (int i = 0; i < 8; i++) {
            int min = Integer.MAX_VALUE;
            char minChar = '0';
            for (int j = 0; j < 26; j++) {
                if (count[i][j] < min && count[i][j] != 0) {
                    min = count[i][j];
                    minChar = (char) (j + 'a');
                }
            }
            result2 += minChar;
        }
        System.out.println(result2);
    }
}
