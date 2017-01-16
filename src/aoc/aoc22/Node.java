package aoc.aoc22;

import java.awt.*;

class Node {
    private Point location;
    int size;
    int usedSpace;

    Node(Point location, int size, int usedSpace) {
        this.location = location;
        this.size = size;
        this.usedSpace = usedSpace;
    }
}