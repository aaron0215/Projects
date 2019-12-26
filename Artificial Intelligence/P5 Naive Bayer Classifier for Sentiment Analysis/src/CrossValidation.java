import java.util.*;

public class CrossValidation {
  /*
   * Returns the k-fold cross validation score of classifier clf on training data.
   */
  public static double kFoldScore(Classifier clf, List<Instance> trainData, int k, int v) {
    List<Instance> data = trainData;
    int n = data.size();
//    double dk = (double)k;
    if (n == 0 || k < 2 || k > n) {
      return 0.0;
    }
    int mistakes = 0;
//  Collections.shuffle(data);
    for (int fold = 0; fold < k; fold++) {
      List<Instance> testSet = new ArrayList<Instance>();
      List<Instance> trainSet = new ArrayList<Instance>();
      // Split data into training and testing set accordingly. # of fold =  i/(n/k)
      for (int i = 0; i < n; i++) {
//        if(i == fold*Math.round((size/k)))
        if (Math.round(i / (n / k)) == fold)
          testSet.add(data.get(i));
        else
          trainSet.add(data.get(i));
      }

      clf.train(trainSet, v);

      // Count mistakes
      for (Instance instance : testSet) {
        ClassifyResult result = clf.classify(instance.words);
        if(result.label != instance.label)
          mistakes++;
      }
    }
    // From piazza: Score = 1 - (total number of mistakes in all folds) / (the original (large) train set size).
    return 1.0-(double)mistakes/(double)trainData.size();
  }
}
