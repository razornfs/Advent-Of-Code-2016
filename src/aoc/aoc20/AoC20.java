package aoc.aoc20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AoC20 {

    private static List<Interval> intervals = new ArrayList<>();
    private static Scanner scanner;

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        getInput();
        parseInput();
        sortIntervals();
        mergeIntervals();
        printResults();
    }

    private static void getInput() {
        File input = new File("./src/aoc/aoc20/input");
        try {
            scanner = new Scanner(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    private static void parseInput() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] in = line.split("-");
            intervals.add(new Interval(Long.parseLong(in[0]), Long.parseLong(in[1])));
        }
    }

    private static void sortIntervals() {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.left == o2.left) {
                    return Long.compare(o1.right, o2.right);
                } else {
                    return Long.compare(o1.left, o2.left);
                }
            }
        });
    }

    private static void mergeIntervals() {
        List<Interval> merged = new ArrayList<>();
        Interval prev = intervals.get(0);
        for (Interval next : intervals) {
            if (next.left - 1 > prev.right) {
                merged.add(prev);
                prev = next;
            } else {
                prev = new Interval(prev.left, Math.max(prev.right, next.right));
            }
        }
        merged.add(prev);
        intervals = merged;
    }

    private static void printResults() {
        System.out.println("Part 1: " + intervals.get(0).right + 1);
        long allowed = 0;
        for (int i = 1; i < intervals.size(); i++) {
            allowed += (intervals.get(i).left - intervals.get(i - 1).right - 1);
        }
        System.out.println("Part 2: " + allowed);
    }
}
