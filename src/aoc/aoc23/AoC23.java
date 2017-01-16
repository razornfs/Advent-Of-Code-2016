package aoc.aoc23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AoC23 {

    private static Scanner scanner;
    private static List<String[]> input;
    private static List<String[]> inputClone;

    static {
        File file = new File("./src/aoc/aoc23/input");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine().split(" "));
        }
        inputClone = input.stream().map(String[]::clone).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        start(new int[]{7, 0, 0, 0});
    }

    private static void part2() {
        start(new int[]{12, 0, 0, 0});
    }

    private static void start(int[] registers) {
        int i = 0;
        while (i < input.size()) {
            String[] s = input.get(i);
            String command = s[0];
            switch (command) {
                case "cpy": {
                    int register = getRegister(s[2]);
                    if (register != -1) {
                        registers[register] = getValue(s[1], registers);
                    }
                    i++;
                    break;
                }
                case "inc": {
                    int register = getRegister(s[1]);
                    if (register != -1) {
                        registers[register]++;
                    }
                    i++;
                    break;
                }
                case "dec": {
                    int register = getRegister(s[1]);
                    if (register != -1) {
                        registers[register]--;
                    }
                    i++;
                    break;
                }
                case "jnz": {
                    i += (getValue(s[1], registers) == 0) ? 1 : getValue(s[2], registers);
                    break;
                }
                case "tgl": {
                    toggle(getValue(s[1], registers) + i);
                    i++;
                    break;
                }
            }
        }
        input = inputClone.stream().map(String[]::clone).collect(Collectors.toList());
        System.out.println(Arrays.toString(registers));
    }

    private static int getValue(String source, int[] registers) {
        if (source.charAt(0) >= 'a' && source.charAt(0) <= 'd') {
            return registers[source.charAt(0) - 'a'];
        }
        return Integer.parseInt(source);
    }

    private static int getRegister(String s) {
        int register = s.charAt(0) - 'a';
        if (register >= 0 && register < 4) {
            return register;
        }
        return -1;
    }

    private static void toggle(int which) {
        if (which >= 0 && which < input.size()) {
            String[] curr = input.get(which);
            String command = curr[0];
            switch (curr.length) {
                case 2:
                    if (command.equals("inc")) {
                        curr[0] = "dec";
                    } else {
                        curr[0] = "inc";
                    }
                    break;
                case 3:
                    if (command.equals("jnz")) {
                        curr[0] = "cpy";
                    } else {
                        curr[0] = "jnz";
                    }
            }
        }
    }
}
