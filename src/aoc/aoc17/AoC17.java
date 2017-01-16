package aoc.aoc17;

import aoc.Hash;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class AoC17 {

    private static final String input = "vkjiggvb";
    private static final int height = 4;
    private static final int width = 4;
    private static int[] colOffset = {0, 0, -1, 1};
    private static int[] rowOffset = {-1, 1, 0, 0};
    private static char[] directions = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Point start = new Point(0, 0);
        Point destination = new Point(3, 3);
        BFS(start, destination);
    }

    private static void BFS(Point start, Point destination) {

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0, ""));
        int max = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            Point currPoint = currNode.cords;
            String currPath = currNode.currPath;
            if (currPoint.equals(destination)) {
                if (max == Integer.MIN_VALUE) {
                    System.out.println("Part 1: " + currNode.currPath);
                }
                if (currNode.currPath.length() > max) {
                    max = currNode.currPath.length();
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int row = currPoint.y + rowOffset[i];
                    int col = currPoint.x + colOffset[i];
                    Point p = new Point(col, row);
                    if (isValid(col, row, currPath, i)) {
                        Node adjacent =
                                new Node(p, currNode.distance + 1, currPath + directions[i]);
                        queue.add(adjacent);
                    }
                }
            }
        }
        System.out.println("Part 2: " + max);
    }

    private static boolean isValid(int col, int row, String currPath, int i) {
        return row >= 0 && row < height &&
               col >= 0 && col < width &&
               isOpen(Hash.getMD5(input + currPath).charAt(i));
    }

    private static boolean isOpen(char c) {
        return c >= 'b' && c <= 'f';
    }
}
