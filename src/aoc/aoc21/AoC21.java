package aoc.aoc21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AoC21 {

    private static File file = new File("./src/aoc/aoc21/input");
    private static Scanner scanner;
    private static char[] input;
    private static List<String> instructions = new ArrayList<>();

    static {
        createScanner();
        while (scanner.hasNextLine()) {
            instructions.add(scanner.nextLine());
        }
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        input = "abcdefgh".toCharArray();
        for (String instruction : instructions) {
            parseInstruction(instruction);
        }
        System.out.println(input);
    }

    private static void part2() {
        input = "abcdefgh".toCharArray();
        permute(new String(input));
    }

    private static void createScanner() {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    private static void parseInstruction(String instruction) {
        String[] split = instruction.split(" ");
        String command = split[0];
        switch (command) {
            case "swap":
                swap(split);
                break;
            case "rotate":
                rotate(split);
                break;
            case "reverse":
                reverse(Integer.parseInt(split[2]), Integer.parseInt(split[4]));
                break;
            case "move":
                move(Integer.parseInt(split[2]), Integer.parseInt(split[5]));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void swap(String[] instruction) {
        switch (instruction[1]) {
            case "position":
                swapPositions(Integer.parseInt(instruction[2]), Integer.parseInt(instruction[5]));
                break;
            case "letter":
                swapLetter(instruction[2].charAt(0), instruction[5].charAt(0));
        }
    }

    private static void rotate(String[] instruction) {
        switch (instruction[1]) {
            case "based":
                rotateBasedOn(instruction[6].charAt(0));
                break;
            case "left":
                rotateBy(-Integer.parseInt(instruction[2]));
                break;
            case "right":
                rotateBy(Integer.parseInt(instruction[2]));
                break;
        }
    }

    private static void swapPositions(int x, int y) {
        char temp = input[x];
        input[x] = input[y];
        input[y] = temp;
    }

    private static void swapLetter(char x, char y) {
        swapPositions(indexOf(x), indexOf(y));
    }

    private static int indexOf(char c) {
        for (int i = 0; i < input.length; i++) {
            if (c == input[i]) {
                return i;
            }
        }
        return -1;
    }

    private static void rotateBasedOn(char c) {
        int i = indexOf(c);
        rotateBy(i >= 4 ? i + 2 : i + 1);
    }

    // positive value means rotating to the right; negative - to the left
    private static void rotateBy(int offset) {
        String str = new String(input);
        int len = str.length();
        offset = offset % len;
        int left = (offset < 0) ? -offset : len - offset;
        input = (str + str).substring(left, left + len).toCharArray();
    }

    private static void reverse(int left, int right) {
        while (left < right) {
            swapPositions(left++, right--);
        }
    }

    private static void move(int x, int y) {
        char temp = input[x];
        if (x < y) {
            System.arraycopy(input, x + 1, input, x, y - x);
        } else {
            System.arraycopy(input, y, input, y + 1, x - y);
        }
        input[y] = temp;
    }

    private static void permute(String str) {
        permute("", str);
    }

    private static void permute(String prefix, String str) {
        int len = str.length();
        if (len == 0) {
            input = prefix.toCharArray();
            for (String instruction : instructions) {
                parseInstruction(instruction);
            }
            if ("fbgdceah".equals(new String(input))) {
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < len; i++) {
                permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, len));
            }
        }
    }
}
