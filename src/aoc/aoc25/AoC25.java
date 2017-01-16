package aoc.aoc25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AoC25 {

    private static Scanner scanner;
    private static List<String[]> input;

    static {
        File file = new File("./src/aoc/aoc25/input");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine().split(" "));
        }
    }

    public static void main(String[] args) {
        start1();
    }

    private static void start1() {
        outer:
        for (int k = 0; k < Integer.MAX_VALUE; k++) {
            int[] registers = {k, 0, 0, 0};
            String output = "";
            int i = 0;
            while (i < input.size()) {
                String[] s = input.get(i);
                String command = s[0];
                switch (command) {
                    case "cpy": {
                        int register = getRegister(s[2]);
                        if (register != -1) {
                            registers[register] = getValue(s[1], registers);
                        }
                        i++;
                        break;
                    }
                    case "inc": {
                        int register = getRegister(s[1]);
                        if (register != -1) {
                            registers[register]++;
                        }
                        i++;
                        break;
                    }
                    case "dec": {
                        int register = getRegister(s[1]);
                        if (register != -1) {
                            registers[register]--;
                        }
                        i++;
                        break;
                    }
                    case "jnz": {
                        i += (getValue(s[1], registers) == 0) ? 1 : getValue(s[2], registers);
                        break;
                    }
                    case "tgl": {
                        toggle(getValue(s[1], registers) + i);
                        i++;
                        break;
                    }
                    case "out": {
                        output += getValue(s[1], registers);
                        i++;
                        break;
                    }
                }
                // TODO: solve the halting problem
                if (output.length() ==
                    "010101010101010101"
                            .length()) {
                    if (output
                            .equals("010101010101010101")) {
                        System.out.println(k);
                        break outer;
                    }
                    break;
                }
            }
        }
    }

    private static int getValue(String source, int[] registers) {
        if (source.charAt(0) >= 'a' && source.charAt(0) <= 'd') {
            return registers[source.charAt(0) - 'a'];
        }
        return Integer.parseInt(source);
    }

    private static int getRegister(String s) {
        int register = s.charAt(0) - 'a';
        if (register >= 0 && register < 4) {
            return register;
        }
        return -1;
    }

    private static void toggle(int which) {
        if (which >= 0 && which < input.size()) {
            String[] curr = input.get(which);
            String command = curr[0];
            switch (curr.length) {
                case 2:
                    if (command.equals("inc")) {
                        curr[0] = "dec";
                    } else {
                        curr[0] = "inc";
                    }
                    break;
                case 3:
                    if (command.equals("jnz")) {
                        curr[0] = "cpy";
                    } else {
                        curr[0] = "jnz";
                    }
            }
        }
    }
}
