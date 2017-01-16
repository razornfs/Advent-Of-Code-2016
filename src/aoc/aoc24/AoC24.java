package aoc.aoc24;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AoC24 {

    private static int[] colOffset = {-1, 0, 0, 1};
    private static int[] rowOffset = {0, -1, 1, 0};
    private static List<String> maze;
    private static Set<Integer> valuesToCollect =
            Stream.of(0, 1, 2, 3, 4, 5, 6, 7).collect(Collectors.toCollection(HashSet::new));
    private static int[][] distanceMatrix;
    private static int minDistance = Integer.MAX_VALUE;

    static {
        try {
            maze = Files.readAllLines(Paths.get("./src/aoc/aoc24/input"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        distanceMatrix = new int[8][8];
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
    }

    private static void part1() {
        createGraph();
        permute("1234567");
        System.out.println(minDistance);
    }

    private static void createGraph() {
        for (int i = 0; i < maze.size(); i++) {
            String s = maze.get(i);
            for (int j = 0; j < s.length(); j++) {
                if (valuesToCollect.contains(s.charAt(j) - '0')) {
                    BFS(new Point(j, i));
                }
            }
        }
    }

    private static void BFS(Point start) {

        Set<Integer> gottaCatchEmAll = Stream.of(0, 1, 2, 3, 4, 5, 6, 7).collect(Collectors.toCollection(HashSet::new));
        int startValue = maze.get(start.y).charAt(start.x) - '0';
        gottaCatchEmAll.remove(startValue);

        Set<Point> visited = new HashSet<>();
        visited.add(start);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            Point currPoint = currNode.cords;
            int currValue = maze.get(currPoint.y).charAt(currPoint.x) - '0';
            if (gottaCatchEmAll.remove(currValue)) {
                distanceMatrix[currValue][startValue] = currNode.distance;
                if (gottaCatchEmAll.size() == 0) {
                    return;
                }
            }

            for (int i = 0; i < 4; i++) {
                int row = currPoint.y + rowOffset[i];
                int col = currPoint.x + colOffset[i];
                Point p = new Point(col, row);
                if (isOpen(col, row) &&
                    !visited.contains(p)) {
                    visited.add(p);
                    Node adjacent = new Node(p, currNode.distance + 1);
                    queue.add(adjacent);
                }
            }
        }
    }

    private static boolean isOpen(int col, int row) {
        return col >= 0 && col < maze.get(0).length() &&
               row >= 0 && row < maze.size() &&
               (maze.get(row).charAt(col) == '.' ||
                valuesToCollect.contains(maze.get(row).charAt(col) - '0')
               );
    }

    private static void permute(String str) {
        permute("", str);
    }

    private static void permute(String prefix, String str) {
        int len = str.length();
        if (len == 0) {
            // for part 2 change ("0" + prefix) to ("0" + prefix + "0")
            calculateDistance("0" + prefix);
        } else {
            for (int i = 0; i < len; i++) {
                permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, len));
            }
        }
    }

    private static void calculateDistance(String permutation) {
        int distance = 0;
        for (int i = 0; i < permutation.length() - 1; i++) {
            int a = permutation.charAt(i) - '0';
            int b = permutation.charAt(i + 1) - '0';
            distance += distanceMatrix[a][b];
        }
        if (distance < minDistance) {
            minDistance = distance;
        }
    }
}
