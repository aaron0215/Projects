import java.util.*;

/**
 * The main class that handles the entire network Has multiple attributes each with its own use
 */

public class NNImpl {
  private ArrayList<Node> inputNodes; // list of the output layer nodes.
  private ArrayList<Node> hiddenNodes; // list of the hidden layer nodes
  private ArrayList<Node> outputNodes; // list of the output layer nodes

  private ArrayList<Instance> trainingSet; // the training set

  private double learningRate; // variable to store the learning rate
  private int maxEpoch; // variable to store the maximum number of epochs
  private Random random; // random number generator to shuffle the training set

  /**
   * This constructor creates the nodes necessary for the neural network Also connects the nodes of
   * different layers After calling the constructor the last node of both inputNodes and hiddenNodes
   * will be bias nodes.
   */

  NNImpl(ArrayList<Instance> trainingSet, int hiddenNodeCount, Double learningRate, int maxEpoch,
      Random random, Double[][] hiddenWeights, Double[][] outputWeights) {
    this.trainingSet = trainingSet;
    this.learningRate = learningRate;
    this.maxEpoch = maxEpoch;
    this.random = random;

    // input layer nodes
    inputNodes = new ArrayList<>();
    int inputNodeCount = trainingSet.get(0).attributes.size();
    int outputNodeCount = trainingSet.get(0).classValues.size();
    for (int i = 0; i < inputNodeCount; i++) {
      Node node = new Node(0);
      inputNodes.add(node);
    }

    // bias node from input layer to hidden
    Node biasToHidden = new Node(1);
    inputNodes.add(biasToHidden);

    // hidden layer nodes
    hiddenNodes = new ArrayList<>();
    for (int i = 0; i < hiddenNodeCount; i++) {
      Node node = new Node(2);
      // Connecting hidden layer nodes with input layer nodes
      for (int j = 0; j < inputNodes.size(); j++) {
        NodeWeightPair nwp = new NodeWeightPair(inputNodes.get(j), hiddenWeights[i][j]);
        node.parents.add(nwp);
      }
      hiddenNodes.add(node);
    }

    // bias node from hidden layer to output
    Node biasToOutput = new Node(3);
    hiddenNodes.add(biasToOutput);

    // Output node layer
    outputNodes = new ArrayList<>();
    for (int i = 0; i < outputNodeCount; i++) {
      Node node = new Node(4);
      // Connecting output layer nodes with hidden layer nodes
      for (int j = 0; j < hiddenNodes.size(); j++) {
        NodeWeightPair nwp = new NodeWeightPair(hiddenNodes.get(j), outputWeights[i][j]);
        node.parents.add(nwp);
      }
      outputNodes.add(node);
    }

  }

  // Set up nodes with different types accordingly
  public void setUpNodes(Instance instance) {
    for (int i = 0; i < inputNodes.size() - 1; i++) {
      inputNodes.get(i).setInput(instance.attributes.get(i));
      inputNodes.get(i).calculateOutput(null);
    }

    for (int i = 0; i < hiddenNodes.size(); i++) {
      hiddenNodes.get(i).setInput();
      hiddenNodes.get(i).calculateOutput(null);
    }

    for (int i = 0; i < outputNodes.size(); i++) {
      outputNodes.get(i).setInput();
    }
    for (int i = 0; i < outputNodes.size(); i++) {
      outputNodes.get(i).calculateOutput(outputNodes);
    }
  }

  /**
   * Get the prediction from the neural network for a single instance Return the idx with highest
   * output values. For example if the outputs of the outputNodes are [0.1, 0.5, 0.2], it should
   * return 1. The parameter is a single instance
   */

  public int predict(Instance instance) {
    // TODO: add code here
    this.setUpNodes(instance);
    int retIndex = 0;
    double maxValue = outputNodes.get(0).getOutput();
    for (int i = 1; i < outputNodes.size(); i++) {
      if (outputNodes.get(i).getOutput() > maxValue) {
        maxValue = outputNodes.get(i).getOutput();
        retIndex = i;
      }
    }
    return retIndex;
  }


  /**
   * Train the neural networks with the given parameters
   * <p>
   * The parameters are stored as attributes of this class
   */

  public void train() {
    // TODO: add code here
    for (int epoch = 0; epoch < maxEpoch; epoch++) {
      double error = 0.0;
      Collections.shuffle(trainingSet, random);
      for (Instance instance : trainingSet) { //Calculate and update weights backward
        this.setUpNodes(instance);

        for (int index = 0; index < outputNodes.size(); index++)
          outputNodes.get(index).calculateDelta(outputNodes, instance.classValues.get(index), index);

        for (int index = 0; index < hiddenNodes.size(); index++)
          hiddenNodes.get(index).calculateDelta(outputNodes, -1, index);

        for (Node n : outputNodes)
          n.updateWeight(learningRate);

        for (Node n : hiddenNodes)
          n.updateWeight(learningRate);

//        error += this.loss(instance); //TODO not correct here
      }
      for (Instance instance : trainingSet) {
        error += this.loss(instance);
      }
      error = error / trainingSet.size();
      System.out.format("Epoch: " + epoch + ", Loss: %.3e\n", error);
    }
  }

  /**
   * Calculate the cross entropy loss from the neural network for a single instance. The parameter
   * is a single instance
   */
  private double loss(Instance instance) {
    // TODO: add code here
    this.setUpNodes(instance);
    double entropy = 0.0;
    for (int i = 0; i < outputNodes.size(); i++) {
      entropy += instance.classValues.get(i) * Math.log(outputNodes.get(i).getOutput());
      // System.out.println(outputNodes.get(i).getOutput()); //TODO there is log 0
    }
    return -entropy;
  }

  // NOTE: Removed. Change arguments as needed in Node
  // public void calculateDenominator(Node node) {
  // double denominator = 0.0;
  // double temp = 0.0;
  // for(Node n : outputNodes) {
  // for(NodeWeightPair nw : n.parents) {
  // temp += nw.node.getOutput();
  // }
  // denominator += Math.exp(temp);
  // }
  // }

}
