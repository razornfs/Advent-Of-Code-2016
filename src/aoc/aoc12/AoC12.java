package aoc.aoc12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AoC12 {

    private static Scanner scanner;
    private static List<String> input;

    static {
        File file = new File("./src/aoc/aoc12/input");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        start(new int[]{0, 0, 0, 0});
    }

    private static void part2() {
        start(new int[]{0, 0, 1, 0});
    }

    private static void start(int[] registers) {
        int i = 0;
        while (i < input.size()) {
            String[] s = input.get(i).split(" ");
            String command = s[0];
            switch (command) {
                case "cpy": {
                    registers[getRegister(s[2])] = getValue(s[1], registers);
                    i++;
                    break;
                }
                case "inc": {
                    registers[getRegister(s[1])]++;
                    i++;
                    break;
                }
                case "dec": {
                    registers[getRegister(s[1])]--;
                    i++;
                    break;
                }
                case "jnz": {
                    i += (getValue(s[1], registers) == 0) ? 1 : Integer.parseInt(s[2]);
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(registers));
    }

    private static int getValue(String source, int[] registers) {
        if (source.charAt(0) >= 'a' && source.charAt(0) <= 'd') {
            return registers[source.charAt(0) - 'a'];
        }
        return Integer.parseInt(source);
    }

    private static int getRegister(String s) {
        return s.charAt(0) - 'a';
    }
}
