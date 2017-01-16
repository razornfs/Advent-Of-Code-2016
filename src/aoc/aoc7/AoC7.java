package aoc.aoc7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AoC7 {

    private static Scanner scanner;
    private static File file;

    static {
        file = new File("./src/aoc/aoc7/input");
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        int count = 0;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            boolean insideBrackets = false;
            boolean isValidIP = false;
            for (int i = 0; i < str.length() - 3; i++) {
                char c = str.charAt(i);
                if (c == '[') {
                    insideBrackets = true;
                } else if (c == ']') {
                    insideBrackets = false;
                } else if (isABBA(str.substring(i, i + 4))) {
                    if (insideBrackets) {
                        isValidIP = false;
                        break;
                    } else {
                        isValidIP = true;
                    }
                }
            }
            if (isValidIP) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isABBA(String str) {
        if (str.length() != 4) {
            return false;
        }
        return str.charAt(0) == str.charAt(3) && str.charAt(1) == str.charAt(2) && str.charAt(0) != str.charAt(1);
    }

    private static void part2() {
        int count = 0;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            List<String> ABAsOutsideBrackets = new ArrayList<>();
            List<String> BABsInsideBrackets = new ArrayList<>();
            boolean insideBrackets = false;
            boolean isValidIp = false;
            for (int i = 0; i < str.length() - 2; i++) {
                char c = str.charAt(i);
                if (c == '[') {
                    insideBrackets = true;
                } else if (c == ']') {
                    insideBrackets = false;
                } else if (isABA(str.substring(i, i + 3))) {
                    if (insideBrackets) {
                        BABsInsideBrackets.add(str.substring(i, i + 3));
                    } else {
                        ABAsOutsideBrackets.add(str.substring(i, i + 3));
                    }
                }
            }
            boolean areMatching = false;
            for (String aba : ABAsOutsideBrackets) {
                for (String bab : BABsInsideBrackets) {
                    if (areMatching(aba, bab)) {
                        areMatching = true;
                    }
                }
            }
            if (areMatching) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isABA(String str) {
        if (str.length() != 3) {
            return false;
        }
        return str.charAt(0) == str.charAt(2) && str.charAt(0) != str.charAt(1);
    }

    private static boolean areMatching(String aba, String bab) {
        if (aba.length() != 3 || bab.length() != 3) {
            return false;
        }
        return isABA(aba) && isABA(bab) && aba.charAt(0) == bab.charAt(1) && aba.charAt(1) == bab.charAt(0);
    }
}
