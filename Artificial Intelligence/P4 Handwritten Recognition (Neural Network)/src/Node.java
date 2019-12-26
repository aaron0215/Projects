import java.util.*;

/**
 * Class for internal organization of a Neural Network. There are 5 types of nodes. Check the type
 * attribute of the node for details. Feel free to modify the provided function signatures to fit
 * your own implementation
 */

public class Node {
  private int type = 0; // 0=input,1=biasToHidden,2=hidden,3=biasToOutput,4=Output
  public ArrayList<NodeWeightPair> parents = null; // Array List that will contain the parents
                                                   // (including the bias node) with weights if
                                                   // applicable

  private double inputValue = 0.0;
  private double outputValue = 0.0;
  private double outputGradient = 0.0; // TODO
  private double delta = 0.0; // input gradient

  // Create a node with a specific type
  Node(int type) {
    if (type > 4 || type < 0) {
      System.out.println("Incorrect value for node type");
      System.exit(1);

    } else {
      this.type = type;
    }

    if (type == 2 || type == 4) {
      parents = new ArrayList<>();
    }
  }

  // For an input node sets the input value which will be the value of a particular attribute
  public void setInput(double inputValue) {
    if (type == 0) { // If input node
      this.inputValue = inputValue;
    }
  }

  public void setInput() { // Hidden and output layers need weighted input
    if (type == 2 || type == 4) {
      this.inputValue = 0.0;
      for (NodeWeightPair nw : parents) {
        this.inputValue += nw.weight * nw.node.getOutput();
      }
    }
  }

  /**
   * Calculate the output of a node. You can get this value by using getOutput()
   */
  public void calculateOutput(ArrayList<Node> outputNodes) {
    if (type == 2 || type == 4) { // Not an input or bias node
      // TODO: add code here
      switch (type) {
        case 2: // ReLU
          this.setInput();
          outputValue = Math.max(0, this.inputValue);
          break;
        case 4: // Softmax
          double denominator = 0.0;
          double numerator = Math.exp(this.inputValue);
          for (Node n : outputNodes)
            denominator += Math.exp(n.inputValue);
          // System.out.println("Denominator: " + denominator);
          outputValue = numerator / denominator;
          break;
      }
    }
  }

  // Gets the output value
  public double getOutput() {

    if (type == 0) { // Input node
      return this.inputValue;
    } else if (type == 1 || type == 3) { // Bias node
      return 1.00;
    } else {
      return outputValue;
    }

  }

  // Calculate the delta value of a node.
  public void calculateDelta(ArrayList<Node> outputNodes, double expectedOutput, int index) {
    if (type == 2 || type == 4) {
      // TODO: add code here
      switch (type) {
        case 2: // hidden unit
          double reluDerivative;
          double weightedOutput = 0.0;
          if (this.inputValue <= 0)
            reluDerivative = 0;
          else
            reluDerivative = 1;
          for (Node n : outputNodes)
            weightedOutput += n.parents.get(index).weight * n.delta; // TODO Need to fix: parents
                                                                     // always == 0
          delta = reluDerivative * weightedOutput;
          break;
        case 4: // output unit
          delta = expectedOutput - outputValue;
          break;
      }
    }
    // System.out.println("Delta: " + delta);
  }

  // Update the weights between parents node and current node
  public void updateWeight(double learningRate) {
    if (type == 2 || type == 4) {
      // TODO: add code here
      for (int i = 0; i < parents.size(); i++)
        parents.get(i).weight += learningRate * parents.get(i).node.getOutput() * delta;
    }
    // TODO Needs to be fixed: parents = 0;
  }

  // Get output values from other nodes
  // public void setDenominator(double denominator) {
  // this.denominator = denominator;
  // }

  // public void setExpectedOutput(double output) {
  // this.expectedOutput = output;
  // }
}


