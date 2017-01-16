package aoc.aoc14;

import aoc.Hash;

import java.util.HashMap;
import java.util.Map;

public class AoC14 {

    private static final String ID = "ngcjuoqr";
    private static Map<String, String> map;

    public static void main(String[] args) {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    private static void part1() {
        start(1, 3, 5, 64, 1000);
    }

    private static void part2() {
        start(2017, 3, 5, 64, 1000);
    }

    private static void start(int amount, int repeatedCharsInKey, int repeatedCharsInHash, int keysNeeded,
                              int checkHashesUpTo) {
        map = new HashMap<>();
        int count = 0;
        int iteration = 0;
        while (count != keysNeeded) {
            String hexEncoded = repeatedHash(ID + iteration, amount);
            for (int i = 0; i < hexEncoded.length() - repeatedCharsInKey + 1; i++) {
                if (hasSubsequence(hexEncoded, i, repeatedCharsInKey)) {
                    char c = hexEncoded.charAt(i);
                    for (int j = 1; j <= checkHashesUpTo; j++) {
                        hexEncoded = repeatedHash(ID + (iteration + j), amount);
                        if (hexEncoded.contains(repeatedChar(c, repeatedCharsInHash))) {
                            count++;
                            break;
                        }
                    }
                    break;
                }
            }
            iteration++;
        }
        System.out.println(iteration - 1);
    }

    private static boolean hasSubsequence(String s, int index, int repeated) {
        for (int i = index; i < index + repeated - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static String repeatedChar(char c, int amount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static String repeatedHash(String s, int amount) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        String hash = s;
        for (int i = 0; i < amount; i++) {
            hash = Hash.getMD5(hash);
        }
        map.put(s, hash);
        return hash;
    }
}
