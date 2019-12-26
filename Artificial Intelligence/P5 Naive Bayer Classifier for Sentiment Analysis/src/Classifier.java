import java.util.List;
import java.util.Map;

/**
 * Interface class for the naive bayes classifier. For an explanation of methods, see
 * NaiveBayesClassifierImpl.
 * <p>
 * DO NOT MODIFY
 */
public interface Classifier {
    void train(List<Instance> trainData, int v);

    ClassifyResult classify(List<String> words);

    Map<Label, Integer> getWordsCountPerLabel(List<Instance> trainData);

    Map<Label, Integer> getDocumentsCountPerLabel(List<Instance> trainData);

}
