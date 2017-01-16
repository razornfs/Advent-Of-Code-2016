package aoc.aoc4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class AoC4 {
    private static List<List<String>> input = new ArrayList<>();

    static {
        List<String> temp;
        try {
            temp = Files.readAllLines(Paths.get("./src/aoc/aoc4/input"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parseInput(temp);
    }

    private static void parseInput(List<String> in) {
        IntStream.range(0, in.size())
                 .forEach(index -> input.add(join(in.get(index)
                                                    .split("[|]|-"))
                          )
                 );
    }

    // joins first arr.length - 2 elements, e.g. ["a", "b", "c", 123, "abcd"] becomes ["abc", 123, "abcd"]
    // assumes input is valid
    private static List<String> join(String[] arr) {
        List<String> ret = new ArrayList<>();

        List<String> list = Arrays.asList(arr);
        list = list.subList(0, list.size() - 2);

        ret.add(String.join("", list));
        ret.add(arr[arr.length - 2]);
        ret.add(arr[arr.length - 1]);
        return ret;
    }

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        int validIdCount = 0;
        for (List<String> list : input) {
            if (isValid(list.get(0), list.get(2))) {
                validIdCount += Integer.parseInt(list.get(1));
            }
        }
        System.out.println(validIdCount);
    }

    private static boolean isValid(String name, String checksum) {
        int[] charCount = countOccurrences(name);
        for (int i = 0; i < checksum.length(); i++) {
            if (!isMax(checksum.charAt(i), charCount)) {
                return false;
            }
        }
        return true;
    }

    private static int[] countOccurrences(String name) {
        int[] charCount = new int[26];
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c >= 'a' && c <= 'z') {
                charCount[c - 'a']++;
            } else {
                throw new IllegalArgumentException(
                        "The name is supposed to contain only lower-case english characters");
            }
        }
        return charCount;
    }

    private static boolean isMax(char c, int[] charCount) {
        OptionalInt max = Arrays.stream(charCount).max();
        if (max.isPresent()) {
            int actualMax = max.getAsInt();
            int potentialMax = charCount[c - 'a'];
            charCount[c - 'a'] = 0;
            return actualMax == potentialMax;
        }
        return false;
    }

    private static void part2() {
        for (List<String> list : input) {
            int ID = Integer.parseInt(list.get(1));
            if (decrypted(list.get(0), ID).contains("north")) {
                System.out.println(ID);
                break;
            }
        }
    }

    private static String decrypted(String name, int ID) {
        String decryptedName = "";
        for (int i = 0; i < name.length(); i++) {
            decryptedName += (char) ((name.charAt(i) - 'a' + ID) % 26 + 'a');
        }
        return decryptedName;
    }
}
