package aoc.aoc5;

import aoc.Hash;

import java.util.Random;

public class AoC5 {

    private static String encoded1 = "";
    private static char[] encoded2 = new char[8];
    private static Random random = new Random();

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        String ID = "abbhdwsy";
        long count = 0;
        int char1Count = 0;
        int char2Count = 0;
        printWelcomingMessage(ID);
        while (char1Count < 8 || char2Count < 8) {
            String hexEncoded = Hash.getMD5(ID + count);
            if (hexEncoded.substring(0, 5).equals("00000")) {
                if (char1Count < 8) {
                    char1Count++;
                    encoded1 += hexEncoded.charAt(5);
                }
                int loc = hexEncoded.charAt(5) - '0';
                if (loc >= 0 && loc < 8 && encoded2[loc] == '\u0000') {
                    encoded2[loc] = hexEncoded.charAt(6);
                    char2Count++;
                }
            }
            if (count % 10000 == 0) {
                System.out.print(replaceEmptyCharsWithRandomChars(encoded1) + " - " +
                                 replaceEmptyCharsWithRandomChars(encoded2));
            }
            count++;
        }
        printExitingMessage();
    }

    private static void printWelcomingMessage(String ID) {
        printWithDelay("Initializing hacking for door ID " + ID, 70);
        printWithDelay("...", 1000);
        System.out.println();
    }

    private static void printExitingMessage() {
        System.out.print('\r');
        System.out.print(encoded1 + " - ");
        System.out.println(encoded2);
        printWithDelay("Hacking completed", 70);
        System.out.println();
        printWithDelay("", 1000);
        printWithDelay("HACKERMAN out", 70);
        printWithDelay("", 1000);
    }

    private static void printWithDelay(String message, int delay) {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) { // should never happen
                throw new RuntimeException(e);
            }
        }
    }

    private static String replaceEmptyCharsWithRandomChars(String str) {
        int len = str.length();
        for (int i = 0; i < 8 - len; i++) {
            str += getRandomChar(32, 128);
        }
        return str;
    }

    private static String replaceEmptyCharsWithRandomChars(char[] str) {
        String ret = "";
        for (char c : str) {
            if (c == '\u0000') {
                ret += getRandomChar(32, 128);
            } else {
                ret += c;
            }
        }
        return ret;
    }

    private static char getRandomChar(int left, int right) {
        return (char) (random.nextInt(right - left) + left);
    }

}
