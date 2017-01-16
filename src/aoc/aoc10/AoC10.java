package aoc.aoc10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class AoC10 {

    private static File file;
    private static Map<Integer, List<Integer>> bots; // maps indexes to values that the bot holds
    private static Map<Integer, List<Integer>> outputs;
    private static List<Instruction> instructions;

    static {
        file = new File("./src/aoc/aoc10/input");
        bots = new HashMap<>();
        outputs = new HashMap<>();
        instructions = new ArrayList<>();
    }

    public static void main(String[] args) {
        start1And2();
    }

    private static void start1And2() {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            if ("bot".equals(input[0])) {
                Instruction instruction = parseInstruction(input);
                int bot = instruction.index;
                instructions.add(instruction);
                if (bots.containsKey(bot)) {
                    List<Integer> values = bots.get(bot);
                    if (values.size() == 2) {
                        executeInstructions(bot, values);
                    }
                } else {
                    bots.put(bot, new ArrayList<>());
                }
            } else {
                int bot = Integer.parseInt(input[5]);
                int value = Integer.parseInt(input[1]);
                giveValue(bot, value, false);
            }
        }
        System.out.println("Part 2: " + outputs.get(0).get(0) * outputs.get(1).get(0) * outputs.get(2).get(0));
    }

    private static Instruction parseInstruction(String[] input) {
        boolean isLowOutput = ("output".equals(input[5]));
        boolean isHighOutput = ("output".equals(input[10]));
        int bot = Integer.parseInt(input[1]);
        int low = Integer.parseInt(input[6]);
        int high = Integer.parseInt(input[11]);
        return new Instruction(bot, low, isLowOutput, high, isHighOutput);
    }

    private static void giveValue(int index, int value, boolean isOutput) {
        if (isOutput) {
            giveToOutput(index, value);
        } else {
            giveToBot(index, value);
        }
    }

    private static void giveToOutput(int output, int value) {
        if (outputs.containsKey(output)) {
            outputs.get(output).add(value);
        } else {
            outputs.put(output, new ArrayList<>(Collections.singletonList(value)));
        }
    }

    private static void giveToBot(int bot, int value) {
        if (bots.containsKey(bot)) {
            List<Integer> values = bots.get(bot);
            values.add(value);
            if (values.size() == 2) {
                executeInstructions(bot, values);
            }
        } else {
            bots.put(bot, new ArrayList<>(Collections.singletonList(value)));
        }
    }

    private static void executeInstructions(int bot, List<Integer> values) {
        int low = Math.min(values.get(0), values.get(1));
        int high = Math.max(values.get(0), values.get(1));
        if (low == 17 && high == 61) {
            System.out.println("Part 1: " + bot);
        }
        List<Instruction> instructionsToExecute = new ArrayList<>();
        for (Iterator<Instruction> it = instructions.iterator(); it.hasNext(); ) {
            Instruction instruction = it.next();
            if (bot == instruction.index) {
                it.remove();
                bots.put(bot, new ArrayList<>());
                instructionsToExecute.add(instruction);
            }
        }
        for (Instruction instruction : instructionsToExecute) {
            giveValue(instruction.low, low, instruction.isLowOutput);
            giveValue(instruction.high, high, instruction.isHighOutput);
        }
    }
}