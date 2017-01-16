package aoc.aoc2;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AoC2 {
    private static final List<String> input;

    static {
        try {
            input = Files.readAllLines(Paths.get("./src/aoc/aoc2/input"));
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
        final char[][] keyPad = new char[][]{
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };
        Point start = new Point(1, 1);
        solve(start, keyPad);
    }

    private static void part2() {
        final char[][] keyPad = new char[][]{
                {' ', ' ', '1', ' ', ' '},
                {' ', '2', '3', '4', ' '},
                {'5', '6', '7', '8', '9'},
                {' ', 'A', 'B', 'C', ' '},
                {' ', ' ', 'D', ' ', ' '}
        };
        Point start = new Point(0, 2);
        solve(start, keyPad);
    }

    private static void solve(Point currentPosition, char[][] keyPad) {
        String code = "";
        for (String instruction : input) {
            for (int i = 0; i < instruction.length(); i++) {
                char c = instruction.charAt(i);
                switch (c) {
                    case 'U':
                        currentPosition.y = moveUp(currentPosition, keyPad);
                        break;
                    case 'D':
                        currentPosition.y = moveDown(currentPosition, keyPad);
                        break;
                    case 'L':
                        currentPosition.x = moveLeft(currentPosition, keyPad);
                        break;
                    case 'R':
                        currentPosition.x = moveRight(currentPosition, keyPad);
                        break;
                }
            }
            code += keyPad[currentPosition.y][currentPosition.x];
        }
        System.out.println(code);
    }

    private static int moveUp(Point position, char[][] keypad) {
        return (position.y == 0) ? position.y :
               (keypad[position.y - 1][position.x] == ' ') ? position.y : position.y - 1;
    }

    private static int moveDown(Point position, char[][] keypad) {
        return (position.y == keypad.length - 1) ? position.y :
               (keypad[position.y + 1][position.x] == ' ') ? position.y : position.y + 1;
    }

    private static int moveLeft(Point position, char[][] keypad) {
        return (position.x == 0) ? position.x :
               (keypad[position.y][position.x - 1] == ' ') ? position.x : position.x - 1;
    }

    private static int moveRight(Point position, char[][] keypad) {
        return (position.x == keypad[position.y].length - 1) ? position.x :
               (keypad[position.y][position.x + 1] == ' ') ? position.x : position.x + 1;
    }
}
