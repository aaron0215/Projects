import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


/**
 * This is the main method that will load the application.
 * <p>
 * DO NOT MODIFY
 */

public class SentimentAnalysis {

    /**
     * Main method reads command-line flags and outputs either the classifications of the test file or
     * uses cross-validation to compute a mean accuracy of the classifier.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        args = new String[] {"2", "src/sample_tests/train.txt", "src/sample_tests/test.txt"};
//        args = new String[] {"3", "src/sample_tests/train.txt", "1600"};
        if (args.length < 2) {
            System.out.println("usage: java SentimentAnalysis <mode> <trainingFilename> [<testFilename>|<K>]");
            return;
        }

        // Output classifications on test data
        int mode = Integer.parseInt(args[0]);

        Path trainFile = Paths.get(args[1]);
        List<Instance> trainData = createInstances(trainFile);

        Classifier clf = new NaiveBayesClassifier();

        if (mode == 0) {
            printDocumentsCountPerLabel(clf.getDocumentsCountPerLabel(trainData));
        } else if (mode == 1) {
            printWordsCountPerLabel(clf.getWordsCountPerLabel(trainData));
        } else if (mode == 2) {
            Path testFile = Paths.get(args[2]);
            List<Instance> testData = createInstances(testFile);

            clf.train(trainData, vocabularySize(trainData, testData));

            for (Instance i : testData) {
                ClassifyResult cr = clf.classify(i.words);
                System.out.printf("Actual=%-10s\tPredicted=%-10s\tLog probabilities: positive=%.2f\tnegative=%.2f\n",
                        i.label, cr.label, cr.logProbPerLabel.get(Label.POSITIVE), cr.logProbPerLabel.get(Label.NEGATIVE));
            }
        } else if (mode == 3){
            int k = Integer.parseInt(args[2]);
            double score = CrossValidation.kFoldScore(clf, trainData, k, vocabularySize(trainData));
            System.out.printf("%d-Fold Cross Validation Score=%.4f\n", k, score);
        }
    }

    public static int vocabularySize(List<Instance>... data) {
        Set<String> all = new HashSet<>();
        for (List<Instance> datum : data) {
            for (Instance instance : datum) {
                all.addAll(instance.words);
            }
        }
        return all.size();
    }

    /**
     * Reads the lines of the input file, treats the first token as the label and cleanses the
     * remainder, returning an list of instances.
     *
     * @param p
     * @return
     * @throws IOException
     */
    private static List<Instance> createInstances(Path p) throws IOException {
        return Files.lines(p)
                .map(line -> line.toLowerCase().split("\\s"))
                .map(tokens -> {
                    Instance ins = new Instance();
                    ins.words = new ArrayList<>(Arrays.asList(tokens));
                    ins.label = Label.values()[Integer.parseInt(ins.words.remove(0))];
                    return ins;
                }).collect(Collectors.toList());
    }

    /*
     * Prints the number of documents or words for each label
     */
    private static void printDocumentsCountPerLabel(Map<Label, Integer> count) {
        System.out.println("Documents:");
        System.out.println("POSITIVE=" + count.get(Label.POSITIVE));
        System.out.println("NEGATIVE=" + count.get(Label.NEGATIVE));
    }


    /*
     * Prints out the number of words for each label
     */
    private static void printWordsCountPerLabel(Map<Label, Integer> count) {
        System.out.println("Words:");
        System.out.println("POSITIVE=" + count.get(Label.POSITIVE));
        System.out.println("NEGATIVE=" + count.get(Label.NEGATIVE));
    }

}
