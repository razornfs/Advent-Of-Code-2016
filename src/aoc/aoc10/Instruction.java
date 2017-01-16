package aoc.aoc10;


class Instruction {
    int index;
    int low;
    boolean isLowOutput;
    int high;
    boolean isHighOutput;

    Instruction(int index, int low, boolean isLowOutput, int high, boolean isHighOutput) {
        this.index = index;
        this.low = low;
        this.isLowOutput = isLowOutput;
        this.high = high;
        this.isHighOutput = isHighOutput;
    }
}
