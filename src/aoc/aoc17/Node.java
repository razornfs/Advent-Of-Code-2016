package aoc.aoc17;


import java.awt.*;

class Node {
    Point cords;
    int distance;
    String currPath;

    Node(Point cords, int distance, String currPath) {
        this.cords = cords;
        this.distance = distance;
        this.currPath = currPath;
    }
}