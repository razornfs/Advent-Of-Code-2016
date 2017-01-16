package aoc.aoc8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class AoC8 {

    private static File file;
    private static char[][] display;
    private static int displayWidth = 50;
    private static int displayHeight = 6;

    static {
        file = new File("./src/aoc/aoc8/input");
        display = new char[displayHeight][displayWidth];
        for (int i = 0; i < displayHeight; i++) {
            Arrays.fill(display[i], ' ');
        }
    }

    public static void main(String[] args) {
        start1And2();
        printCount();
    }

    private static void start1And2() {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] input = s.split(" ");
            if ("rect".equals(input[0])) {
                drawRectangle(input[1]);
            } else {
                char xOrY = input[2].charAt(0);
                int which = Integer.parseInt(input[2].substring(2));
                int howMuch = Integer.parseInt(input[4]);
                if (xOrY == 'x') {
                    rotateColumn(which, howMuch);
                } else {
                    rotateRow(which, howMuch);
                }
            }
        }
    }

    private static void drawRectangle(String input) {
        String[] spliced = input.split("x");
        int width = Integer.parseInt(spliced[0]);
        int height = Integer.parseInt(spliced[1]);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                display[i][j] = '#';
                printDisplayAndSleep();
            }
        }
    }

    private static void rotateColumn(int col, int offset) {
        for (int i = 0; i < offset; i++) {
            char temp = display[displayHeight - 1][col];
            for (int j = displayHeight - 1; j > 0; j--) {
                display[j][col] = display[j - 1][col];
            }
            display[0][col] = temp;
            printDisplayAndSleep();
        }
    }

    private static void rotateRow(int row, int offset) {
        for (int i = 0; i < offset; i++) {
            char temp = display[row][displayWidth - 1];
            System.arraycopy(display[row], 0, display[row], 1, displayWidth - 1);
            display[row][0] = temp;
            printDisplayAndSleep();
        }
    }

    private static void printCount() {
        int count = 0;
        for (char[] i : display) {
            for (char j : i) {
                if (j == '#') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void printDisplay() {
        char c = (char) (27);
        System.out.print(c + "[6A");
        for (char[] i : display) {
            for (char j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    private static void printDisplayAndSleep() {
        printDisplay();
        try {
            int sleepTime = 50;
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
