package aoc.aoc1;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AoC1 {
    private static final String[] input;
    private static final String[] directions;

    private static List<Point> visited;

    static {
        try {
            input = Files.readAllLines(Paths.get("./src/aoc/aoc1/input"))
                         .get(0)
                         .split(", ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        directions = new String[]{"North", "East", "South", "West"};
        visited = new ArrayList<>(input.length);
        parseInput();
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        Point last = visited.get(visited.size() - 1);
        System.out.println(Math.abs(last.x) + Math.abs(last.y));
    }

    private static void part2() {
        outer:
        for (int i = 1; i < visited.size() - 1; i++) {
            Point prevStart = visited.get(i);
            Point prevEnd = visited.get(i + 1);
            for (int j = 0; j < i; j++) {
                Point nextStart = visited.get(j);
                Point nextEnd = visited.get(j + 1);

                Optional<Point> intersection = getIntersection(prevStart, prevEnd, nextStart, nextEnd);
                if (intersection.isPresent()) {
                    System.out.println(Math.abs(intersection.get().x) + Math.abs(intersection.get().y));
                    break outer;
                }
            }
        }
    }

    private static Optional<Point> getIntersection(Point firstStart, Point firstEnd, Point secondStart,
                                                   Point secondEnd) {
        if (firstStart.y == firstEnd.y) {
            return calculateIntersection(firstStart, firstEnd, secondStart, secondEnd);
        }
        return calculateIntersection(secondStart, secondEnd, firstStart, firstEnd);
    }

    private static Optional<Point> calculateIntersection(Point horizontalStart, Point horizontalEnd,
                                                         Point verticalStart,
                                                         Point verticalEnd) {
        return (Math.max(verticalStart.y, verticalEnd.y) > horizontalStart.y &&
                Math.min(verticalStart.y, verticalEnd.y) < horizontalStart.y &&
                Math.max(horizontalStart.x, horizontalEnd.x) > verticalStart.x &&
                Math.min(horizontalStart.x, horizontalEnd.x) < verticalStart.x
               ) ? Optional.of(new Point(verticalStart.x, horizontalStart.y)) : Optional.empty();
    }


    private static void parseInput() {

        Point currentPosition = new Point(0, 0);
        visited.add(currentPosition);
        int currentDirection = 0; // North
        for (String s : input) {
            char dir = s.charAt(0);
            switch (dir) {
                case 'R':
                    currentDirection = (currentDirection == 3) ? 0 : currentDirection + 1;
                    break;
                case 'L':
                    currentDirection = (currentDirection == 0) ? 3 : currentDirection - 1;
                    break;
            }
            int len = Integer.parseInt(s.substring(1));
            Point next = new Point(currentPosition.x, currentPosition.y);
            switch (directions[currentDirection]) {
                case "North":
                    next.x += len;
                    break;
                case "South":
                    next.x -= len;
                    break;
                case "East":
                    next.y += len;
                    break;
                case "West":
                    next.y -= len;
                    break;
            }
            visited.add(next);
            currentPosition = next;
        }
    }
}
