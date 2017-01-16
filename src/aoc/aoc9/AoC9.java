package aoc.aoc9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC9 {

    private static File file;

    static {
        file = new File("./src/aoc/aoc9/input");
    }

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        long ret = 0;
        while (scanner.hasNextLine()) {
            String cur = scanner.nextLine();
            ret += decompress(cur, 1);
        }
        System.out.println(ret);
    }

    private static long decompress(String cur, long currLength) {
        int i = 0;
        long ret = 0;
        while (i < cur.length()) {
            char c = cur.charAt(i);
            if (c == '(') {
                String lengthOfRepeatedSequence = "";
                c = cur.charAt(++i);
                while (c != 'x') {
                    lengthOfRepeatedSequence += c;
                    c = cur.charAt(++i);
                }
                int length = Integer.parseInt(lengthOfRepeatedSequence);
                c = cur.charAt(++i);
                String howManyTimesToRepeat = "";
                while (c != ')') {
                    howManyTimesToRepeat += c;
                    c = cur.charAt(++i);
                }
                int howMany = Integer.parseInt(howManyTimesToRepeat);
                i++;
                ret += decompress(cur.substring(i, i + length), currLength * howMany);
                i += length;
            } else {
                ret += currLength;
                i++;
            }
        }
        return ret;
    }
}
