import java.util.ArrayList;
import java.util.List;

/**
 * Fill in the implementation details of the class DecisionTree using this file. Any methods or
 * secondary classes that you want are fine but we will only interact with those methods in the
 * DecisionTree framework.
 */
public class DecisionTreeImpl {
  public DecTreeNode root;
  public List<List<Integer>> trainData;
  public int maxPerLeaf;
  public int maxDepth;
  public int numAttr;
  
  private int identicalLabel = -1;

  // Build a decision tree given a training set
  DecisionTreeImpl(List<List<Integer>> trainDataSet, int mPerLeaf, int mDepth) {
    this.trainData = trainDataSet;
    this.maxPerLeaf = mPerLeaf;
    this.maxDepth = mDepth;
    if (this.trainData.size() > 0) this.numAttr = trainDataSet.get(0).size() - 1;
    this.root = buildTree(trainDataSet, 0);
  }

  private DecTreeNode buildTree(List<List<Integer>> Instances, int depth) {
    if(Instances.isEmpty()) return new DecTreeNode(1,-1,-1);
    if(allSameLabels(Instances)) return new DecTreeNode(identicalLabel,-1,-1);
    double[] bestList = bestAttribute(Instances);
    int bestAttr = (int) bestList[0]; // get best attribute and threshold here
    int bestThreshold = (int) bestList[1];
    double maxInfoGain = bestList[2];
//    System.out.println(bestAttr + " = " + bestThreshold + " : " + maxInfoGain + ", Depth: " + depth);
    if(Instances.size() <= maxPerLeaf || depth >= maxDepth || maxInfoGain == 0) { // Leaf node condition
//      System.out.println(bestAttr + " = " + bestThreshold + " : " + maxInfoGain);
      return new DecTreeNode(majorLabel(Instances),-1,-1);
    }
    List<List<Integer>> leftExamples = new ArrayList<List<Integer>>();
    List<List<Integer>> rightExamples = new ArrayList<List<Integer>>();
    for (int i = 0; i < Instances.size(); i++) {
//      System.out.println(bestAttr);
//      System.out.println("Instance: " + Instances.get(i).get(bestAttr) + " Threshold: " + bestThreshold);
      if(Instances.get(i).get(bestAttr) <= bestThreshold) {
        leftExamples.add(Instances.get(i));
      } else {
        rightExamples.add(Instances.get(i));
      }
    }
    DecTreeNode tree = new DecTreeNode(majorLabel(Instances),bestAttr,bestThreshold); // Non leaf
    tree.left = buildTree(leftExamples, depth + 1);
    tree.right = buildTree(rightExamples, depth + 1);
    return tree;
  }

  // Maximize information gain
  // Return the index of best attribute column (X_0 to X_8 attributes
  private double[] bestAttribute (List<List<Integer>> Instances) {
    double maxInfoGain = 0;
    int bestThreshold = 0;
    int bestAttr = -1;
    double entropy = getEntropy(Instances);
    for (int a = 0; a < numAttr; a++) { // Attributes
      for (int t = 1; t <= 9; t++) { // Thresholds
        double infoGain = entropy - getConEntropy(Instances,a,t);
//        System.out.println("Attribute: " + a);
//        System.out.println("Threshold: " + t);
//        System.out.println("Entropy: " + entropy);
//        System.out.println("C entropy: " + getConEntropy(Instances,a,t));
        if(infoGain > maxInfoGain) {
          maxInfoGain = infoGain;
          bestAttr = a;
          bestThreshold = t;
        }
      }
    }
    return new double[]{bestAttr,bestThreshold,maxInfoGain};
  }

  // Class Entropy H(Y)
  private double getEntropy(List<List<Integer>> Instances) {
    int[] labelCount = new int[] {0, 0};
    for (List<Integer> label : Instances) {
      labelCount[label.get(numAttr)]++;
    }
    double entropy = 0.0;
    for (int numLabel : labelCount) {
      double prob = 1.0 * numLabel / Instances.size();
      entropy += -prob * (Math.log(prob) / Math.log(2));
    }
    return entropy;
  }
  
  //Conditional entropy H(Y|X)
  private double getConEntropy(List<List<Integer>> Instances, int attrIndex, int threshold) { // Given a column and threshold
    double conEntropy = 0;
    int numInstance = Instances.size(); // number of instance
    int [][] count = new int[2][2]; //[label][less/more than threshold (0/1)]
    for(int i = 0; i < numInstance; i++) { // Loop through instances
      if(Instances.get(i).get(attrIndex) <= threshold)
        count[Instances.get(i).get(numAttr)][0]++;
      else
        count[Instances.get(i).get(numAttr)][1]++;
    }
    int count_0_less = count[0][0];
    int count_0_more = count[0][1];
    int count_1_less = count[1][0];
    int count_1_more = count[1][1];
    double probLess = 1.0 * (count_0_less + count_1_less) / numInstance;
    double probMore = 1.0 * (count_0_more + count_1_more) / numInstance;
    double prob_0_less = 1.0 * (count_0_less) / probLess;
    double prob_1_less = 1.0 * (count_1_less) / probLess;
    double prob_0_more = 1.0 * (count_0_more) / probMore;
    double prob_1_more = 1.0 * (count_1_more) / probMore;
    double prob_0_less_log = 0.0;
    double prob_1_less_log = 0.0;
    double prob_0_more_log = 0.0;
    double prob_1_more_log = 0.0;
    if(prob_0_less != 0.0) {
      prob_0_less_log = Math.log(prob_0_less) / Math.log(2);
    }
    if(prob_1_less != 0.0) {
      prob_1_less_log = Math.log(prob_1_less) / Math.log(2);
    }
    if(prob_0_more != 0.0) {
      prob_0_more_log = Math.log(prob_0_more) / Math.log(2);
    }
    if(prob_1_more != 0.0) {
      prob_1_more_log = Math.log(prob_1_more) / Math.log(2);
    }
//    conEntropy = -(probLess * prob_0_less*(Math.log(prob_0_less) / Math.log(2) + prob_1_less*Math.log(prob_1_less) / Math.log(2))
//        + probMore * (prob_0_more *Math.log(prob_0_more) / Math.log(2) + prob_1_more*Math.log(prob_1_more) / Math.log(2)));
    conEntropy = -(probLess * (prob_0_less*prob_0_less_log + prob_1_less*prob_1_less_log)
        + probMore * (prob_0_more *prob_0_more_log + prob_1_more*prob_1_more_log));
    return conEntropy;
  }
  
  private boolean allSameLabels(List<List<Integer>> Instances) {
    identicalLabel = -1;
    int firstLabel = Instances.get(0).get(numAttr);
    for(int i = 1; i < Instances.size(); i++) { // Check all labels
      if(Instances.get(i).get(numAttr) == firstLabel)
        continue;
      else
        return false;
    }
    identicalLabel = firstLabel;
    return true;
  }
  
  private int majorLabel(List<List<Integer>> Instances) {
    int [] numLabels = new int[2];
    for(List<Integer> instance : Instances) {
      numLabels[instance.get(numAttr)]++;
    }
    return (numLabels[0] > numLabels[1] ? 0 : 1);
  }

  public int classify(List<Integer> instance) {
    // Note that the last element of the array is the label.
    DecTreeNode curr = this.root;
    while(!curr.isLeaf()) {
      int attrIndex = curr.attribute; // Find which attribute we are going to compare
      int attrValue = instance.get(attrIndex); // Find the attribute value in given instance
      if (attrValue <= curr.threshold) { // Go through the tree
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }
    return curr.classLabel;
  }

  // Print the decision tree in the specified format
  public void printTree() {
    printTreeNode("", this.root);
  }

  public void printTreeNode(String prefixStr, DecTreeNode node) {
    String printStr = prefixStr + "X_" + node.attribute;
    System.out.print(printStr + " <= " + String.format("%d", node.threshold));
    if (node.left.isLeaf()) {
      System.out.println(" : " + String.valueOf(node.left.classLabel));
    } else {
      System.out.println();
      printTreeNode(prefixStr + "|\t", node.left);
    }
    System.out.print(printStr + " > " + String.format("%d", node.threshold));
    if (node.right.isLeaf()) {
      System.out.println(" : " + String.valueOf(node.right.classLabel));
    } else {
      System.out.println();
      printTreeNode(prefixStr + "|\t", node.right);
    }
  }

  public double printTest(List<List<Integer>> testDataSet) {
    int numEqual = 0;
    int numTotal = 0;
    for (int i = 0; i < testDataSet.size(); i++) {
      int prediction = classify(testDataSet.get(i));
      int groundTruth = testDataSet.get(i).get(testDataSet.get(i).size() - 1);
      System.out.println(prediction);
      if (groundTruth == prediction) {
        numEqual++;
      }
      numTotal++;
    }
    double accuracy = numEqual * 100.0 / (double) numTotal;
    System.out.println(String.format("%.2f", accuracy) + "%");
    return accuracy;
  }
}
