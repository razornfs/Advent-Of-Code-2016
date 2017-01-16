package aoc.aoc13;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AoC13 {

    private static final int input = 1364;
    private static int[] colOffset = {-1, 0, 0, 1};
    private static int[] rowOffset = {0, -1, 1, 0};

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        Point start = new Point(1, 1);
        Point end = new Point(31, 39);
        int minimalDistance = BFSPart1(start, end);
        System.out.println(minimalDistance);
    }

    private static void part2() {
        Point start = new Point(1, 1);
        System.out.println(BFSPart2(start, 50));
    }

    private static boolean isOpen(int x, int y) {
        int formula = x * x + 3 * x + 2 * x * y + y + y * y + input;
        return Integer.bitCount(formula) % 2 == 0;
    }

    private static int BFSPart1(Point start, Point end) {
        if (!isOpen(start.x, start.y) || !isOpen(end.x, end.y)) {
            return -1;
        }

        Set<Point> visited = new HashSet<>();
        visited.add(start);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node currNode = queue.element();
            Point currPoint = currNode.cords;

            if (currPoint.equals(end)) {
                return currNode.distance;
            }
            queue.remove();

            for (int i = 0; i < 4; i++) {
                int row = currPoint.y + rowOffset[i];
                int col = currPoint.x + colOffset[i];
                Point p = new Point(col, row);
                if (isValid(col, row) &&
                    isOpen(col, row) &&
                    !visited.contains(p)) {
                    visited.add(p);
                    Node adjacent = new Node(p, currNode.distance + 1);
                    queue.add(adjacent);
                }
            }
        }
        return -1;
    }

    private static int BFSPart2(Point start, int distance) {
        if (!isOpen(start.x, start.y)) {
            return 0;
        }
        int count = 1;

        Set<Point> visited = new HashSet<>();
        visited.add(start);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            Point currPoint = currNode.cords;
            if (currNode.distance > distance) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int row = currPoint.y + rowOffset[i];
                int col = currPoint.x + colOffset[i];
                Point p = new Point(col, row);
                if (isValid(col, row) &&
                    isOpen(col, row) &&
                    !visited.contains(p)) {
                    visited.add(p);
                    Node adjacent = new Node(p, currNode.distance + 1);
                    queue.add(adjacent);
                    if (adjacent.distance <= distance) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isValid(int col, int row) {
        return row >= 0 && col >= 0;
    }
}
