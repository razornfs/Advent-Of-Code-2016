package aoc.aoc22;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AoC22 {


    private static File file = new File("./src/aoc/aoc22/input");
    private static List<Node> nodes = new ArrayList<>();

    static {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            nodes.add(parseNode(scanner.nextLine()));
        }
    }

    private static Node parseNode(String node) {
        String[] input = node.split("\\s+");
        String[] dev = input[0].split("-");
        Point p = new Point(Integer.parseInt(dev[1].substring(1)),
                            Integer.parseInt(dev[2].substring(1)));
        int size = Integer.parseInt(input[1].substring(0, input[1].length() - 1));
        int usedSpace = Integer.parseInt(input[2].substring(0, input[2].length() - 1));
        return new Node(p, size, usedSpace);
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        // Part 2 done by hand
    }

    private static void part1() {
        int count = 0;
        for (Node nodeI : nodes) {
            for (Node nodeJ : nodes) {
                if (areValid(nodeI, nodeJ)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean areValid(Node a, Node b) {
        return a != b && a.usedSpace != 0 && a.usedSpace <= (b.size - b.usedSpace);
    }
}
