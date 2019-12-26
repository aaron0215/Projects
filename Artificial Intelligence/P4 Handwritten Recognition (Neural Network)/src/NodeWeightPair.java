/**
 * Class to identify connections
 * between different layers.
 * Do NOT modify.
 */

public class NodeWeightPair {
    public Node node; //The parent node
    public double weight; //Weight of this connection

    //Create an object with a given parent node
    //and connect weight
    NodeWeightPair(Node node, Double weight) {
        this.node = node;
        this.weight = weight;
    }
}