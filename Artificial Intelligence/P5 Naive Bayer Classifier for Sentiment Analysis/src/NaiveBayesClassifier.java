import java.util.*;

/**
 * Your implementation of a naive bayes classifier. Please implement all four methods.
 */

public class NaiveBayesClassifier implements Classifier {
  private int size = 0;
  private int vCount = 0;
  // private int posDoc = 0;
  // private int negDoc = 0;
  private Map<String, Integer> posWords;
  private Map<String, Integer> negWords;
  private Map<Label, Integer> documentsMap;
  private Map<Label, Integer> wordsMap;
  // private ArrayList<String> allWords;
  private Set<String> allWords;
  private Map<Label, Double> visited; // TODO use it to improve speed.

  /**
   * Trains the classifier with the provided training data and vocabulary size
   */
  @Override
  public void train(List<Instance> trainData, int v) {
    // Hint: First, calculate the documents and words counts per label and store them.
    // Then, for all the words in the documents of each label, count the number of occurrences of
    // each word.
    // Save these information as you will need them to calculate the log probabilities later.
    //
    // e.g.
    // Assume m_map is the map that stores the occurrences per word for positive documents
    // m_map.get("catch") should return the number of "catch" es, in the documents labeled positive
    // m_map.get("asdasd") would return null, when the word has not appeared before.
    // Use m_map.put(word,1) to put the first count in.
    // Use m_map.replace(word, count+1) to update the value
    size = trainData.size();
    vCount = v;
    visited = new HashMap<Label, Double>();
    // Get documents and words counts per label
    documentsMap = this.getDocumentsCountPerLabel(trainData);
    wordsMap = this.getWordsCountPerLabel(trainData);
  }

  /*
   * Counts the number of words for each label
   */
  @Override
  public Map<Label, Integer> getWordsCountPerLabel(List<Instance> trainData) {
    Map<Label, Integer> ret = new HashMap<>();
//    allWords = new ArrayList<String>();
    allWords = new HashSet<String>();
    posWords = new HashMap<String, Integer>();
    negWords = new HashMap<String, Integer>();
    int posCount = 0;
    int negCount = 0;
    for (Instance instance : trainData) {
      if (instance.label == Label.POSITIVE) {
        for (String word : instance.words) {
//           //if(!allWords.contains(word)) {
          allWords.add(word);
//           //}
          posWords.put(word, posWords.getOrDefault(word, 0) + 1);
        }
        posCount += instance.words.size();
      } else {
        for (String word : instance.words) {
//           //if(!allWords.contains(word)) {
          allWords.add(word);
//           //}
          negWords.put(word, negWords.getOrDefault(word, 0) + 1);
        }
        negCount += instance.words.size();
      }
    }
    ret.put(Label.POSITIVE, posCount);
    ret.put(Label.NEGATIVE, negCount);
    return ret;
  }


  /*
   * Counts the total number of documents for each label
   */
  @Override
  public Map<Label, Integer> getDocumentsCountPerLabel(List<Instance> trainData) {
    Map<Label, Integer> ret = new HashMap<>();
    for (Instance instance : trainData) {
      ret.put(instance.label,ret.getOrDefault(instance.label,0) + 1);
    }
    // posDoc = ret.get(Label.POSITIVE);
    // negDoc = ret.get(Label.NEGATIVE);
    return ret;
  }


  /**
   * Returns the prior probability of the label parameter, i.e. P(POSITIVE) or P(NEGATIVE)
   */
  private double p_l(Label label) {
    // Calculate the probability for the label. No smoothing here.
    // Just the number of label counts divided by the number of documents.
     if (size == 0) {
       return (label == Label.POSITIVE) ? 1.0 : 0.0;
     }
     return (double) documentsMap.getOrDefault(label, 0) / (double) size;
  }

  /**
   * Returns the smoothed conditional probability of the word given the label, i.e. P(word|POSITIVE)
   * or P(word|NEGATIVE)
   */
  private double p_w_given_l(String word, Label label) {
    // Calculate the probability with Laplace smoothing for word in class(label)
    Map<String, Integer> words = (label == Label.POSITIVE) ? posWords : negWords;;
    double numerator = (double) words.getOrDefault(word, 0) + 1.0;
    double denominator = 0.0;
    // TODO current implementation is too slow
//     for (Map.Entry<String, Integer> entry : map.entrySet())
//       denominator += 1.0 * entry.getValue();
//     denominator += 1.0 * vCount;
    if (visited == null || !visited.containsKey(label)) {
      for (String s : allWords) {
        denominator += (double) words.getOrDefault(s, 0);
      }
//      visited.put(label, denominator);
      denominator += (double) vCount;
      visited.put(label, denominator);
    } else {
//      denominator = visited.get(label) + (double) vCount;
      denominator = visited.get(label);
    }
    return (denominator == 0.0) ? 0.0 : (numerator / denominator);
  }

  /**
   * Classifies an array of words as either POSITIVE or NEGATIVE.
   */
  @Override
  public ClassifyResult classify(List<String> words) {
    // Sum up the log probabilities for each word in the input data, and the probability of the
    // label
    // Set the label to the class with larger log probability
    double posLog = this.p_l(Label.POSITIVE);
    double negLog = this.p_l(Label.NEGATIVE);
    if (posLog != 0)
      posLog = Math.log(posLog);
    if (negLog != 0) {
      negLog = Math.log(negLog);
    }
    for (String word : words) {
      double posCondLog = this.p_w_given_l(word, Label.POSITIVE);
      double negCondLog = this.p_w_given_l(word, Label.NEGATIVE);
//      if (posCondLog == 0)
//        posLog += 0;
//      else
//        posLog += Math.log(posCondLog);
//      
//      if (negCondLog == 0)
//        negLog += 0;
//      else
//        negLog += Math.log(negCondLog);
      posLog += (posCondLog == 0.0) ? 0.0 : Math.log(posCondLog);
      negLog += (negCondLog == 0.0) ? 0.0 : Math.log(negCondLog);
    }
    // Create result
    ClassifyResult ret = new ClassifyResult();
    // arg max
    ret.label = (posLog > negLog) ? Label.POSITIVE : Label.NEGATIVE;
    Map<Label, Double> logProb = new HashMap<Label, Double>();
    logProb.put(Label.POSITIVE, posLog);
    logProb.put(Label.NEGATIVE, negLog);
    ret.logProbPerLabel = logProb;
    return ret;
  }
}
