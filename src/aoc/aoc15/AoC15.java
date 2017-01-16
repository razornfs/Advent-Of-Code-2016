package aoc.aoc15;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AoC15 {

    // using Point to represent a disc, x coordinate for amount of positions, and y coordinate for starting position
    // why? I'm lazy
    private static List<Point> input1 =
            Arrays.asList(new Point(13, 1), new Point(19, 10), new Point(3, 2),
                          new Point(7, 1), new Point(5, 3), new Point(17, 5));

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        start(input1);
    }

    private static void part2() {
        List<Point> input2 = new ArrayList<>(input1);
        input2.add(new Point(11, 0));
        start(input2);
    }

    private static void start(List<Point> input) {
        int currTime = 0;
        while (!capsuleWillPass(currTime, input)) {
            currTime++;
        }
        System.out.println(currTime);
    }

    private static boolean isOpen(int time, Point disc) {
        return (disc.y + time) % disc.x == 0;
    }

    private static boolean capsuleWillPass(int time, List<Point> input) {
        for (int i = 0; i < input.size(); i++) {
            if (!isOpen(i + time + 1, input.get(i))) {
                return false;
            }
        }
        return true;
    }
}
